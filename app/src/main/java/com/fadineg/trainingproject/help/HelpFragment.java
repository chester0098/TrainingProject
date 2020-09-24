package com.fadineg.trainingproject.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;

public class HelpFragment extends MvpAppCompatFragment implements HelpView {
    @InjectPresenter
    HelpPresenter helpPresenter;
    private HelpRecyclerAdapter helpRecyclerAdapter;
    private RecyclerView helpRv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        helpRecyclerAdapter = new HelpRecyclerAdapter(helpPresenter.getHelpList());

        helpRv = view.findViewById(R.id.help_rv);
        helpRv.setHasFixedSize(true);
        helpRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        setRecyclerAdapter();

    }

    public void setRecyclerAdapter() {
        helpRv.setAdapter(helpRecyclerAdapter);
    }
}
