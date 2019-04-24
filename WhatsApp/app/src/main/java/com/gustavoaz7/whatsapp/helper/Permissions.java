package com.gustavoaz7.whatsapp.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissions {

    public static boolean validatePermissions(int requestCode, Activity activity, String[] permissions) {

        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }

        List<String> permissionsList = new ArrayList<String>();

        for (String permission: permissions) {
            Boolean validatePermission = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
            if (!validatePermission) {
                permissionsList.add(permission);
            }
        }

        if (permissionsList.isEmpty()) {
            return true;
        }

        ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]), requestCode);

        return true;
    }
}


