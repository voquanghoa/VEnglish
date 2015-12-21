package com.qhvv.englishforalllevel.control;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.qhvv.englishforalllevel.R;
import com.qhvv.englishforalllevel.adapter.FileSelectAdapter;
import com.qhvv.englishforalllevel.model.DataItem;


public abstract class FileSelectionActivity extends ListActivity {
    private FileSelectAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_select_layout);
        adapter = new FileSelectAdapter(this, getDataItem());
        setListAdapter(adapter);
    }

    public void onBackPressed() {
        if(!adapter.showParent()){
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    protected abstract DataItem getDataItem();

}
