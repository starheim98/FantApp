package ntnu.no.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import ntnu.no.R;
import ntnu.no.model.User;
import ntnu.no.model.UserObserver;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddItemActivity extends AppCompatActivity implements UserObserver {

    private LinearLayout imageContainer;
    static final String FILEPROVIDER = "ntnu.no.fileprovider";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private List<File> photoFiles;
    private File currentPhoto;
    private TextView username;



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

        findViewById(R.id.addImageBtn).setOnClickListener(view -> onCameraClick());
        findViewById(R.id.addItemBtn).setOnClickListener(view -> addItem());
        findViewById(R.id.cancelBtn).setOnClickListener(view -> finish());
    }

    private void addItem() {


        String title = findViewById(R.id.addItemTitle).toString();
        String price = findViewById(R.id.addItemPrice).toString();
        String desc = findViewById(R.id.addItemDesc).toString();




    }

    private void logout() {
        User.getInstance().clearAll();
        finish();
    }

    public void onCameraClick() {
        System.out.println("test");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            currentPhoto = createImageFile();
            if (currentPhoto != null) {
                Uri photoURI = FileProvider.getUriForFile(this, FILEPROVIDER, currentPhoto);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("wtfreak");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                if(requestCode == REQUEST_IMAGE_CAPTURE) {
                    photoFiles.add(currentPhoto);
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