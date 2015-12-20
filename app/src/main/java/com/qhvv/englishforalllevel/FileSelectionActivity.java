package com.qhvv.englishforalllevel;

import android.os.Bundle;
import android.widget.ListView;

import com.qhvv.englishforalllevel.adapter.FileSelectAdapter;
import com.qhvv.englishforalllevel.control.BaseActivity;
import com.qhvv.englishforalllevel.controller.AssetDataController;
import com.qhvv.englishforalllevel.model.DataItem;
import com.qhvv.englishforalllevel.util.DataUtil;

/**
 * Created by voqua on 12/20/2015.
 */
public class FileSelectionActivity extends BaseActivity {
    private FileSelectAdapter adapter;
    private DataItem dataItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        dataItem = AssetDataController.getInstance().getDataItem();
        adapter = new FileSelectAdapter(this, dataItem);
        listView.setAdapter(adapter);
        setContentView(listView);
    }

    public void onBackPressed() {
        DataItem parentItem = DataUtil.getParent(dataItem, adapter.getDataItem());
        if(parentItem != null){
            adapter.setDataItem(parentItem);
        }else{
            super.onBackPressed();
        }
    }
}
