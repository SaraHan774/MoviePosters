package com.gahee.movieposters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gahee.movieposters.data.database.LikedMovie;
import com.gahee.movieposters.data.database.MyRoomViewModel;
import com.gahee.movieposters.data.remote.RemoteViewModel;
import com.gahee.movieposters.model.PopularMovie;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.gahee.movieposters.utils.Constants.PARCEL_KEY;
import static com.gahee.movieposters.utils.Constants.POSTER_BASE_URL;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private ImageView detailToolbarImageView;
    private PopularMovie popularMovie;
    private ViewPager viewPager;
    private TrailersPagerAdapter trailersPagerAdapter;
    private MyRoomViewModel myRoomViewModel;

    private ImageButton likeButton;
    private ImageButton addCommentButton;
    private EditText commentEditText;
    private boolean isLiked = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        popularMovie  = intent.getParcelableExtra(PARCEL_KEY);

        likeButton = findViewById(R.id.detail_like_imagebtn);
        addCommentButton = findViewById(R.id.detail_myreview_imagebtn);
        commentEditText = new EditText(this);

        myRoomViewModel = ViewModelProviders.of(this).get(MyRoomViewModel.class);
        myRoomViewModel.getLikeMoviesLiveDataFromRepo().observe(this, likedMovies -> {
            Log.d(TAG, "onChanged: liked movies " + likedMovies);
            isLiked = checkIfLikedMovie(popularMovie, likedMovies);
            setUpLikedButtonStatus();
        });

        setUpCollapsingToolbar();
        setUpToolbarImage();
        setUpDetailMovieInfo();
        onClickImageButtons();

        RemoteViewModel remoteViewModel = ViewModelProviders.of(this).get(RemoteViewModel.class);

        remoteViewModel.fetchTrailersFromRepo(String.valueOf(popularMovie.getMovieId()));
        remoteViewModel.getTrailerLiveDataFromRepo().observe(this, trailerResponse -> {
            viewPager = findViewById(R.id.trailer_viewpager);
            //send video key to view holder
            trailersPagerAdapter = new TrailersPagerAdapter(DetailActivity.this, trailerResponse.getTrailers());
            viewPager.setAdapter(trailersPagerAdapter);
            viewPager.setPageMargin(32);
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

    private void onClickImageButtons(){

        likeButton.setOnClickListener(view -> {
            if(!isLiked){
                insertMovieOnClick(popularMovie, view);
            }else {
                deleteMovieOnClick(popularMovie.getMovieId(), view);
            }
        });

        addCommentButton.setOnClickListener(view -> {
            if(!isLiked){
                Toast toast = Toast.makeText(this, getString(R.string.save_first), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }else{
                showAddCommentDialog(this, view);
            }
        });

        ImageButton shareButton = findViewById(R.id.detail_share_imagebtn);
        shareButton.setOnClickListener(view -> {

        });
    }

    private void setUpLikedButtonStatus(){
        if(isLiked){
            likeButton.setImageDrawable(getDrawable(R.drawable.ic_thumb_up_white_48dp));
        }else{
            likeButton.setImageDrawable(getDrawable(R.drawable.ic_thumb_up_outlined_48dp));
        }
    }

    private void insertMovieOnClick(PopularMovie popularMovie, View view){
        LikedMovie likedMovie = new LikedMovie(
                popularMovie.getMovieId(),
                popularMovie.getTitle(),
                popularMovie.getOverview(),
                popularMovie.getPosterPath(),
                popularMovie.getReleaseDate(),
                popularMovie.getVoteAverage()
        );
        myRoomViewModel.insertLikedMovieViaViewModel(likedMovie);

        likeButton.setImageDrawable(getDrawable(R.drawable.ic_thumb_up_white_48dp));
        Snackbar.make(view, getString(R.string.saved_to_db), Snackbar.LENGTH_LONG).show();
    }

    private void deleteMovieOnClick(int movieId, View view){
        myRoomViewModel.deleteLikedMovieByIdViaViewModel(movieId);

        likeButton.setImageDrawable(getDrawable(R.drawable.ic_thumb_up_outlined_48dp));
        Snackbar.make(view, getString(R.string.removed_from_db), Snackbar.LENGTH_LONG).show();
    }

    private boolean checkIfLikedMovie(PopularMovie popularMovie, List<LikedMovie> likedMovieList) {
        if (myRoomViewModel.getLikeMoviesLiveDataFromRepo() != null) {
            if (likedMovieList != null) {
                for (LikedMovie likedMovie : likedMovieList) {
                    Log.d(TAG, "checkIfLikedMovie: " + likedMovie.getMovieId());
                    if (likedMovie.getMovieId() == popularMovie.getMovieId()) {
                        Log.d(TAG, "checkIfLikedMovie: " + popularMovie.getMovieId() + " is in the database");
                        return true;
                    }
                }
            }
        }
        Log.d(TAG, "checkIfLikedMovie: " + popularMovie.getMovieId() + " not in the database");
        return false;
    }


    private void showAddCommentDialog(Context context, View view) {
        if(commentEditText.getParent() != null){
            ((ViewGroup)commentEditText.getParent()).removeView(commentEditText);
        }
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(getString(R.string.write_comment_title))
                .setMessage(getString(R.string.comment_message))
                .setView(commentEditText)
                .setPositiveButton(getString(R.string.add_comment_btn_text), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String comment = commentEditText.getText().toString();
                        myRoomViewModel.updateCommentByMovieIdViaViewModel(popularMovie.getMovieId(), comment);
                        Snackbar snackbar = Snackbar.make(view, getString(R.string.saved_comment), Snackbar.LENGTH_SHORT);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            snackbar.setBackgroundTint(getColor(R.color.movieGreen));
                            snackbar.setTextColor(getColor(R.color.colorBlack));
                        }
                        snackbar.show();
                    }
                })
                .setNegativeButton(getString(android.R.string.cancel), (dialogInterface, i) -> {
                    Snackbar snackbar = Snackbar.make(view, getString(R.string.canceled), Snackbar.LENGTH_SHORT);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        snackbar.setBackgroundTint(getColor(R.color.movieAccent));
                        snackbar.setTextColor(getColor(R.color.colorBlack));
                    }
                    snackbar.show();
                })
                .create();

        dialog.show();
    }
}
