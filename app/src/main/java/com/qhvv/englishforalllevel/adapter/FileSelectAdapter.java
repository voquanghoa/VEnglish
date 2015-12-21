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
import java.util.Stack;

/**
 * Created by Vo Quang Hoa on 12/20/2015.
 */
public class FileSelectAdapter extends BaseAdapter {

    private Context context;
    private DataItem dataItem;
    private List<DataItem> children;
    private Stack<DataItem> pathStack;

    public FileSelectAdapter(Context context, DataItem dataItem){
        setDisplayDataItem(dataItem);
        this.context = context;
        this.pathStack = new Stack<>();
    }

    public boolean showParent(){
        if(pathStack.size()>0){
            setDisplayDataItem(pathStack.pop());
            return true;
        }
        return false;
    }

    public void setDisplayDataItem(DataItem dataItem){
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
        final DataItem rowDataItem = (DataItem) getItem(position);

        convertView = createView();

        Button button = (Button) convertView.findViewById(R.id.button_main_menu);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                List<DataItem> localChildren = rowDataItem.getChildren();
                if(localChildren!=null && localChildren.size()>0){
                    FileSelectAdapter.this.pathStack.push(dataItem);
                    FileSelectAdapter.this.setDisplayDataItem(rowDataItem);
                }else{
                    Utils.Log("Click on file item");
                }
            }
        });
        button.setText(rowDataItem.getDisplay());
        return convertView;
    }

    private View createView(){
        int layout = pathStack.size()==0 ? R.layout.data_item_category_layout:
                R.layout.data_item_folder_layout;

        return LayoutInflater.from(context).inflate(layout, null);
    }
}
