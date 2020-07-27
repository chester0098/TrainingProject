package com.fadineg.trainingproject.profile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fadineg.trainingproject.R;

import java.io.File;

import static com.fadineg.trainingproject.R.*;
import static com.fadineg.trainingproject.R.drawable.ic_logo_splash_screen;
import static com.fadineg.trainingproject.R.drawable.image_man;
import static com.fadineg.trainingproject.R.drawable.image_user;


public class ProfileFragment extends Fragment {
    private ImageView userPhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_profile, container, false);
        userPhoto = view.findViewById(id.profile_iv_userPhoto);
        userPhoto.setImageResource(image_man);
        ChangeDialogFragment changeDialogFragment = new ChangeDialogFragment();
        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDialogFragment.setDialogListener(data -> {
                    userPhoto.setImageResource(data);
                });
                changeDialogFragment.show(getActivity().getSupportFragmentManager(), "PhotoFragmentDialog");
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    public void setImageFromCamera(Bitmap bitmap) {
        userPhoto.setImageBitmap(bitmap);
    }
}
