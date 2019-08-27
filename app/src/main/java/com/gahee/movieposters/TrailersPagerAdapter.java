package com.gahee.movieposters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gahee.movieposters.model.Trailer;
import com.gahee.movieposters.utils.StringUtils;

import java.util.List;

public class TrailersPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Trailer> trailerList;
    private static final String TAG = "TrailersPagerAdapter";
    public TrailersPagerAdapter(Context context, List<Trailer> trailers){
        Log.d(TAG, "Pager Adapter Constructor running ... " + trailers);
        this.context = context;
        this.trailerList = trailers;
    }

    @Override
    public int getCount() {
        return trailerList != null ? trailerList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.trailer_slider, container, false);
        Log.d(TAG, "instantiate item running ... ");
        Trailer trailer = trailerList.get(position);
        ImageView imageView = view.findViewById(R.id.video_thumbnail_imageview);
        TextView videoName = view.findViewById(R.id.video_name);
        TextView videoSite = view.findViewById(R.id.video_site);
        TextView videoType = view.findViewById(R.id.video_type);
        TextView currentPageNum = view.findViewById(R.id.detail_video_currentpagenum_textview);
        TextView totalPageNum = view.findViewById(R.id.detail_video_totalpagenum_textview);

        if(!trailer.getVideoKey().equals("")) {
            videoName.setText(trailer.getVideoName());
            videoSite.setText(trailer.getVideoSite());
            videoType.setText(trailer.getVideoType());

            Glide.with(context).load(StringUtils.generateYoutubeThumbnailFromId(trailer.getVideoKey()))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.d(TAG, "glide failed " + e.getMessage());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d(TAG, "glide ready " + resource);
                            return false;
                        }
                    })
                    .placeholder(R.drawable.scrim_gradient_updown)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);

            currentPageNum.setText(String.valueOf(position + 1));
            totalPageNum.setText(String.valueOf(trailerList.size()));
            setClickListenerToVideo(view, trailer);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    private void setClickListenerToVideo(View view, Trailer trailer){
        RelativeLayout relativeLayout = view.findViewById(R.id.watch_video_click_area);
        relativeLayout.setOnClickListener(view1 -> {
            String videoId = trailer.getVideoKey();
            String watchUrl = StringUtils.getYoutubeWatchUrlFromIds(videoId);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(watchUrl));
            context.startActivity(intent);
        });
    }
}
