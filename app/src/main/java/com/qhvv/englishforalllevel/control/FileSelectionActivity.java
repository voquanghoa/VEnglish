package com.qhvv.englishforalllevel.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.qhvv.englishforalllevel.QuestionListActivity;
import com.qhvv.englishforalllevel.R;
import com.qhvv.englishforalllevel.adapter.FileSelectAdapter;
import com.qhvv.englishforalllevel.constant.AppConstant;
import com.qhvv.englishforalllevel.model.DataItem;


public abstract class FileSelectionActivity extends BaseActivity implements FileSelectAdapter.FileSelectFeedback {
    private FileSelectAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_select_layout);
        adapter = new FileSelectAdapter(this, getDataItem(), this);
        ((ListView)findViewById(R.id.list_item)).setAdapter(adapter);
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

    public void customHanlder(DataItem dataItem) {

    }

    public void openFile(String filePath) {
        Intent intent = new Intent(this, QuestionListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.MESSAGE_FILE_NAME,filePath);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
