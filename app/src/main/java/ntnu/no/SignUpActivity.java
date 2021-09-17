package ntnu.no;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

//    private final String url = "http://10.22.190.200:8080/api/";
    private final String url = "http://192.168.0.120:8080/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        Button signUpButton = findViewById(R.id.signUpBtn);

            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView username = findViewById(R.id.usernameSingUp);
                    TextView password = findViewById(R.id.passwordSignUp);
                    signUp(username.getText().toString(), password.getText().toString());
                }
            });
        }

    private void signUp(String username, String password){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                url + "auth/create?uid=" + username + "&pwd=" + password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Signed up successfully");
                        System.out.println(response);
                        login(username, password);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Sign up failed");
                System.out.println(error);
                error.getMessage();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("uid", username);
                params.put("pwd", password);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        requestQueue.add(request);
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
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Login failed");
                System.out.println(error.toString());
            }
        });
        requestQueue.add(request);
    }
}