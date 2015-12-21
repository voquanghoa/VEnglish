package com.qhvv.englishforalllevel.control;

import android.os.Bundle;
import android.widget.ListView;

import com.qhvv.englishforalllevel.adapter.FileSelectAdapter;
import com.qhvv.englishforalllevel.model.DataItem;


public abstract class FileSelectionActivity extends BaseActivity {
    private FileSelectAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        adapter = new FileSelectAdapter(this, getDataItem());
        listView.setAdapter(adapter);
        setContentView(listView);
    }

    public void onBackPressed() {
        if(!adapter.showParent()){
            super.onBackPressed();
        }
    }

    protected abstract DataItem getDataItem();

}
