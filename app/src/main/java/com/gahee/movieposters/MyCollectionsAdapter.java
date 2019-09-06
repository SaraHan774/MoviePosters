package com.gahee.movieposters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gahee.movieposters.data.database.LikedMovie;
import com.gahee.movieposters.data.database.MyRoomViewModel;

import java.util.List;

import static com.gahee.movieposters.utils.Constants.POSTER_BASE_URL;

public class MyCollectionsAdapter extends RecyclerView.Adapter<MyCollectionsAdapter.MyCollectionsViewHolder> {

    private Context context;
    private List<LikedMovie> likedMovieList;

    public MyCollectionsAdapter(Context context, List<LikedMovie> likedMovieList){
        this.context = context;
        this.likedMovieList = likedMovieList;
    }

    @NonNull
    @Override
    public MyCollectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_collections_view_holder, parent, false);

        return new MyCollectionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCollectionsViewHolder holder, int position) {
//        holder.trashButton.onTouchEvent() --- swipe actions
        LikedMovie likedMovie = likedMovieList.get(position);
        holder.title.setText(likedMovie.getTitle());
        holder.releaseDate.setText(likedMovie.getReleaseDate());
        holder.voteAverage.setText(String.valueOf(likedMovie.getVoteAverage()));
        holder.comment.setText(likedMovie.getComment());

        Glide.with(context).load(POSTER_BASE_URL + likedMovie.getPosterPath())
                .transform(new RoundedCorners(10))
                .placeholder(R.drawable.scrim_gradient_updown)
                .error(R.drawable.ic_launcher_background)
                .into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return likedMovieList != null ? likedMovieList.size() : 0;
    }

    public void deleteItem(int position, MyRoomViewModel myRoomViewModel){
        LikedMovie likedMovieToBeDeleted = likedMovieList.get(position);
        myRoomViewModel.deleteLikedMovieByIdViaViewModel(likedMovieToBeDeleted.getMovieId());
    }

    class MyCollectionsViewHolder extends RecyclerView.ViewHolder{

        private ImageView poster;
        private TextView title;
        private TextView releaseDate;
        private TextView voteAverage;
        private TextView comment;
        private ImageButton trashButton;

        public MyCollectionsViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.collections_poster_imageview);
            title = itemView.findViewById(R.id.collections_movie_title_textview);
            releaseDate = itemView.findViewById(R.id.collections_releasedate_textview);
            voteAverage = itemView.findViewById(R.id.collections_voteaverage_textview);
            trashButton = itemView.findViewById(R.id.delete_from_collections_imagebtn);
            comment = itemView.findViewById(R.id.collections_myreview_textview);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
