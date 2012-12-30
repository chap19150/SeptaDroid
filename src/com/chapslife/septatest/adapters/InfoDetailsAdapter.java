package com.chapslife.septatest.adapters;

import java.util.ArrayList;
import java.util.List;

import com.chapslife.septatest.R;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class InfoDetailsAdapter extends BaseExpandableListAdapter {  
    Activity activity;  
    ArrayList<String> group;
    ArrayList<List<String>> child;
 
    public InfoDetailsAdapter(Activity a, ArrayList<String> _group, ArrayList<List<String>> _child){  
        activity = a; 
        group = _group;
        child = _child;
    }  
      
    //child method stub  
      
    public Object getChild(int groupPosition, int childPosition) {  
        // TODO Auto-generated method stub  
        return child.get(groupPosition).get(childPosition);  
    }  

    public long getChildId(int groupPosition, int childPosition) {  
        // TODO Auto-generated method stub  
        return childPosition;  
    }  

    public int getChildrenCount(int groupPosition) {  
        // TODO Auto-generated method stub  
        return child.get(groupPosition).size();  
    }  
      
    public View getChildView(int groupPosition, int childPosition,  
            boolean isLastChild, View convertView, ViewGroup parent) {  
        // TODO Auto-generated method stub  
        String string = child.get(groupPosition).get(childPosition);  
        return getGenericView(string);  
    }  

    //group method stub  
    public Object getGroup(int groupPosition) {  
        // TODO Auto-generated method stub  
        return group.get(groupPosition);  
    }  

    public int getGroupCount() {  
        // TODO Auto-generated method stub  
        return group.size();  
    }  

    public long getGroupId(int groupPosition) {  
        // TODO Auto-generated method stub  
        return groupPosition;  
    }  

    public View getGroupView(int groupPosition, boolean isExpanded,  
            View convertView, ViewGroup parent) {  
        // TODO Auto-generated method stub  
        String string = group.get(groupPosition);  
        return getGenericView(string);  
    }  

    //View stub to create Group/Children 's View  
    public TextView getGenericView(String s) {  
        // Layout parameters for the ExpandableListView  
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(  
                ViewGroup.LayoutParams.MATCH_PARENT, 68);  

        TextView text = (TextView) activity.getLayoutInflater().inflate(R.layout.list_item_textview, null);
        text.setLayoutParams(lp);  
        // Center the text vertically  
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);  
        // Set the text starting position  
        text.setPadding(63, 0, 0, 0);  
        text.setText(s);  
        return text;  
    }  
         
    public boolean hasStableIds() {  
        // TODO Auto-generated method stub  
        return false;  
    }  

    public boolean isChildSelectable(int groupPosition, int childPosition) {  
        // TODO Auto-generated method stub  
        return true;  
    }  
      
      
} 
