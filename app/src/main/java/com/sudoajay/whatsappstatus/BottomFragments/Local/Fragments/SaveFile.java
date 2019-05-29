package com.sudoajay.whatsappstatus.BottomFragments.Local.Fragments;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;


import com.sudoajay.whatsappstatus.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SaveFile {

    private String filePath, aapFolder, tabName;
    private Activity activity;
    private final String errorMes = "Sorry Can't Process this Please report this";

    public SaveFile(final Activity activity, final String filePath, final String tabName) {
        this.filePath = filePath;
        this.activity = activity;
        if (tabName.equals("photo")) this.tabName = "Photo";
        else {
            this.tabName = "Video";
        }
        CheckForFolder(); // check folder and create folder if not present
        SaveIt();           // save the file
    }

    private void CheckForFolder() {
        String internalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        aapFolder = internalPath + "/" + activity.getResources().getString(R.string.app_name);
        CreateFolderIfNot(new File(aapFolder)); // app name folder

        CreateFolderIfNot(new File(aapFolder + "/Photo")); // app Photo Folder

        CreateFolderIfNot(new File(aapFolder + "/Video")); // app Video Folder

    }

    private void CreateFolderIfNot(final File path) {

        try {
            if (!path.exists()) if (!path.mkdir()) ToastIt(errorMes);
        } catch (Exception ignored) {
            ToastIt(errorMes);
        }
    }

    public void SaveIt() {
        InputStream is;
        OutputStream os;
        String getName = new File(filePath).getName();
        try {
            if (new File(aapFolder + "/" + tabName).exists()) {
                File outputFile = new File(aapFolder + "/" + tabName + "/" + getName);
                if (outputFile.createNewFile()) {
                    is = new FileInputStream(new File(filePath));
                    os = new FileOutputStream(outputFile);

                    byte[] buffer = new byte[1024];
                    int read;
                    while ((read = is.read(buffer)) != -1) {
                        os.write(buffer, 0, read);
                    }
                    ToastIt("File Saved");
                } else {
                    ToastIt(errorMes);
                }
            }else {
                ToastIt(errorMes);
            }

        } catch (Exception e) {
            if (e.getMessage().equals("write failed: ENOSPC (No space left on device)"))
                ToastIt( "No space left on device");

        }
    }

    private void ToastIt(final String mess){
        Toast.makeText(activity,mess,Toast.LENGTH_SHORT).show();
    }

}
