package com.sudoajay.whatsappstatus.BottomFragments.Download;

import android.app.Activity;
import android.os.Environment;

import com.sudoajay.whatsappstatus.CustomToast;
import com.sudoajay.whatsappstatus.R;

import java.io.File;

public class GrabTheData {

    private String appFolderName;
    private Activity activity;
    private final String errorMes = "Sorry Can't Process this Please report this";

    public GrabTheData(final Activity activity) {
        this.activity= activity;
        CheckForFolder();
    }

    private void CheckForFolder() {
        String internalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        appFolderName = internalPath + "/" + activity.getResources().getString(R.string.app_name);
        CreateFolderIfNot(new File(appFolderName)); // app name folder

        CreateFolderIfNot(new File(appFolderName + "/Photo")); // app Photo Folder

        CreateFolderIfNot(new File(appFolderName + "/Video")); // app Video Folder

    }

    private void CreateFolderIfNot(final File path) {

        try {
            if (!path.exists()) if (!path.mkdir()) CustomToast.ToastIt(activity,errorMes);
        } catch (Exception ignored) {
            CustomToast.ToastIt(activity,errorMes);
        }
    }
}
