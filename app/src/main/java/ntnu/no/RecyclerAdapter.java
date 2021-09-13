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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<Item> itemList;

    public RecyclerAdapter(ArrayList<Item> itemList){
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView price;
        private TextView description;

        public MyViewHolder(final View view){
            super(view);
            image = view.findViewById(R.id.itemImage);
            title = view.findViewById(R.id.itemTitle);
            price = view.findViewById(R.id.itemPrice);
            description = view.findViewById(R.id.itemDesc);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.title.setText(itemList.get(position).getTitle());
        holder.price.setText(itemList.get(position).getPrice() + " kr");
        holder.description.setText(itemList.get(position).getDescription());
//        Picasso.get()
//                .load(itemList.get(position).getPhotos())
//                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
