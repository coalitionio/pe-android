package com.phonglt.pe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.phonglt.pe.adapter.XAdapter;
import com.phonglt.pe.dto.X;
import com.phonglt.pe.dto.Y;
import com.phonglt.pe.service.ApiClient;
import com.phonglt.pe.service.XService;
import com.phonglt.pe.service.YService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifySecondActivity extends AppCompatActivity {

    private Button btnCancel;
    private Button btnSave;
    private TextView txtName;
    private void mapping(){
        btnCancel = (Button) findViewById(R.id.btnCancelSubject);
        btnSave = (Button) findViewById(R.id.btnSaveSubject);
        txtName = (TextView) findViewById(R.id.txtSubjectName);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_second);
        mapping();
        btnCancel.setOnClickListener(v -> finish());
        btnSave.setOnClickListener(v -> {
            Y y = new Y();
            y.setName(txtName.getText().toString());
            YService yService = ApiClient.yService();
            Call<Y> call2 = yService.create(y);
            call2.enqueue(new Callback<Y>() {
                @Override
                public void onResponse(Call<Y> call, Response<Y> response) {
                    System.out.println(response.code());
                    Y data = response.body();
                    finish();
                }
                @Override
                public void onFailure(Call<Y> call, Throwable t) {
                }
            });
        });
    }
}