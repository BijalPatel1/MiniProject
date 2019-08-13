package com.example.akashpatel.miniproject.UI;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.akashpatel.miniproject.Data.FragmentModel;
import com.example.akashpatel.miniproject.R;
import com.example.akashpatel.miniproject.rest.ApiClient;
import com.example.akashpatel.miniproject.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WallpaperActivity extends AppCompatActivity {
    ImageView wallImg;
    Bitmap bitmap;
    long selectedPosition;
    WallpaperManager wallpaperManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Wallpaper Acticity");
        setContentView(R.layout.activity_wallpaper);

        wallImg = (ImageView) findViewById(R.id.wallpaper_img);

        Intent intent = getIntent();
        final String Image = intent.getStringExtra("Images");
        selectedPosition = intent.getLongExtra("key", 0);

        Glide.with(getApplicationContext()).load(Image).into(wallImg);

        wallImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics metrics=new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int height=metrics.heightPixels;
                int width=metrics.widthPixels;

                Glide.with(WallpaperActivity.this).load(Image).asBitmap().override(width,height).centerCrop().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        try {
                            WallpaperManager.getInstance(getApplicationContext()).setBitmap(resource);
                            Toast.makeText(WallpaperActivity.this,"Wallpaper Set",Toast.LENGTH_SHORT).show();

                        } catch (IOException ie) {
                            ie.printStackTrace();
                        }

                    }
                });

            }
        });
    }
}



