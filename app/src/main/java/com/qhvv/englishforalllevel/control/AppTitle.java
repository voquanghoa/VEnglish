package com.qhvv.englishforalllevel.control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.plus.PlusShare;
import com.qhvv.englishforalllevel.R;
import com.qhvv.englishforalllevel.constant.AppConstant;

/**
 * Created by Vo Quang Hoa on 12/21/2015.
 */
public class AppTitle extends RelativeLayout {
    private TextView centerTitle;
    private TextView leftTitle;

    public AppTitle(Context context) {
        super(context);
        initView();
    }

    public AppTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AppTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.app_title, this, true);
        this.findViewById(R.id.action_facebook).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String shareUrl = AppConstant.FACEBOOK_SHARE_FULL_PATH;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shareUrl));
                getContext().startActivity(intent);
            }
        });

        this.findViewById(R.id.action_google).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent shareIntent = new PlusShare.Builder(AppTitle.this.getContext())
                        .setType("text/plain")
                        .setText("")
                        .setContentUrl(Uri.parse(AppConstant.APP_PACKAGE))
                        .getIntent();

                ((Activity)getContext()).startActivityForResult(shareIntent, 0);
            }
        });

        centerTitle = (TextView) findViewById(R.id.center_title);
        leftTitle = (TextView) findViewById(R.id.left_title);
    }

    public void setCenterTitleText(String text) {
        centerTitle.setText(text);
    }

    public void setLeftTitleText(String text) {
        leftTitle.setText(text);
    }
}
