package com.fadineg.trainingproject.profile.change_dialog_fragment

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.main.MainPresenter
import java.io.File

@InjectViewState
class ChangeDialogFragmentPresenter : MvpPresenter<ChangeDialogFragmentView>() {
    fun createFile(context: Context): File {
        return File(context.getExternalFilesDir(MainPresenter.FILES_DIR),
                MainPresenter.FILE_NAME)
    }
}