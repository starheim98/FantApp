package ntnu.no;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ntnu.no.model.Item;
import ntnu.no.model.Photo;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPageViewHolder> {


    //    private final String url = "http://10.22.190.200:8080/api/";
    private final String url = "http://192.168.0.120:8080/api/";
    private final String imageUrl = url + "fant/photo/";

    private ArrayList<Photo> photos;

    public ViewPagerAdapter(ArrayList<Photo> photos){
        this.photos = photos;
    }

    public class ViewPageViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ViewPageViewHolder(final View view){
            super(view);
            image = view.findViewById(R.id.itemDetailImage);
        }
    }

    @NonNull
    @Override
    public ViewPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_image, parent, false);
        return new ViewPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPageViewHolder holder, int position) {
        if(photos.size() > 0){
            Photo currentImage = photos.get(position);
            String subPath = currentImage.getSubPath();

            Picasso.get()
                    .load(imageUrl + subPath)
                    .fit()
                    .centerCrop()
                    .into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }






}
