package com.phonglt.pe.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.phonglt.pe.R;
import com.phonglt.pe.dto.X;
import com.phonglt.pe.dto.Y;
import com.phonglt.pe.service.ApiClient;
import com.phonglt.pe.service.XService;
import com.phonglt.pe.service.YService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Y> items;

    @Override
    public int getCount() {
        return items.size();
    }
    public YAdapter(Context context, int layout, List<Y> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
    }
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return -1;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        TextView txtName = (TextView) view.findViewById(R.id.major_name);
        Button btnDelete  = (Button) view.findViewById(R.id.btnSecondDelete);
        Button btnEdit  = (Button) view.findViewById(R.id.btnSecondEdit);
        btnDelete.setOnClickListener(v -> {
            int clickedPosition = position;
            showDeleteConfirmationDialog(v.getContext(), clickedPosition);
        });
        Y item = items.get(position);
        txtName.setText(item.getName());
        return view;
    }
    private void showDeleteConfirmationDialog(Context context, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to delete this item?");

        builder.setPositiveButton("OK", (dialog, which) -> {
            deleteItem(position);
        });

        builder.setNegativeButton("Cancel",  (dialog, which) -> {
            dialog.dismiss();
        });

        builder.create().show();
    }

    private void deleteItem(int position) {
        YService yService = ApiClient.yService();
        Call<Y> call = yService.delete(items.get(position).getId());
        call.enqueue(new Callback<Y>() {
            @Override
            public void onResponse(Call<Y> call, Response<Y> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Y result = response.body();
                    items.remove(position);
                    notifyDataSetChanged();
                } else {
                }
            }

            @Override
            public void onFailure(Call<Y> call, Throwable t) {
            }
        });
    }
}