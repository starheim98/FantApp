package ntnu.no.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import ntnu.no.R;
import ntnu.no.RecyclerAdapter;
import ntnu.no.ViewPagerAdapter;
import ntnu.no.model.Photo;


import android.os.Bundle;

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {
    private ArrayList<Photo> photos;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);


        recyclerView = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(photos);
        recyclerView.setAdapter(adapter);


    }


}