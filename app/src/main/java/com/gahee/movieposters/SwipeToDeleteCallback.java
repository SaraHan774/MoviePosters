package com.gahee.movieposters;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.movieposters.data.database.MyRoomViewModel;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private MyCollectionsAdapter myCollectionsAdapter;
    private MyRoomViewModel myRoomViewModel;

    public SwipeToDeleteCallback(MyCollectionsAdapter myCollectionsAdapter, MyRoomViewModel myRoomViewModel){
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.myCollectionsAdapter = myCollectionsAdapter;
        this.myRoomViewModel = myRoomViewModel;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        myCollectionsAdapter.deleteItem(position, myRoomViewModel);
    }

}
