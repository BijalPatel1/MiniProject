package com.example.akashpatel.miniproject.Adapter;

import android.content.Context;
import android.content.Intent;;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.akashpatel.miniproject.Data.FragmentModel;
import com.example.akashpatel.miniproject.R;
import com.example.akashpatel.miniproject.UI.WallpaperActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.MyViewHolder> {

    private Context context;
    private List<FragmentModel> fragmentList;

    public FragmentAdapter(Context context, List<FragmentModel> fragmentList) {
        this.context = context;
        this.fragmentList = fragmentList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageview;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.fragment_imageview) ;
        }
    }

    @NonNull
    @Override
    public FragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_card_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentAdapter.MyViewHolder holder, int position) {
        FragmentModel fragmentModel=fragmentList.get(position);
        String imageurl=fragmentModel.getImages();
        String id=fragmentModel.getId();
      //  Glide.with(context).load(imageurl).override(350,250).into(holder.imageview);
        Picasso.with(context).load(imageurl).resize(350,250).into(holder.imageview);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface ClickListener
    {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class FragmentTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private FragmentAdapter.ClickListener clickListener;

        public FragmentTouchListener(Context context, final RecyclerView recyclerView, final FragmentAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
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
