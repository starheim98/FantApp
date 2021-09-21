package ntnu.no.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import ntnu.no.R;
import ntnu.no.RecyclerAdapter;
import ntnu.no.ViewPagerAdapter;
import ntnu.no.model.Item;
import ntnu.no.model.Photo;


import android.accounts.AbstractAccountAuthenticator;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {
    private ArrayList<Photo> photos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        Intent intent = getIntent();
        Item item = intent.getParcelableExtra("Selected Item");
        photos = new ArrayList<>();
        photos.addAll(item.getPhotos());

        Button returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setAdapter();
        setItemDetails(item);

    }

    private void setItemDetails(Item item) {
        TextView title = findViewById(R.id.itemDetailTitle);
        TextView price = findViewById(R.id.itemDetailPrice);
        TextView description = findViewById(R.id.itemDetailDesc);

        title.setText(item.getTitle());
        price.setText(item.getPrice());
        description.setText(item.getDescription());
    }

    private void setAdapter() {
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(photos);

        viewPager.setAdapter(adapter);
    }

}