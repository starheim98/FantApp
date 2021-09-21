package ntnu.no;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ntnu.no.model.Photo;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {


    //    private final String url = "http://10.22.190.200:8080/api/";
    private final String url = "http://10.0.2.2:8080/api/";
    private final String imageUrl = url + "fant/photo/";

    private ArrayList<Photo> photos;

    public ViewPagerAdapter(ArrayList<Photo> photos){
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_img, parent, false);
        return new ViewPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        if(photos.size() > 0){
            Photo currentImage = photos.get(position);
            String subPath = currentImage.getSubPath();

            System.out.println(imageUrl + subPath);
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

    public class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewPagerViewHolder(final View view){
            super(view);
            image = view.findViewById(R.id.itemDetailImg);
            System.out.println(image);

        }
    }




}
