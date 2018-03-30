package com.example.user.tn_sqliterecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomHolder extends RecyclerView.ViewHolder {
      public ImageView imageView;
      public TextView textView;

      public CustomHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image_view);
            textView = (TextView)view.findViewById(R.id.text_name);
      }
}
