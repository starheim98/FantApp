package ntnu.no;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {
    private ArrayList<Item> itemList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        recyclerView = findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();

        setItemInfo();
        setAdapter();

        TextView toolbarText = findViewById(R.id.toolBarText);
        toolbarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPage();
            }
        });
    }

    private void setItemInfo() {
        itemList.add(new Item("Item1", "100", "desc1desc1desc1desc1desc1desc1" +
                "desc1desc1desc1desc1desc1desc1desc1desc1desc1"));
        itemList.add(new Item("Item2", "200", "desc2desc2desc2desc2desc2desc2" +
                "desc2desc2desc2desc2desc2desc2desc2desc2desc2"));
        itemList.add(new Item("Item3", "300", "desc3desc3desc3desc3desc3desc3" +
                "desc3desc3desc3desc3desc3desc3desc3desc3desc3"));
    }

    private void setAdapter(){
        RecyclerAdapter adapter = new RecyclerAdapter(itemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void loginPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}