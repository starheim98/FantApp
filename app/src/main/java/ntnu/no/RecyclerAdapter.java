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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    //    private final String url = "http://10.22.190.200:8080/api/";
    private final String url = "http://10.0.2.2:8080/api/";
    private final String imageUrl = url + "fant/photo/";

    private ArrayList<Item> itemList;
    private OnItemClickListener onItemClickListener;


    public RecyclerAdapter(ArrayList<Item> itemList, OnItemClickListener onItemClickListener){
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;
        private TextView title, price, description;

        OnItemClickListener onItemClickListener;

        public MyViewHolder(final View view, OnItemClickListener onItemClickListener){
            super(view);
            image = view.findViewById(R.id.itemImage);
            title = view.findViewById(R.id.itemTitle);
            price = view.findViewById(R.id.itemPrice);
            description = view.findViewById(R.id.itemDesc);
            this.onItemClickListener = onItemClickListener;

            view.setOnClickListener(this);
            System.out.println("IMAGE: " + image);
        }


        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.title.setText(itemList.get(position).getTitle());
        holder.price.setText(itemList.get(position).getPrice() + " kr");
        holder.description.setText(itemList.get(position).getDescription());
        if(itemList.get(position).getPhotos().size() > 0){
            String subPath = itemList.get(position).getPhotos().get(0).getSubPath();
            Picasso.get()
                    .load(imageUrl + subPath)
                    .fit()
                    .centerCrop()
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
