package ntnu.no.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ntnu.no.R;
import ntnu.no.RecyclerAdapter;
import ntnu.no.model.Item;
import ntnu.no.model.Observer;
import ntnu.no.model.Photo;
import ntnu.no.model.User;

public class ItemListActivity extends AppCompatActivity implements Observer, RecyclerAdapter.OnItemClickListener{
    private ArrayList<Item> itemList;
    private RecyclerView recyclerView;
    private TextView username;

//    private final String url = "http://10.22.190.200:8080/api/";
    private final String url = "http://10.0.2.2:8080/api/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User.getInstance().observe(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        itemList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);


        username = findViewById(R.id.toolBarText);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPage();
            }
        });

        Button logoutBtn = findViewById(R.id.signOutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogout();
            }
        });

        setAdapter();
    }

    @Override
    public void updateUser(){
        username.setText(User.getInstance().getUsername());
    }


    private void setItems() {
        itemList.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        System.out.println("test2");

        StringRequest request = new StringRequest(Request.Method.GET,
                url + "fant",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                                List<Photo> photoList = new ArrayList<>();
                                JSONArray photos = jsonObject.getJSONArray("photos");
                                for (int j = 0; j < photos.length(); j++){
                                    Photo photo = new Photo(photos.getJSONObject(j).getString("subpath"));
                                    photoList.add(photo);
                                }

                                String title = jsonObject.getString("title");
                                int price = jsonObject.getInt("price");
                                String description = jsonObject.getString("description");

                                itemList.add(new Item(photoList, title, Integer.toString(price), description));
                                recyclerView.getAdapter().notifyDataSetChanged();
                            }
                        } catch (JSONException e){
                            System.out.println(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Get items failed");
            }
        });
        requestQueue.add(request);
        System.out.println(itemList.size());
    }

    private void setAdapter(){
        RecyclerAdapter adapter = new RecyclerAdapter(itemList, this);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void loginPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void onLogout(){
        User.getInstance().setLoggedIn(false);
        findViewById(R.id.addItemBtn).setVisibility(View.INVISIBLE);
        findViewById(R.id.signOutBtn).setVisibility(View.INVISIBLE);
        username.setText("Login");
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPage();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        setItems();
        System.out.println(User.getInstance().isLoggedIn());
        if(User.getInstance().isLoggedIn()){
            username.setText(User.getInstance().getUsername());
            findViewById(R.id.addItemBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.signOutBtn).setVisibility(View.VISIBLE);
            username.setOnClickListener(null);
            System.out.println("logged in");
        } else {
            username.setText("Login");
            System.out.println("logged out");

        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("Selected Item", itemList.get(position));
        startActivity(intent);
    }
}