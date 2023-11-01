package com.phonglt.pe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.phonglt.pe.adapter.YAdapter;
import com.phonglt.pe.dto.Y;

import java.util.List;

public class SubActivity extends AppCompatActivity {

    private List<Y> subItems;
    private ListView lvSub;
    private Button btnBack;
    private YAdapter adapter;
    private void mapping(){
        lvSub = (ListView) findViewById(R.id.lvSub);
        btnBack = (Button) findViewById(R.id.btnSubBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        mapping();

        Bundle bundle = getIntent().getExtras();
        subItems  = (List<Y>) bundle.get("subItems");
        adapter = new YAdapter(this, R.layout.item_second_view, subItems);
        lvSub.setAdapter(adapter);
        btnBack.setOnClickListener(v -> finish());
    }
}