package ntnu.no.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import ntnu.no.R;
import ntnu.no.model.User;
import ntnu.no.model.UserObserver;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.http.entity.ContentType;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddItemActivity extends AppCompatActivity implements UserObserver {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private LinearLayout imageContainer;
    static final String FILEPROVIDER = "ntnu.no.fileprovider";

    private List<File> photoFiles = new ArrayList<>();
    private List<ImageView> selected = new ArrayList<>();
    private File currentPhoto;
    private TextView username;
    private Button removeSelectedBtn;
    private HashMap<ImageView, File> image_file_map = new HashMap<>();

    Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User.getInstance().observe(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        username = findViewById(R.id.toolBarText);
        username.setText(User.getInstance().getUsername());
        findViewById(R.id.signOutBtn).setVisibility(View.VISIBLE);
        findViewById(R.id.signOutBtn).setOnClickListener(view -> logout());

        imageContainer = findViewById(R.id.imageContainer);
        removeSelectedBtn = findViewById(R.id.removeSelectedBtn);
        removeSelectedBtn.setOnClickListener(view -> removeImages());

        findViewById(R.id.addItemBtn).setOnClickListener(view -> addItem());
        findViewById(R.id.cancelBtn).setOnClickListener(view -> finish());
        findViewById(R.id.addImageBtn).setOnClickListener(view -> onCameraClick());
    }

    private void removeImages() {
        for (ImageView imageView : selected){
            imageContainer.removeView(imageView);
            photoFiles.remove(image_file_map.get(imageView));
            image_file_map.remove(imageView);
        }
        removeSelectedBtn.setEnabled(false);
    }

    public void onCameraClick() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            currentPhoto = createImageFile();
            if (currentPhoto != null) {
                photoURI = FileProvider.getUriForFile(this, FILEPROVIDER, currentPhoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() {
        File result = null;

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {
            result = File.createTempFile(imageFileName,  ".jpg",   storageDir);
            System.out.println("File is " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void addItem() {
        String title = findViewById(R.id.addItemTitle).toString();
        String price = findViewById(R.id.addItemPrice).toString();
        String desc = findViewById(R.id.addItemDesc).toString();

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("title", title);
        builder.addFormDataPart("price", price);
        builder.addFormDataPart("description", desc);

        if(photoFiles.size() > 0){
            for (File file : photoFiles){

            }
        }

        MultipartBody multipartBody = builder.build();
        System.out.println("Multipartbody: " + multipartBody);

        Request request = new Request.Builder()
                .header("Authorization", "Bearer " + User.getInstance().getToken())
                .url("http://10.0.2.2:8080/api/fant/create/")
                .post(multipartBody)
                .build();

        System.out.println("Request: " + request);
        OkHttpClient client = new OkHttpClient.Builder().build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    private void logout() {
        User.getInstance().clearAll();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                if(requestCode == REQUEST_IMAGE_CAPTURE) {
                    System.out.println(currentPhoto);
                    ImageView image = new ImageView(this);
                    image.setImageURI(photoURI);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
                    image.setLayoutParams(layoutParams);
                    image.setOnClickListener(view -> {
                        if (selected.contains(image)){
                            selected.remove(image);
                            image.setPadding(0,0,0,0);
                            if (selected.size() == 0) {
                                removeSelectedBtn.setEnabled(false);
                            }
                        } else {
                            selected.add(image);
                            removeSelectedBtn.setEnabled(true);
                            //TODO: fix border
                            image.setPadding(5, 5, 5, 5);
                            image.setBackgroundColor(0xff0000);
                        }
                    });

                    imageContainer.addView(image);
                    photoFiles.add(currentPhoto);
                    image_file_map.put(image, currentPhoto);
                    currentPhoto = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: useless?
    @Override
    public void updateUser() {
        username.setText(User.getInstance().getUsername());
    }
}