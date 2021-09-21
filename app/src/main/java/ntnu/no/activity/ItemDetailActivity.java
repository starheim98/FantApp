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

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {
    private Item item;
    private ArrayList<Photo> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        Intent intent = getIntent();
        item = intent.getParcelableExtra("Selected Item");
        photos = new ArrayList<>();
        photos.addAll(item.getPhotos());

        // TODO: fix viewpager
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(photos);
        viewPager.setAdapter(adapter);

    }

}