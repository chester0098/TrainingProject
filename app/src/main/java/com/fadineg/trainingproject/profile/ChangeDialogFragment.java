package com.fadineg.trainingproject.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.main.MainActivity;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.fadineg.trainingproject.R.drawable.ic_image_user;
import static com.fadineg.trainingproject.R.drawable.image_user;

public class ChangeDialogFragment extends DialogFragment {
    private CDFListener listener;
    private static final String FILE_PROVIDER = ".fileprovider";

    public interface CDFListener {
        void action(int data);
    }

    void setDialogListener(CDFListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_change, null);

        builder.setView(view);

        view.findViewById(R.id.choose_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        view.findViewById(R.id.take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(getContext().getExternalFilesDir(MainActivity.FILES_DIR),
                        MainActivity.FILE_NAME);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(),
                        getActivity().getPackageName() + FILE_PROVIDER, file));
                getActivity().startActivityForResult(intent, MainActivity.REQUEST_TAKE_PHOTO);
                dismiss();
            }
        });

        view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.action(image_user);
                dismiss();
            }
        });


        return builder.create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

