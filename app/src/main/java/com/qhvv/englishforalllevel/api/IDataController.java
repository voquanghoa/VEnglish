package com.qhvv.englishforalllevel.api;

import android.content.Context;

import com.qhvv.englishforalllevel.model.DataItem;

/**
 * Created by voqua on 12/20/2015.
 */
public interface IDataController {
    void loadDataItem(Context context);
    DataItem getDataItem();
}
