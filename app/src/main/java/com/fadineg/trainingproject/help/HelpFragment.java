package com.fadineg.trainingproject.help;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fadineg.trainingproject.R;

public class HelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HelpList helpList = new HelpList();
        RecyclerView helpRv = view.findViewById(R.id.help_rv);
        helpRv.setHasFixedSize(true);
        helpRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        HelpRecyclerAdapter adapter = new HelpRecyclerAdapter(helpList.createdHelpList());
        helpRv.setAdapter(adapter);
    }
}
