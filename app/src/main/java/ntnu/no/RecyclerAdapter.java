package ntnu.no;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

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
//        holder.image.setImageResource(itemList.get(position).getImage());
        holder.title.setText(itemList.get(position).getTitle());
        holder.price.setText(itemList.get(position).getPrice());
        holder.description.setText(itemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
