package com.qhvv.englishforalllevel.util;

import com.qhvv.englishforalllevel.model.DataItem;

import java.util.List;

/**
 * Created by voqua on 12/20/2015.
 */
public class DataUtil {
    public static DataItem getParent(DataItem dataStruct, DataItem dataToFind){
        List<DataItem> children = dataStruct.getChildren();
        if(children == null){
            return  null;
        }

        for(int i=0;i<children.size();i++){
            if(children.get(i) == dataToFind){
                return dataStruct;
            }
        }

        for(int i=0;i<children.size();i++){
            DataItem parent = getParent(children.get(i), dataToFind);
            if(parent != null){
                return parent;
            }
        }
        return null;
    }
}
