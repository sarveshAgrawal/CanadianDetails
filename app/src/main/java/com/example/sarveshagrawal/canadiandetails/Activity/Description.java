package com.example.sarveshagrawal.canadiandetails.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sarveshagrawal.canadiandetails.R;

public class Description extends AppCompatActivity {

    TextView title,description;
    ImageView title_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        title = (TextView)findViewById(R.id.title);
        description = (TextView)findViewById(R.id.description);
        title_image = (ImageView)findViewById(R.id.title_image);

        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
        Glide.with(this).load(getIntent().getStringExtra("image")).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(title_image);


    }
}
