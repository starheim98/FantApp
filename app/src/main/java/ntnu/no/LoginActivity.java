package ntnu.no;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                finish();
            }
        });

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView username = findViewById(R.id.username);
                TextView password = findViewById(R.id.password);
                login(username.getText().toString(), password.getText().toString());

                // close annoying keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
                        user.setLoggedIn(true);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Login failed");
                System.out.println(error.toString());
                displayLoginError(error.toString());
            }
        });
        requestQueue.add(request);
    }

    private void displayLoginError(String error){
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }


}