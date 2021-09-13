package ntnu.no;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {

//    private final String url = "http://10.22.190.200:8080/api/";
    private final String url = "http://192.168.0.120:8080/api/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

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
                TextView username = findViewById(R.id.username);
                TextView password = findViewById(R.id.password);
                login(username.getText().toString(), password.getText().toString());
            }
        });
    }

    // switch to sign up page
    private void signUpSwitch() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void login(String username, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET,
                url + "auth/login?uid=" + username + "&pwd=" + password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("LOGIN SUCCESS + " + response);
                        User user = User.getInstance();
                        user.setUsername(username);
                        user.setToken(response);
                        itemPageSwitch();
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Login failed");
            }
        });
        requestQueue.add(request);
    }

    // switch to item page
    private void itemPageSwitch() {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);
    }
}