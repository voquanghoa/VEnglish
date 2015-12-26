package com.qhvv.englishforalllevel.control;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.qhvv.englishforalllevel.R;

/**
 * Created by voqua on 12/20/2015.
 */
public class BaseActivity extends Activity implements DialogInterface.OnCancelListener {
    protected AppTitle appTitle;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected synchronized void showLoadingDialog(){
        if(progressDialog==null){
            progressDialog = ProgressDialog.show(this, "",getString(R.string.loading) , true);
            progressDialog.setOnCancelListener(this);
        }
        progressDialog.show();
    }

    protected synchronized void closeLoadingDialog(){
        this.runOnUiThread(new Runnable() {
            public void run() {
                if (progressDialog != null) {
                    progressDialog.hide();
                }
            }
        });
    }

    protected synchronized void setProgressMessage(final String message){
        this.runOnUiThread(new Runnable() {
            public void run() {
                if(progressDialog!=null){
                    progressDialog.setMessage(message);
                }
            }
        });
    }

    public void onCancel(DialogInterface dialog) {

    }

    protected void showMessage(final String content){
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(BaseActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void showMessage(final int contentId) {
        showMessage(getString(contentId));
    }
}