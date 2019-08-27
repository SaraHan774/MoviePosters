package com.gahee.movieposters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gahee.movieposters.data.RemoteViewModel;
import com.gahee.movieposters.model.PopularMovie;
import com.gahee.movieposters.model.TrailerResponse;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import static com.gahee.movieposters.utils.Constants.PARCEL_KEY;
import static com.gahee.movieposters.utils.Constants.POSTER_BASE_URL;

public class DetailActivity extends AppCompatActivity {

    private ImageView detailToolbarImageView;
    private PopularMovie popularMovie;
    private ViewPager viewPager;
    private TrailersPagerAdapter trailersPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        popularMovie  = intent.getParcelableExtra(PARCEL_KEY);

        setUpCollapsingToolbar();
        setUpToolbarImage();
        setUpDetailMovieInfo();

        RemoteViewModel remoteViewModel = ViewModelProviders.of(this).get(RemoteViewModel.class);

        remoteViewModel.fetchTrailersFromRepo(String.valueOf(popularMovie.getMovieId()));
        remoteViewModel.getTrailerLiveDataFromRepo().observe(this, new Observer<TrailerResponse>() {
            @Override
            public void onChanged(TrailerResponse trailerResponse) {
                viewPager = findViewById(R.id.trailer_viewpager);
                //send video key to view holder
                trailersPagerAdapter = new TrailersPagerAdapter(DetailActivity.this, trailerResponse.getTrailers());
                viewPager.setAdapter(trailersPagerAdapter);
            }
        });
    }

    private void setUpToolbarImage(){
        detailToolbarImageView = findViewById(R.id.detail_toolbar_imageview);
        Glide.with(this).load(POSTER_BASE_URL + popularMovie.getPosterPath())
                .placeholder(R.drawable.scrim_gradient_updown)
                .error(R.drawable.ic_launcher_background)
                .into(detailToolbarImageView);
    }

    private void setUpDetailMovieInfo(){

        ImageView detailImagePoster = findViewById(R.id.detail_poster_imageview);
        TextView tvTitle = findViewById(R.id.detail_title_textview);
        TextView tvReleaseDate = findViewById(R.id.detail_release_date_textview);
        TextView tvVoteAverage = findViewById(R.id.detail_vote_average_textview);
        RatingBar ratingBar = findViewById(R.id.detail_ratingbar);
        TextView tvOverview = findViewById(R.id.detail_overview_textview);

        tvTitle.setText(popularMovie.getTitle());
        tvReleaseDate.setText(popularMovie.getReleaseDate());
        tvVoteAverage.setText(String.valueOf(popularMovie.getVoteAverage()));

        Glide.with(this).load(POSTER_BASE_URL + popularMovie.getPosterPath())
                .transform(new RoundedCorners(10))
                .placeholder(R.drawable.scrim_gradient_updown)
                .error(R.drawable.ic_launcher_background)
                .into(detailImagePoster);

        ratingBar.setRating(popularMovie.getVoteAverage());
        tvOverview.setText(popularMovie.getOverview());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setUpCollapsingToolbar(){
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.detail_collapsing_toolbar_layout);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);

        collapsingToolbarLayout.setContentScrimColor(Color.BLACK);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}
