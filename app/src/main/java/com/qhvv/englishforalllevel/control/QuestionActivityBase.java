package com.qhvv.englishforalllevel.control;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.qhvv.englishforalllevel.R;
import com.qhvv.englishforalllevel.adapter.QuestionAnswerAdapter;
import com.qhvv.englishforalllevel.constant.AppConstant;
import com.qhvv.englishforalllevel.controller.AssetDataController;
import com.qhvv.englishforalllevel.controller.HttpDownloadController;
import com.qhvv.englishforalllevel.controller.OnlineDataController;
import com.qhvv.englishforalllevel.controller.UserResultController;
import com.qhvv.englishforalllevel.model.TestContent;
import com.qhvv.englishforalllevel.util.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

/**
 * Created by Vo Quang Hoa on 12/22/2015.
 */
public class QuestionActivityBase extends BaseActivity implements Runnable, HttpDownloadController.IDownload {
    private static final int BACK_KEY_DELAY_TIME = 2000;
    private QuestionAnswerAdapter questionAnswerAdapter;
    private int timeDuration = 0;
    private boolean isStopTimer = false;
    private DecimalFormat decimalFormat;
    private Button submitButton;
    private Runnable setTitleText = new Runnable() {
        public void run() {
            QuestionActivityBase.this.appTitle.setCenterTitleText(getDurationTime());
        }
    };
    private boolean isDelayFinish = true;
    private String currentFileName;
    private String currentFolder;

    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_content_layout);

        appTitle.setLeftTitleText("");
        listView = (ListView) findViewById(R.id.question_list_view);
        submitButton = (Button) findViewById(R.id.submit_button);

        Bundle bundle = getIntent().getExtras();
        currentFileName = bundle.getString(AppConstant.MESSAGE_FILE_NAME);
        currentFolder = bundle.getString(AppConstant.MESSAGE_FOLDER);

        decimalFormat = new DecimalFormat("00");
        timeDuration = 0;

        loadFileData();
    }

    private void showData(TestContent testContent){
        questionAnswerAdapter = new QuestionAnswerAdapter(this, testContent);
        new Thread(this).start();
        runOnUiThread(new Runnable() {
            public void run() {
                synchronized (questionAnswerAdapter) {
                    listView.setAdapter(questionAnswerAdapter);
                }
            }
        });
    }

    private void loadFileData(){
        if(currentFolder.equals(ASSET_FOLDER)){
            try {
                showData(AssetDataController.getInstance().loadTestFile(this, currentFileName));
            } catch (IOException e) {
                showLoadFileError();
            }
        }else{
            showLoadingDialog();
            setProgressMessage(R.string.downloading_file);
            String url = SERVER_BASE_PATH + currentFolder + currentFileName;
            HttpDownloadController.getInstance().startDownload(url, this);
        }
    }

    private void showLoadFileError(){
        showMessage(R.string.can_not_read_file);
        Utils.Log("Can not load file "+currentFileName);
    }

    public void onSubmit(View view){
        if (questionAnswerAdapter.isShowAnswer()) {
            this.finish();
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setTitle(R.string.dialog_result_title)
                    .setMessage(questionAnswerAdapter.getResultAsString(getDurationTime()))
                    .setCancelable(false)
                    .setPositiveButton(R.string.dialog_result_button, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            questionAnswerAdapter.setShowAnswer(true);
                            submitButton.setText(R.string.finish);

                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            isStopTimer = true;
            alertDialog.show();
            UserResultController.getInstance().setResult(currentFileName,
                    questionAnswerAdapter.getCorrects(), questionAnswerAdapter.getTotal());
        }
    }

    public void onBackPressed() {
        if (isDelayFinish && !questionAnswerAdapter.isShowAnswer()) {
            showMessage(R.string.delay_backkey_message);
            isDelayFinish = false;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    isDelayFinish = true;
                }
            }, BACK_KEY_DELAY_TIME);
        } else {
            this.finish();
        }
    }

    public void run() {
        while(!isStopTimer){
            try{
                Thread.sleep(1000);
            }catch (Exception ex){
                return;
            }
            timeDuration ++;
            this.runOnUiThread(setTitleText);
        }
    }

    private String getDurationTime() {
        return decimalFormat.format(timeDuration / 60) + ":" + decimalFormat.format(timeDuration % 60);
    }

    public void onDownloadDone(String url, byte[] data) {
        try {
            showData(OnlineDataController.getInstance().loadTestFile(data));
        } catch (UnsupportedEncodingException e) {
            showLoadFileError();
        }finally {
            closeLoadingDialog();
        }
    }

    public void onDownloadFail(String message) {
        showLoadFileError();
    }

    public void onDownloadProgress(int done, int total) {
        setProgressMessage("Download " + (done/1024)+" Kb/" + (total/1024)+" Kb.");
    }
}
