package com.phonglt.pe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.phonglt.pe.dto.X;
import com.phonglt.pe.dto.Y;
import com.phonglt.pe.service.ApiClient;
import com.phonglt.pe.service.XService;
import com.phonglt.pe.service.YService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyFirstActivity extends AppCompatActivity {
    private Button btnCancel;
    private Button btnSave;
    private EditText txtName;

    private  EditText txtDate;
    private EditText txtAddress;
    private EditText txtGender;
    private Spinner spinner;
    private List<Y> spinnerItems;
    private void mapping(){
        txtName = (EditText) findViewById(R.id.txtFirst1);
        txtDate = (EditText) findViewById(R.id.dateFirst2);
        txtGender = (EditText) findViewById(R.id.txtFirst3);
        txtAddress = (EditText) findViewById(R.id.txtFirst4);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnSave = (Button) findViewById(R.id.btnFirstSave);
        btnCancel = (Button) findViewById(R.id.btnFirstCancel);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_first);
        mapping();
        Bundle bundle = getIntent().getExtras();
        spinnerItems  = (List<Y>) bundle.get("spinner");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems.stream().map(i->i.getName()).collect(Collectors.toList()));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        btnCancel.setOnClickListener(v -> finish());
        btnSave.setOnClickListener(v -> {
            String gender = txtGender.getText().toString().trim();
            String date = txtDate.getText().toString().trim();
            String name = txtName.getText().toString().trim();
            String address = txtAddress.getText().toString().trim();
            String selectedSpinnerItem = spinner.getSelectedItem().toString();
            String idNganh = "";
            Y y = spinnerItems.stream().filter(i -> i.getName().equalsIgnoreCase(selectedSpinnerItem)).findFirst().orElse(null);
            if(y!=null){
                idNganh = y.getId();
            }
            if (!gender.matches("(?i)^(male|female)$")) {
                Toast.makeText(ModifyFirstActivity.this, "Gender must be 'male' or 'female'", Toast.LENGTH_SHORT).show();
                return;
            }
            if (name.isEmpty()) {
                Toast.makeText(ModifyFirstActivity.this, "Name cannot empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (address.isEmpty()) {
                Toast.makeText(ModifyFirstActivity.this, "Address cannot empty", Toast.LENGTH_SHORT).show();
                return;
            }



            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false); // This ensures strict date parsing

            try {
                dateFormat.parse(date);
            } catch (ParseException e) {
                Toast.makeText(ModifyFirstActivity.this, "Invalid date format (dd-MM-yyyy)", Toast.LENGTH_SHORT).show();
                return;
            }
            X x = new X();
            x.setName(name);
            x.setDate(date);
            x.setAddress(address);
            x.setGender(gender);
            x.setIdNganh(idNganh);

            create(x);
        });
    }
    private void create(X x){
        XService xService = ApiClient.xService();
        Call<X> call2 = xService.create(x);
        call2.enqueue(new Callback<X>() {
            @Override
            public void onResponse(Call<X> call, Response<X> response) {
                System.out.println(response.code());
                X data = response.body();
                finish();
            }
            @Override
            public void onFailure(Call<X> call, Throwable t) {
            }
        });
    }
}