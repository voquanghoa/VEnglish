package com.qhvv.englishforalllevel.application;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.qhvv.englishforalllevel.controller.FileStoreController;
import com.qhvv.englishforalllevel.controller.UserResultController;
import com.qhvv.englishforalllevel.util.Utils;

/**
 * Created by Vo Quang Hoa on 12/26/2015.
 */
public class EnglishForAllLevelApplication extends Application {
    private static EnglishForAllLevelApplication singleton;

    public EnglishForAllLevelApplication getInstance(){
        return singleton;
    }

    public void onCreate() {
        super.onCreate();
        singleton = this;

        FileStoreController.getInstance().setBaseDir(getDataDir());
        UserResultController.getInstance().load();
    }

    private String getDataDir(){
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return packageInfo.applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException e) {
            Utils.Log("Error Package name not found ");
            Utils.Log(e);
        }
        return "";
    }
}
