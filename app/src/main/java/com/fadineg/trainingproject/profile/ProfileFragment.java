package com.fadineg.trainingproject.profile;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static com.fadineg.trainingproject.R.*;
import static com.fadineg.trainingproject.R.drawable.image_man;


public class ProfileFragment extends Fragment {
    private ImageView userPhoto;
    static final String PHOTO_FRAGMENT_TAG = "PhotoFragmentDialog";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userPhoto = view.findViewById(id.profile_iv_userPhoto);
        userPhoto.setImageResource(image_man);
        ChangeDialogFragment changeDialogFragment = new ChangeDialogFragment();
        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDialogFragment.setDialogListener(data -> {
                    userPhoto.setImageResource(data);
                });
                changeDialogFragment.show(getChildFragmentManager(), PHOTO_FRAGMENT_TAG);
            }
        });

        setHasOptionsMenu(true);
    }

    public void setImageFromCamera(Bitmap bitmap) {
        userPhoto.setImageBitmap(bitmap);
    }
}
