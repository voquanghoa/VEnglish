package com.qhvv.englishforalllevel.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qhvv.englishforalllevel.api.IDataController;
import com.qhvv.englishforalllevel.constant.AppConstant;
import com.qhvv.englishforalllevel.model.DataItem;
import com.qhvv.englishforalllevel.util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * Created by voqua on 12/20/2015.
 */
public class AssetDataController implements IDataController, AppConstant {

    private static AssetDataController instance;
    public static synchronized AssetDataController getInstance(){
        if(instance == null){
            instance = new AssetDataController();
        }
        return instance;
    }
    private AssetDataController(){}

    private DataItem dataItem;

    public void loadDataItem(Context context) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(ASSET_PATH_FILE)));
            StringBuffer stringBuffer = new StringBuffer();

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                stringBuffer.append(mLine);
            }
            Type listType = new TypeToken<DataItem>(){}.getType();
            dataItem = new Gson().fromJson(stringBuffer.toString(), listType);
        } catch (IOException e) {
            Utils.Log(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Utils.Log(e);
                }
            }
        }
    }

    public void loadTestFile(String path) {

    }

    public DataItem getDataItem() {
        return dataItem;
    }
}
