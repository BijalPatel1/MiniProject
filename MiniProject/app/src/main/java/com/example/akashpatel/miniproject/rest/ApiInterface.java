package com.example.akashpatel.miniproject.rest;

import com.example.akashpatel.miniproject.Data.Category_List_Model;
import com.example.akashpatel.miniproject.Data.FragmentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("v1/wallpaper_one/wallpaper_one_category_list")
    Call<Category_List_Model> getCategory();

    @GET("/v1/wallpaper_one/wallpaper_one_post_list?category_id=4")
    Call <List<FragmentModel>> getdetail(@Query("category_id")String id);
}
