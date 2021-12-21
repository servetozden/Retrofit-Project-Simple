package com.example.retrofitproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitproject.databinding.ActivityMainBinding;
import com.example.retrofitproject.model.MultipleResourceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Call<MultipleResourceModel> call = APIClient.getInstance().getMyApi().getListResources();
        call.enqueue(new Callback<MultipleResourceModel>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<MultipleResourceModel> call, Response<MultipleResourceModel> response) {
                MultipleResourceModel multipleResourceList = response.body();

                List<MultipleResourceModel.Datum> datumList = multipleResourceList.data;
                String[] dataItem = new String[datumList.size()];

                for (int i = 0; i< datumList.size(); i++){

                    dataItem[i] = datumList.get(i).name;

                }

                binding.listViewHeroes.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, dataItem));

            }

            @Override
            public void onFailure(Call<MultipleResourceModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    }