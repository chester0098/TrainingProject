package com.fadineg.trainingproject.domain.rxjava;


import androidx.appcompat.widget.SearchView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class RxSearchView {
    public static Observable<String> searchViewObservable(@NonNull SearchView searchView) {
        return Observable.create(emitter -> {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    emitter.onNext(newText);
                    return true;
                }
            });
        });
    }
}
