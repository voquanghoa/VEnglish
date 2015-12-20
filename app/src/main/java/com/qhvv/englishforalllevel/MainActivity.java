package com.qhvv.englishforalllevel;

import android.content.Intent;
import android.os.Bundle;

import com.qhvv.englishforalllevel.control.BaseActivity;
import com.qhvv.englishforalllevel.controller.AssetDataController;

public class MainActivity extends BaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetDataController.getInstance().loadDataItem(this);
        startActivity(new Intent(this, FileSelectionActivity.class));
    }
}
