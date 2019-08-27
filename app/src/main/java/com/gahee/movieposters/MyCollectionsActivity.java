package com.gahee.movieposters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.gahee.movieposters.data.database.MyRoomViewModel;

public class MyCollectionsActivity extends AppCompatActivity {

    private MyRoomViewModel myRoomViewModel;
    private MyCollectionsAdapter myCollectionsAdapter;
    private RecyclerView myCollectionsRecyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collections);

        setUpToolbar();
        View emptyCollectionView = findViewById(R.id.my_collections_empty_container);

        myRoomViewModel = ViewModelProviders.of(this).get(MyRoomViewModel.class);
        myRoomViewModel.getLikeMoviesLiveDataFromRepo().observe(this, likedMovieList -> {
            if(likedMovieList.size() == 0){
                emptyCollectionView.setVisibility(View.VISIBLE);
            }else{
                emptyCollectionView.setVisibility(View.GONE);
                setUpRecyclerView();
                myCollectionsAdapter = new MyCollectionsAdapter(MyCollectionsActivity.this, likedMovieList);
                myCollectionsRecyclerView.setAdapter(myCollectionsAdapter);
            }
        });
    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.my_collections_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void setUpRecyclerView(){
        myCollectionsRecyclerView = findViewById(R.id.my_collections_recyclerview);
        myCollectionsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myCollectionsRecyclerView.setLayoutManager(layoutManager);
    }
}
