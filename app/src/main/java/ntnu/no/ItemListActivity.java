package ntnu.no;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        TextView toolbarText = findViewById(R.id.toolBarText);
        toolbarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPage();
            }
        });
    }

    private void loginPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}