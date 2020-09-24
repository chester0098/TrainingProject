package com.fadineg.trainingproject.profile.change_dialog_fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.main.MainActivity;

import static com.fadineg.trainingproject.R.drawable.image_user;

public class ChangeDialogFragment extends MvpAppCompatDialogFragment implements ChangeDialogFragmentView {
    private CDFListener listener;
    private static final String FILE_PROVIDER = ".fileprovider";

    @InjectPresenter
    ChangeDialogFragmentPresenter changeDialogFragmentPresenter;

    public interface CDFListener {
        void action(int data);
    }

    public void setDialogListener(CDFListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_change, null);
        builder.setView(view);

        view.findViewById(R.id.choose_photo).setOnClickListener((View v) -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            getActivity().startActivityForResult(intent, MainActivity.REQUEST_CHOOSE_PHOTO);
            dismiss();
        });

        view.findViewById(R.id.take_photo).setOnClickListener((View v) -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(),
                    getActivity().getPackageName() + FILE_PROVIDER,
                    changeDialogFragmentPresenter.createFile(getContext())));

            getActivity().startActivityForResult(intent, MainActivity.REQUEST_TAKE_PHOTO);
            dismiss();
        });

        view.findViewById(R.id.delete).setOnClickListener((View v) -> {
            listener.action(image_user);
            dismiss();
        });

        return builder.create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

