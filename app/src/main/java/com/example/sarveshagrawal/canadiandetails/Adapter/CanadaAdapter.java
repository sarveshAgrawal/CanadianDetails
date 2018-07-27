package com.example.sarveshagrawal.canadiandetails.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.sarveshagrawal.canadiandetails.Data.Canada_List_Model;
import com.example.sarveshagrawal.canadiandetails.R;

import java.util.ArrayList;

/**
 * Created by Sarvesh Agrawal on 27-07-2018.
 */


public class CanadaAdapter extends BaseAdapter {
    ArrayList<Canada_List_Model> mList;
    Context context;
    LayoutInflater inflater;

    public CanadaAdapter(ArrayList<Canada_List_Model> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.from(context).inflate(R.layout.activity_main,null);
        return view;
    }
}
