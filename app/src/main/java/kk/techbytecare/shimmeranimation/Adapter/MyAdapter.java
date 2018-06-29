package kk.techbytecare.shimmeranimation.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import kk.techbytecare.shimmeranimation.Model.Model;
import kk.techbytecare.shimmeranimation.R;
import kk.techbytecare.shimmeranimation.ViewHolder.MyViewHolder;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Model> modelList;

    public MyAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(modelList.get(position).getThumbnailUrl())
                .into(holder.thumbnail);

        holder.title.setText(modelList.get(position).getTitle());
        holder.url.setText(modelList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
