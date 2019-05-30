package com.sudoajay.whatsappstatus.Storage;

import android.app.Activity;
import android.os.Environment;

import com.sudoajay.whatsappstatus.CustomToast;

import java.io.File;
import java.util.ArrayList;

public class GrabData {

    private String path;
    private ArrayList<String> arrayPath;
    private Activity activity;
    private final String errorMes = "Sorry Can't Process this Please report this";

    public GrabData(final Activity activity, final String path) {
        this.path = Environment.getExternalStorageDirectory().getAbsoluteFile() +"/"+ path;
        this.activity = activity;
        arrayPath = new ArrayList<>();
        getData(new File(this.path));
    }

    private void getData(final File filesPath) {
        try {
            if (filesPath.exists() && filesPath.list().length != 0) {
                for (File file : filesPath.listFiles()) {
                    if (file.isDirectory()) getData(file);
                    else {
                            arrayPath.add(file.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            CustomToast.ToastIt(activity, errorMes);
        }
    }

    public ArrayList<String> getArrayPath() {
        return arrayPath;
    }
}
