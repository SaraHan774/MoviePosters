package com.gahee.movieposters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gahee.movieposters.model.PopularMovie;

import java.util.ArrayList;
import java.util.List;

import static com.gahee.movieposters.utils.Constants.MAIN_MOVIE_POSTER_CORNER_RADIUS;
import static com.gahee.movieposters.utils.Constants.PARCEL_KEY;
import static com.gahee.movieposters.utils.Constants.POSTER_BASE_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<PopularMovie> popularMovies;
    private Context context;

    public MovieAdapter(Context context, List<PopularMovie> popularMovies){
        this.context = context;
        this.popularMovies = popularMovies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_view_holder, parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final PopularMovie popularMovie = popularMovies.get(position);
        String title = popularMovie.getTitle();
        String releaseDate = popularMovie.getReleaseDate();
        float voteAverage = popularMovie.getVoteAverage();
        String overView = popularMovie.getOverview();
        String posterPath = popularMovie.getPosterPath();

        //movie Id needs to be extracted somewhere else

        holder.tvMovieTitle.setText(title);
        holder.tvMovieReleaseDate.setText(releaseDate);
        holder.tvMovieVoteAverage.setText(String.valueOf(voteAverage));
        holder.tvMovieOverView.setText(overView);

        Glide.with(context)
                .load(POSTER_BASE_URL + posterPath)
                .transform(new RoundedCorners(MAIN_MOVIE_POSTER_CORNER_RADIUS))
                .placeholder(R.drawable.scrim_gradient_updown)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageViewPoster);

        holder.checkoutDetailBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(PARCEL_KEY, popularMovie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularMovies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPoster;
        ConstraintLayout checkoutDetailBox;
        TextView tvMovieTitle;
        TextView tvMovieReleaseDate;
        TextView tvMovieVoteAverage;
        TextView tvMovieOverView;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.main_poster_imageview);
            checkoutDetailBox = itemView.findViewById(R.id.checkout_detail_box);
            tvMovieTitle = itemView.findViewById(R.id.main_movie_title);
            tvMovieReleaseDate = itemView.findViewById(R.id.main_movie_release_date);
            tvMovieVoteAverage = itemView.findViewById(R.id.main_movie_vote_average);
            tvMovieOverView = itemView.findViewById(R.id.main_movie_overview);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
