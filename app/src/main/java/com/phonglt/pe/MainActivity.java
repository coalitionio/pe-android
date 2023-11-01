package com.phonglt.pe;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.phonglt.pe.adapter.XAdapter;
import com.phonglt.pe.dto.X;
import com.phonglt.pe.dto.Y;
import com.phonglt.pe.service.ApiClient;
import com.phonglt.pe.service.XService;
import com.phonglt.pe.service.YService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView lvMain;
    XAdapter adapter;
    List<X> items;
    ArrayList<Y> items2;

    Button btnAdd1;
    Button btnAdd2;
    Button btn3;
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                        LoadData();
                        adapter.notifyDataSetChanged();
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        // There are no request codes
                    }
                }
            });
    private void mapping(){
        btnAdd1 = (Button) findViewById(R.id.btnAdd1);
        btnAdd2 = (Button) findViewById(R.id.btnAdd2);
        btn3 = (Button) findViewById(R.id.btn3);
        lvMain = (ListView) findViewById(R.id.lvMain);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        LoadData();
        btnAdd1.setOnClickListener(v -> {
            Intent intent = new Intent(this, ModifySecondActivity.class);
            someActivityResultLauncher.launch(intent);
        });
        btnAdd2.setOnClickListener(v -> {
            Intent intent = new Intent(this, ModifyFirstActivity.class);
            intent.putExtra("spinner",items2);
            someActivityResultLauncher.launch(intent);
        });
        btn3.setOnClickListener(v ->{
            Intent intent = new Intent(this, SubActivity.class);
            intent.putExtra("subItems",items2);
            someActivityResultLauncher.launch(intent);
        });
    }
    private void LoadData(){

        YService yService = ApiClient.yService();
        Call<Y[]> call = yService.getAll();
        call.enqueue(new Callback<Y[]>() {
            @Override
            public void onResponse(Call<Y[]> call, Response<Y[]> response) {
                System.out.println(response.code());
                Y[] data = response.body();
                items2 = new ArrayList<>();
                for (int i = 0; i < data.length; ++ i) {
                    items2.add(data[i]);
                }
                XService xService = ApiClient.xService();
                Call<X[]> call2 = xService.getAll();
                call2.enqueue(new Callback<X[]>() {
                    @Override
                    public void onResponse(Call<X[]> call, Response<X[]> response) {
                        System.out.println(response.code());
                        X[] data = response.body();
                        items = new ArrayList<>();
                        for (int i = 0; i < data.length; ++ i) {
                            items.add(data[i]);
                        }
                        items.stream().forEach(i -> {
                            Y y = items2.stream().filter(i2 -> i2.getId()
                                    .equals(i.getIdNganh()))
                                    .findAny()
                                    .orElse(null);
                            if (y != null){
                                i.setY(y);
                            }
                        });
                        adapter = new XAdapter(MainActivity.this, R.layout.item_view, items);
                        lvMain.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(Call<X[]> call, Throwable t) {
                    }
                });
            }
            @Override
            public void onFailure(Call<Y[]> call, Throwable t) {

            }
        });

    }
}