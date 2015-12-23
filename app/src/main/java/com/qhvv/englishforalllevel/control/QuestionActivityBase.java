package com.qhvv.englishforalllevel.control;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.qhvv.englishforalllevel.R;
import com.qhvv.englishforalllevel.adapter.QuestionAnswerAdapter;
import com.qhvv.englishforalllevel.constant.AppConstant;
import com.qhvv.englishforalllevel.controller.AssetDataController;
import com.qhvv.englishforalllevel.model.TestContent;
import com.qhvv.englishforalllevel.util.Utils;

/**
 * Created by Vo Quang Hoa on 12/22/2015.
 */
public class QuestionActivityBase extends BaseActivity {
    private QuestionAnswerAdapter questionAnswerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_content_layout);
        ListView listView = (ListView) findViewById(R.id.question_list_view);

        String fileName = getIntent().getExtras().getString(AppConstant.MESSAGE_FILE_NAME);
        try{
            TestContent test = AssetDataController.getInstance().loadTestFile(this, fileName);
            questionAnswerAdapter = new QuestionAnswerAdapter(this, test);
            listView.setAdapter(questionAnswerAdapter);

        }catch (Exception ex) {
            Utils.Log(ex);
        }
    }

    public void onSubmit(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setTitle("This is result")
                .setMessage("Correct: 6/10 \nTime test: 00:11")
                .setCancelable(false)
                .setPositiveButton("Show Answers",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
