package com.example.sarveshagrawal.canadiandetails.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sarveshagrawal.canadiandetails.Activity.Description;
import com.example.sarveshagrawal.canadiandetails.Data.Canada_List_Model;
import com.example.sarveshagrawal.canadiandetails.R;

import java.util.ArrayList;

/**
 * Created by Sarvesh Agrawal on 27-07-2018.
 */


public class CanadaListAdapter extends BaseAdapter {
    ArrayList<Canada_List_Model> mList;
    Context context;
    LayoutInflater inflater;

    public CanadaListAdapter(ArrayList<Canada_List_Model> mList, Context context) {
        this.mList = mList;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = inflater.from(context).inflate(R.layout.view_list,null);

        TextView tittle = (TextView)view.findViewById(R.id.title);
        TextView description = (TextView)view.findViewById(R.id.description);
        ImageView add_icon = (ImageView)view.findViewById(R.id.add_icon);
        ImageView image = (ImageView)view.findViewById(R.id.icon);
        add_icon.setTag(position);

        tittle.setText(mList.get(position).getTitle());
        description.setText(mList.get(position).getDescription());
        Glide.with(context).load(mList.get(position).getImageHref()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(image);

        add_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(v.getTag().toString());

                Intent intent = new Intent(context, Description.class);
                intent.putExtra("title",mList.get(index).getTitle());
                intent.putExtra("description",mList.get(index).getDescription());
                intent.putExtra("image",mList.get(index).getImageHref());
                context.startActivity(intent);


            }
        });
        return view;
    }
}
