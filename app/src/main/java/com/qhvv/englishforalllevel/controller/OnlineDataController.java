package com.qhvv.englishforalllevel.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qhvv.englishforalllevel.api.IDataController;
import com.qhvv.englishforalllevel.model.DataItem;
import com.qhvv.englishforalllevel.util.Utils;

import java.lang.reflect.Type;

/**
 * Created by Vo Quang Hoa on 12/21/2015.
 */
public class OnlineDataController implements IDataController {
    private static OnlineDataController instance;
    public static OnlineDataController getInstance(){
        if(instance==null){
            instance = new OnlineDataController();
        }
        return instance;
    }
    private DataItem dataItem;

    private OnlineDataController(){

    }

    public void loadData(byte[] data){
        try{
            Type listType = new TypeToken<DataItem>(){}.getType();
            String strData = new String(data, "UTF-8");
            dataItem = new Gson().fromJson(strData, listType);
        }catch (Exception ex){
            Utils.Log(ex);
        }
    }

    public void loadDataItem(Context context) {

    }

    public void loadTestFile(String path) {

    }

    public DataItem getDataItem() {
        return dataItem;
    }
}
