package com.gahee.movieposters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.gahee.movieposters.data.RemoteViewModel;
import com.gahee.movieposters.model.PopularResponse;

public class MainActivity extends AppCompatActivity {

    //https://api.themoviedb.org/3/
    // discover/movie?api_key=cba2350c8309476e27a4fdd039071be7&language=en-US&sort_by=popularity.desc&page=1

    public static final String VIEW_MODEL_DEBUG = "view_model_debug";

    private RemoteViewModel remoteViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        remoteViewModel = new RemoteViewModel();
        remoteViewModel.fetchPopularMoviesFromRepo();

        remoteViewModel.getPopularMovieResponseLiveDataFromRepo().observe(this, new Observer<PopularResponse>() {
            @Override
            public void onChanged(PopularResponse popularResponse) {
                Log.d(VIEW_MODEL_DEBUG, "response : " + popularResponse.getResults().get(0).getTitle());
            }
        });
    }
}
