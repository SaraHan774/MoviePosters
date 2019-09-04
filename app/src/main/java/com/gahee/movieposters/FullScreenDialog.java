package com.gahee.movieposters;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.gahee.movieposters.model.Review;

public class FullScreenDialog extends DialogFragment {

    public static final String TAG = "FullScreenDialog";
    private Review review;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        Bundle bundle = getArguments();
        review = bundle.getParcelable("review");

        Log.d(TAG, "onCreate: " + review);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_review_full_screen_dialog, container, false);
        Log.d(TAG, "onCreateView: " + view);

        TextView tvAuthor = view.findViewById(R.id.dialog_review_author_textview);
        TextView tvContent = view.findViewById(R.id.dialog_review_content_textview);

        tvAuthor.setText(review.getAuthor());
        tvContent.setText(review.getContent());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        if(dialog != null){
            dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.round_corner_rect_dark));
        }
    }
}
