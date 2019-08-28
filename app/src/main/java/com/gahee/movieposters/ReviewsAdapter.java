package com.gahee.movieposters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.movieposters.model.Review;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private Context context;
    private List<Review> reviewList;

    public ReviewsAdapter(Context context, List<Review> reviewList){
        this.context = context;
        this.reviewList = reviewList;
    }


    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_review_view_holder, parent, false);

        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
            Review review = reviewList.get(position);
            holder.tvReviewAuthor.setText(review.getAuthor());
            holder.tvReviewContent.setText(review.getContent());
            holder.tvReviewContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(review.getReviewUrl()));
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return reviewList != null ? reviewList.size() : 0;
    }

    class ReviewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvReviewAuthor;
        TextView tvReviewContent;
        TextView tvClickForMore;
        TextView tvClickToCollapse;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReviewAuthor = itemView.findViewById(R.id.detail_review_author_textview);
            tvReviewContent = itemView.findViewById(R.id.detail_review_textview);
            tvClickForMore = itemView.findViewById(R.id.review_clickformore_textview);
            tvClickToCollapse = itemView.findViewById(R.id.review_clicktocollapse_textview);
        }
    }
}
