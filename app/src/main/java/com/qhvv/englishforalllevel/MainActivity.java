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
        setContentView(R.layout.main_menu_layout);
        AssetDataController.getInstance().loadDataItem(this);
    }

    public void onGrammarClicked(View view){
        if(OnlineDataController.getInstance().getGrammarDataItem() == null) {
            showLoadingDialog();
            HttpDownloadController.getInstance().startDownload(GRAMMAR_JSON_PATH, this);
        }else{
            startActivity(new Intent(this, GrammarActivity.class));
        }
    }

    public void onStudyOfflineClick(View view){
        startActivity(new Intent(this, StudyOfflineActivity.class));
    }

    public void onExaminationClick(View view){
        if(OnlineDataController.getInstance().getGrammarDataItem() == null) {
            showLoadingDialog();
            HttpDownloadController.getInstance().startDownload(EXAMINATION_JSON_PATH, this);
        }else{
            startActivity(new Intent(this, ExaminationActivity.class));
        }
    }

    public void onMoreAppClick(View view){
        showMessage("Not implemented yet !");
    }

    public void onDownloadDone(String downloadUrl, byte[] data) {
        closeLoadingDialog();
        if(downloadUrl.equals(EXAMINATION_JSON_PATH)){
            OnlineDataController.getInstance().loadExamination(data);
            startActivity(new Intent(this, ExaminationActivity.class));
        }else{
            OnlineDataController.getInstance().loadGrammar(data);
            startActivity(new Intent(this, GrammarActivity.class));
        }
    }

    public void onListenExerciseClick(View view) {

    }

    public void onDownloadFail(String message) {
        showMessage("Download file fail !!!");
    }

    public void onDownloadProgress(int done, int total) {
        setProgressMessage("Download " + (done/1024)+" Kb/" + (total/1024)+" Kb.");
    }
}
