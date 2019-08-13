package com.example.akashpatel.miniproject.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.akashpatel.miniproject.Adapter.FragmentAdapter;
import com.example.akashpatel.miniproject.Adapter.RecycleAdapter;
import com.example.akashpatel.miniproject.Data.CategoryModel;
import com.example.akashpatel.miniproject.Data.Category_List_Model;
import com.example.akashpatel.miniproject.Data.FragmentModel;
import com.example.akashpatel.miniproject.R;
import com.example.akashpatel.miniproject.UI.RecycleActivity;
import com.example.akashpatel.miniproject.UI.WallpaperActivity;
import com.example.akashpatel.miniproject.rest.ApiClient;
import com.example.akashpatel.miniproject.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class ArgumentFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<FragmentModel> fragmentList;
    private final String BASE_URL="http://mapi.trycatchtech.com";
    private String category_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_layout,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.Recycle_fragment);

        final Bundle bundle=this.getArguments();

        category_id=bundle.getString("cat_id");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<FragmentModel>> call = api.getdetail(category_id);

        call.enqueue(new Callback<List<FragmentModel>>() {
            @Override
            public void onResponse(Call<List<FragmentModel>> call, Response<List<FragmentModel>> response) {
                fragmentList=(List<FragmentModel>)response.body();

                final FragmentAdapter fragmentAdapter=new FragmentAdapter(getContext(),fragmentList);
                layoutManager=new GridLayoutManager(getContext(),3);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(fragmentAdapter);

                recyclerView.addOnItemTouchListener(new FragmentAdapter.FragmentTouchListener(getContext(), recyclerView, new FragmentAdapter.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        //Intent intent=new Intent(getContext(),WallpaperActivity.class);
                        //startActivity(intent);

                        String images=fragmentList.get(position).getImages();

                        Intent intent=new Intent(getContext(),WallpaperActivity.class);
                        intent.putExtra("Images",images);
                        final long selectedPosition=fragmentAdapter.getItemId(position);
                        intent.putExtra("key",selectedPosition);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

            @Override
            public void onFailure(Call<List<FragmentModel>> call, Throwable t) {

            }
        });
        return view;
    }
}
