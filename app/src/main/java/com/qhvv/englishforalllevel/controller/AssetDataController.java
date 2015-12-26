package com.qhvv.englishforalllevel.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qhvv.englishforalllevel.api.IDataController;
import com.qhvv.englishforalllevel.constant.AppConstant;
import com.qhvv.englishforalllevel.model.DataItem;
import com.qhvv.englishforalllevel.model.TestContent;
import com.qhvv.englishforalllevel.util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
        try {
            String fileData = readFile(context, JSON_DATA_FILE);
            Type listType = new TypeToken<DataItem>(){}.getType();
            dataItem = new Gson().fromJson(fileData, listType);
        } catch (IOException e) {
            Utils.Log(e);
        }
    }

    public TestContent loadTestFile(Context context, String path) throws IOException {
        ArrayList<String> lines = readFileAsArray(context, path);
        return QuestionHelper.readQuestion(lines);
    }

    public DataItem getGrammarDataItem() {
        return dataItem;
    }

    private String readFile(Context context, String path) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(path)));
            StringBuffer stringBuffer = new StringBuffer();

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                stringBuffer.append(mLine+"\n");
            }
            return stringBuffer.toString();
        } finally {
            reader.close();
        }
    }

    private ArrayList<String> readFileAsArray(Context context, String path) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(path)));
            ArrayList<String> lines = new ArrayList<>();
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                lines.add(mLine);
            }
            return lines;
        } finally {
            reader.close();
        }
    }
}
