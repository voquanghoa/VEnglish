package com.qhvv.englishforalllevel;

import com.qhvv.englishforalllevel.control.FileSelectionActivity;
import com.qhvv.englishforalllevel.controller.OnlineDataController;
import com.qhvv.englishforalllevel.model.DataItem;

/**
 * Created by Vo Quang Hoa on 12/21/2015.
 */
public class GrammarActivity extends FileSelectionActivity {
    protected DataItem getDataItem() {
        return OnlineDataController.getInstance().getGrammarDataItem();
    }
}
