package com.example.akashpatel.miniproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.akashpatel.miniproject.Data.CategoryModel;
import com.example.akashpatel.miniproject.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    List<CategoryModel> list;
    LayoutInflater inflater;

    public CustomAdapter(Context context, List<CategoryModel> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {

            view = new View(context);
            view = inflater.inflate(R.layout.single_row, null);

            TextView textView = (TextView) view.findViewById(R.id.txtVwCat);
            ImageView imageView = (ImageView)view.findViewById(R.id.imgVwCat);

            textView.setText(list.get(position).getCat_name());

            String imageurl = list.get(position).getCat_image();

            Glide.with(context).load(imageurl).placeholder(R.drawable.placeholder).error(R.drawable.error_image).into(imageView);

        } else {
            view = (View) convertView;
        }

        return view;
    }
}

