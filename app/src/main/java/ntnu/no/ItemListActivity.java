package ntnu.no;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.stream.Stream;

public class ItemListActivity extends AppCompatActivity {
    private ArrayList<Item> itemList;
    private RecyclerView recyclerView;

    private final String url = "http://10.22.190.200:8080/api/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        recyclerView = findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();

        setItems();
        setAdapter();

        TextView toolbarText = findViewById(R.id.toolBarText);
        toolbarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPage();
            }
        });
    }

    private void setItems() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

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

                                JSONArray photo = jsonObject.getJSONArray("photos");
                                String subpath = photo.getJSONObject(0).getString("subpath");

                                String imgUrl = url + "photo/" + subpath;
                                String title = jsonObject.getString("title");
                                int price = jsonObject.getInt("price");
                                String description = jsonObject.getString("description");

                                itemList.add(new Item(imgUrl, title, Integer.toString(price), description));
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



//        itemList.add(new Item("https://i.picsum.photos/id/18/100/100.jpg?hmac=OedHV-WC1S7y_AKkQXqUTjOTdwumY5dWeO53bO0ox2o",
//                "Item1", "100", "desc1desc1desc1desc1desc1desc1"));
//        itemList.add(new Item("https://i.picsum.photos/id/896/100/100.jpg?hmac=_jToGRL7iP8B-HahUeCVhU9FEqOfjbVBNFX_09VnTFM",
//                "Item2", "200", "desc2desc2desc2desc2desc2desc2"));
//        itemList.add(new Item("https://i.picsum.photos/id/147/100/100.jpg?hmac=ZKzrDm4MliOp9keOjaqpf9qNdBrrTTu-_C5mfgA6uMk",
//                "Item3", "300", "desc3desc3desc3desc3desc3desc3"));
//        itemList.add(new Item("https://i.picsum.photos/id/189/100/100.jpg?hmac=A0WAuH9vU1cWAapStacGxD5ED_MgpcMXNfpCMAXFG9U",
//                "Item4", "400", "desc4desc4desc3desc4desc4desc4"));

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