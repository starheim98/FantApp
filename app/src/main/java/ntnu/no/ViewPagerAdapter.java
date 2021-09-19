package ntnu.no;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ntnu.no.model.Photo;

public class ViewPagerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewPageViewHolder> {
    private ArrayList<Photo> photos;

    public ViewPagerAdapter(ArrayList<Photo> photos){
        this.photos = photos;
    }


    public class ViewPagerViewHolder extends RecyclerView.ViewHolder{

        public ViewPagerViewHolder(final View view){
            super(view);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewPageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }




}
