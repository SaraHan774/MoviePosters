package com.gahee.movieposters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.gahee.movieposters.model.PopularMovie;

import static com.gahee.movieposters.utils.Constants.PARCEL_KEY;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        PopularMovie popularMovie  = intent.getParcelableExtra(PARCEL_KEY);

        TextView textView = findViewById(R.id.detail_movie_title);
        textView.setText(popularMovie.getTitle());
    }
}
