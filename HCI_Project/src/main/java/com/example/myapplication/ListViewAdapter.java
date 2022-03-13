package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class ListViewAdapter<A> extends ArrayAdapter<Assignment> {
    private Context mContext;
    private List<Assignment> assignments= new ArrayList<>();
    double sessionTime;
    private long sessionTimeLong;

    public ListViewAdapter(@NonNull Context context, int resource, ArrayList<Assignment> list) {
        super(context, 0, list);
        mContext = context;
        assignments = list;
        sessionTime = 0.0;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View listItems = convertView;
      if(listItems == null)
          listItems = LayoutInflater.from(getContext()).inflate(R.layout.layout_listview_2, parent, false);

      Assignment currentAssignment = assignments.get(position);

      TextView tvName = (TextView) listItems.findViewById(R.id.tvName);
      tvName.setText(currentAssignment.getName());

      TextView tvClockTime = (TextView) listItems.findViewById(R.id.tvTime);
      tvClockTime.setText(currentAssignment.getClockTime());
      int id = -1;
      int time = (int) CalendarUtils.actualSessionTime;
      System.out.println(time);


      return listItems;
    }


}
