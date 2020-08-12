package com.fadineg.trainingproject.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.fadineg.trainingproject.R;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment {
    private SearchProvider searchProvider;
    private Context context;
    private SearchView searchView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private SearchViewPagerAdapter mFragmentAdapter;
    private Handler mainHandler;
    private Runnable page1update;
    private Runnable page2update;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        searchProvider = (SearchProvider) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        searchProvider.setTabPosition(mTabLayout.getSelectedTabPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragmentAdapter = new SearchViewPagerAdapter(getChildFragmentManager(), getContext());

        mViewPager = view.findViewById(R.id.search_viewPager);
        mTabLayout = view.findViewById(R.id.search_tabLayout);


        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = view.findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.clearFocus();


        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0)
                    searchView.setQuery(searchProvider.getSearchViewPage1(), false);
                else
                    searchView.setQuery(searchProvider.getSearchViewPage2(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        searchViewSubscribe();

        if (searchProvider.getTabPosition() == 0) {
            searchView.setQuery(searchProvider.getSearchViewPage1(), false);
            mainHandler.post(page1update);
        } else {
            searchView.setQuery(searchProvider.getSearchViewPage2(), false);
            mainHandler.post(page2update);
        }

        mTabLayout.getTabAt(searchProvider.getTabPosition()).select();
    }

    private void searchViewSubscribe() {
        mainHandler = new Handler(context.getMainLooper());
        page1update = () -> {
            mFragmentAdapter.getPage1().updateRecyclerAdapter(searchProvider.getSearchViewPage1());

        };
        page2update = () -> {
            mFragmentAdapter.getPage2().updateRecyclerAdapter(searchProvider.getSearchViewPage2());
        };


        Observable<String> searchViewObservable = RxSearchView.searchViewObservable(searchView);

        searchViewObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull String s) {
                        if (mViewPager.getCurrentItem() == 0) {
                            searchProvider.setSearchViewPage1(s);
                            mainHandler.post(page1update);
                        } else {
                            searchProvider.setSearchViewPage2(s);
                            mainHandler.post(page2update);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
