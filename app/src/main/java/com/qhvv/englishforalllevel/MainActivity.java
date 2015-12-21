package com.qhvv.englishforalllevel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.qhvv.englishforalllevel.control.BaseActivity;
import com.qhvv.englishforalllevel.controller.AssetDataController;

public class MainActivity extends BaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetDataController.getInstance().loadDataItem(this);
    }

    public void onGrammarClicked(View view){
        showMessage("Not implemented yet !");
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
}
