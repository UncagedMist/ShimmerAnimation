package kk.techbytecare.shimmeranimation.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kk.techbytecare.shimmeranimation.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView thumbnail;
    public TextView title,url;

    public MyViewHolder(View itemView) {
        super(itemView);

        thumbnail = itemView.findViewById(R.id.thumbnail);
        title = itemView.findViewById(R.id.title);
        url = itemView.findViewById(R.id.url);
    }
}
