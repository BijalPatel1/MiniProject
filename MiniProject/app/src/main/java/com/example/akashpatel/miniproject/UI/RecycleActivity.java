package com.example.akashpatel.miniproject.UI;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.akashpatel.miniproject.Adapter.RecycleAdapter;
import com.example.akashpatel.miniproject.Data.CategoryModel;
import com.example.akashpatel.miniproject.Data.Category_List_Model;
import com.example.akashpatel.miniproject.Fragment.ArgumentFragment;
import com.example.akashpatel.miniproject.R;
import com.example.akashpatel.miniproject.rest.ApiClient;
import com.example.akashpatel.miniproject.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecycleActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private List<CategoryModel> list;
    private String TAG = RecycleActivity.class.getSimpleName();
    long selectedPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Recycle Activity");
        setContentView(R.layout.activity_recycle);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Intent intent=getIntent();
        selectedPosition=intent.getLongExtra("key",0);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Category_List_Model> call = apiService.getCategory();

        call.enqueue(new Callback<Category_List_Model>() {
            @Override
            public void onResponse(Call<Category_List_Model> call, Response<Category_List_Model> response) {
                final Category_List_Model data = response.body();
                list = data.getData();

                    RecycleAdapter adapter = new RecycleAdapter(RecycleActivity.this, list,selectedPosition);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(RecycleActivity.this, LinearLayoutManager.HORIZONTAL, false);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);

                    recyclerView.addOnItemTouchListener(new RecycleAdapter.RecyclerTouchListener(RecycleActivity.this, recyclerView, new RecycleAdapter.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            String cat_id=list.get(position).getId();

                            Bundle bundle=new Bundle();
                            bundle.putString("cat_id",cat_id);

                            ArgumentFragment argumentFragment=new ArgumentFragment();
                            argumentFragment.setArguments(bundle);

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,argumentFragment).commit();
                        }

                        @Override
                        public void onLongClick(View view, int position) {

                        }
                    }));
            }

            @Override
            public void onFailure(Call<Category_List_Model> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
