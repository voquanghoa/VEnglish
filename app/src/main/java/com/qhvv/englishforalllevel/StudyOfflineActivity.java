package com.qhvv.englishforalllevel;

import com.qhvv.englishforalllevel.control.FileSelectionActivity;
import com.qhvv.englishforalllevel.controller.AssetDataController;
import com.qhvv.englishforalllevel.model.DataItem;

/**
 * Created by Vo Quang Hoa on 12/21/2015.
 */
public class StudyOfflineActivity extends FileSelectionActivity {
    protected DataItem getDataItem() {
        return AssetDataController.getInstance().getGrammarDataItem();
    }

    protected String getFolder() {
        return null;
    }
}
