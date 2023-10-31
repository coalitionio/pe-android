package com.phonglt.pe.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.phonglt.pe.R;
import com.phonglt.pe.dto.X;
import com.phonglt.pe.service.ApiClient;
import com.phonglt.pe.service.XService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<X> items;

    @Override
    public int getCount() {
        return items.size();
    }
    public XAdapter(Context context, int layout, List<X> items) {
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
        TextView txtName = (TextView) view.findViewById(R.id.stu_name);
        TextView txtDob = (TextView) view.findViewById(R.id.dob);
        TextView txtGender = (TextView) view.findViewById(R.id.stu_gender);
        TextView txtAddress =(TextView) view.findViewById(R.id.stu_adress);
        TextView txtMajor =(TextView) view.findViewById(R.id.stu_subject);
        Button btnDelete  = (Button) view.findViewById(R.id.btnDelete);
        Button btnEdit  = (Button) view.findViewById(R.id.btnEdit);
        btnDelete.setOnClickListener(v -> {
            int clickedPosition = position;
            showDeleteConfirmationDialog(v.getContext(), clickedPosition);
        });
//        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        X item = items.get(position);
        txtName.setText(item.getName());
        txtGender.setText(item.getGender());
//        txtDob.setText(LocalDateTime.ofEpochSecond(item.getDate()/1000, 0, ZoneOffset.UTC)
//                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        txtDob.setText(item.getDate());
        txtAddress.setText(item.getAddress());
        if(item.getY() != null ){
            txtMajor.setText(item.getY().getName());
        }
//        String imageUrl = "https://images.unsplash.com/photo-1697197850355-c70e8ea18394?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY5ODYwMDE2OQ&ixlib=rb-4.0.3&q=80&w=1080";
//        Picasso.get().load(imageUrl).into(imageView);
//        imageView.setImageURI(Uri.parse("https://source.unsplash.com/random"));
        return view;
    }
    private void showDeleteConfirmationDialog(Context context, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to delete this item?");

        builder.setPositiveButton("OK", (dialog, which) -> {
            // Perform the delete action
            deleteItem(position);
        });

        builder.setNegativeButton("Cancel",  (dialog, which) -> {
            dialog.dismiss(); // Dismiss the dialog if Cancel is clicked
        });

        builder.create().show();
    }

    private void deleteItem(int position) {
        // Perform the actual deletion of the item at the specified position
        XService xService = ApiClient.xService();
        Call<X> call = xService.delete(items.get(position).getId());

        call.enqueue(new Callback<X>() {
            @Override
            public void onResponse(Call<X> call, Response<X> response) {
                if (response.isSuccessful() && response.body() != null) {
                    X result = response.body();
                    items.remove(position);
                    notifyDataSetChanged();
                } else {
                }
            }

            @Override
            public void onFailure(Call<X> call, Throwable t) {
                // Handle the API call failure
            }
        });
        // Remove the item from your data source and notify the adapter of the change

    }
}
