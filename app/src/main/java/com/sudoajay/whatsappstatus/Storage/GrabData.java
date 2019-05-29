package com.sudoajay.whatsappstatus.Storage;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class GrabData {

    private String path;
    private ArrayList<String> arrayPath;

    public GrabData(final String path) {
        this.path = path;
        this.path = Environment.getExternalStorageDirectory().getAbsoluteFile() + path;
        arrayPath = new ArrayList<>();
        getData(new File(this.path));
    }

    private void getData(final File filesPath) {
        try {
            if (filesPath.exists() && filesPath.list().length != 0) {
                for (File file : filesPath.listFiles()) {
                    if (file.isDirectory()) getData(file);
                    else {
                        String path = file.getAbsoluteFile().toString();
                        if (path.contains("jpg") || path.contains("mp4"))
                            arrayPath.add(file.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            Log.d("onPath", e.getMessage());
        }
    }

    public ArrayList<String> getArrayPath() {
        return arrayPath;
    }
}
