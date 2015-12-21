package com.qhvv.englishforalllevel.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.qhvv.englishforalllevel.R;

/**
 * Created by Vo Quang Hoa on 12/21/2015.
 */
public class AppTitle extends RelativeLayout {
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
                //Share facebook+
            }
        });

        this.findViewById(R.id.action_google).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Share google+
            }
        });
    }
}
