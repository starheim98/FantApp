package ntnu.no;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signUpLink = findViewById(R.id.signUpLink);
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpSwitch();
            }
        });

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemPageSwitch();
            }
        });
    }
    // switch to sign up page
    private void signUpSwitch() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    // switch to item page
    private void itemPageSwitch() {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);
    }
}