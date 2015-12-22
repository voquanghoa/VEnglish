package com.qhvv.englishforalllevel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qhvv.englishforalllevel.R;
import com.qhvv.englishforalllevel.model.DataItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Vo Quang Hoa on 12/20/2015.
 */
public class FileSelectAdapter extends BaseAdapter {
    public interface FileSelectFeedback{
        void customHanlder(DataItem dataItem);
        void openFile(String filePath);
    }

    private Context context;
    private DataItem dataItem;
    private List<DataItem> children;
    private Stack<DataItem> pathStack;
    private FileSelectFeedback selectFeedback;

    public FileSelectAdapter(Context context, DataItem dataItem, FileSelectFeedback selectFeedback){
        setDisplayDataItem(dataItem);
        this.context = context;
        this.pathStack = new Stack<>();
        this.selectFeedback = selectFeedback;
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

        convertView = createView(rowDataItem);

        TextView button =  (TextView)convertView.findViewById(R.id.button_main_menu);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                List<DataItem> localChildren = rowDataItem.getChildren();

                if(localChildren!=null && localChildren.size()>0){
                    FileSelectAdapter.this.pathStack.push(dataItem);
                    FileSelectAdapter.this.setDisplayDataItem(rowDataItem);
                }else if(selectFeedback!=null){
                    if(rowDataItem.getFileName().contains(".")){
                        selectFeedback.openFile(getCurrentPath() + rowDataItem.getFileName());
                    }else{
                        selectFeedback.customHanlder(rowDataItem);
                    }
                }
            }
        });
        button.setText(rowDataItem.getDisplay());
        return convertView;
    }

    private String getCurrentPath(){
        StringBuffer sb = new StringBuffer();
        for(int i=1; i<pathStack.size(); i++){
            sb.append(pathStack.get(i).getFileName()+"/");
        }
        sb.append(dataItem.getFileName()+"/");
        return sb.toString();
    }

    private View createView(DataItem dataItem){
        //NOTED : Implement pool object if any issue relative to memory leak or performance here
        int layout =  R.layout.data_item_folder_layout;

        if(pathStack.size()==0){
            layout = R.layout.data_item_category_layout;
        }else if(dataItem.isFileTest()){
            layout = R.layout.data_item_file_test;
        }

        return LayoutInflater.from(context).inflate(layout, null);
    }
}
