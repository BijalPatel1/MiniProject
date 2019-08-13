package com.example.akashpatel.miniproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.akashpatel.miniproject.Data.CategoryModel;
import com.example.akashpatel.miniproject.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{

    private List<CategoryModel> list;
    private Context context;
    long selectedPosition;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtVwName;
        ImageView imgVwIcon;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.txtVwName = (TextView) itemView.findViewById(R.id.textViewNameCardLay);
            this.imgVwIcon = (ImageView) itemView.findViewById(R.id.imageViewCardLay);
        }
    }

    public RecycleAdapter(Context context,List<CategoryModel> list,long selectedPosition)
    {
        this.context=context;
        this.list=list;
        this.selectedPosition=selectedPosition;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

       // TextView textViewName = holder.txtVwName;
        //ImageView imageView = holder.imgVwIcon;
        if(selectedPosition==position)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"));
        }

        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();
            }
        });


        CategoryModel cm=list.get(position);
        list.get(position).getCat_image();

        String imgUrl=cm.getCat_image();
        String txt=cm.getCat_name();
        String id=cm.getId();

        holder.txtVwName.setText(txt);
        Glide.with(context).load(imgUrl).into(holder.imgVwIcon);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ClickListener
    {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener
    {
        private GestureDetector gestureDetector;
        private RecycleAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecycleAdapter.ClickListener clickListener)
        {
            this.clickListener=clickListener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener()
            {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
