package com.qhvv.englishforalllevel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.qhvv.englishforalllevel.constant.AppConstant;
import com.qhvv.englishforalllevel.control.BaseActivity;
import com.qhvv.englishforalllevel.controller.AssetDataController;
import com.qhvv.englishforalllevel.controller.HttpDownloadController;
import com.qhvv.englishforalllevel.controller.OnlineDataController;

public class MainActivity extends BaseActivity implements HttpDownloadController.IDownload, AppConstant {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetDataController.getInstance().loadDataItem(this);
    }

    public void onGrammarClicked(View view){
        if(OnlineDataController.getInstance().getDataItem() == null) {
            showLoadingDialog();
            HttpDownloadController.getInstance().startDownload(ONLINE_PATH_FILE, this);
        }else{
            startActivity(new Intent(this, GramarActivity.class));
        }
    }

    public void onStudyOfflineClick(View view){
        startActivity(new Intent(this, StudyOfflineActivity.class));
    }

    public void onExaminationClick(View view){
        showMessage("Not implemented yet !");
    }

    public void onMoreAppClick(View view){
        showMessage("Not implemented yet !");
    }

    public void onDownloadDone(byte[] data) {
        closeLoadingDialog();
        OnlineDataController.getInstance().loadData(data);
        startActivity(new Intent(this, GramarActivity.class));
    }

    public void onDownloadFail(String message) {
        showMessage("Download file fail !!!");
    }

    public void onDownloadProgress(int done, int total) {
        setProgressMessage("Download " + (done/1024)+" Kb/" + (total/1024)+" Kb.");
    }
}
