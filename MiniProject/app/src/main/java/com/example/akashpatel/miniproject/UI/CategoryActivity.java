package com.example.akashpatel.miniproject.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.akashpatel.miniproject.Adapter.CustomAdapter;
import com.example.akashpatel.miniproject.Data.CategoryModel;
import com.example.akashpatel.miniproject.Data.Category_List_Model;
import com.example.akashpatel.miniproject.R;
import com.example.akashpatel.miniproject.rest.ApiClient;
import com.example.akashpatel.miniproject.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private String TAG = CategoryActivity.class.getSimpleName();
    GridView grid;
    List<CategoryModel> list;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Category Activity");
        setContentView(R.layout.activity_category);

        grid=(GridView)findViewById(R.id.grdVw);
        showProgDig();
        getCat();
    }

    private void showProgDig() {
        pDialog = new ProgressDialog(CategoryActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void getCat() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Category_List_Model> call = apiService.getCategory();

        call.enqueue(new Callback<Category_List_Model>() {
            @Override
            public void onResponse(Call<Category_List_Model> call, Response<Category_List_Model> response) {

                if (pDialog.isShowing())
                    pDialog.dismiss();

                Category_List_Model data = response.body();
                list=data.getData();
                if ( list!= null) {
                    Log.d(TAG, "Number of data received: " + list.size());

                    final CustomAdapter adapter = new CustomAdapter(CategoryActivity.this, list);

                    grid.setAdapter(adapter);
                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(CategoryActivity.this,RecycleActivity.class);
                            final long selectedPosition=adapter.getItemId(position);
                            intent.putExtra("key",selectedPosition);
                            startActivity(intent);

                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<Category_List_Model> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    }
