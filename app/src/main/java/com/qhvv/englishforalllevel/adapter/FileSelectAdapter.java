package com.qhvv.englishforalllevel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.qhvv.englishforalllevel.R;
import com.qhvv.englishforalllevel.model.DataItem;
import com.qhvv.englishforalllevel.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voqua on 12/20/2015.
 */
public class FileSelectAdapter extends BaseAdapter {

    private Context context;
    private DataItem dataItem;
    private List<DataItem> children;

    public FileSelectAdapter(Context context, DataItem dataItem){
        setDataItem(dataItem);
        this.context = context;
    }

    public void setDataItem(DataItem dataItem){
        this.dataItem = dataItem;
        this.children = dataItem.getChildren();
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        this.notifyDataSetChanged();
    }
    public int getCount() {
        return children.size();
    }


    public Object getItem(int position) {
        return children.get(position);
    }


    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        final DataItem dataItem = (DataItem) getItem(position);

        if(convertView == null){
            LayoutInflater inflator = LayoutInflater.from(context);
            convertView = inflator.inflate(R.layout.data_item_layout, null);
        }

        Button button = (Button) convertView.findViewById(R.id.button_main_menu);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                List<DataItem> localChildren = dataItem.getChildren();
                if(localChildren!=null && localChildren.size()>0){
                    FileSelectAdapter.this.setDataItem(dataItem);
                }else{
                    Utils.Log("Click on file item");
                }
            }
        });
        button.setText(dataItem.getDisplay());
        return convertView;
    }

    public DataItem getDataItem() {
        return dataItem;
    }
}
