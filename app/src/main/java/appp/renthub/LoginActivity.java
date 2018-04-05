package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity implements View.OnClickListener{
EditText email;
EditText password;
TextView pwdicon,usericon,forgot,signup;
Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        usericon=findViewById(R.id.usericon);
        pwdicon=findViewById(R.id.pwdicon);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        login.setTypeface(font);
        pwdicon.setTypeface(font);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Regular-400.otf" );
        usericon.setTypeface(font2);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(this);
        forgot=findViewById(R.id.forgot);
        forgot.setOnClickListener(this);
    }
/*    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(LoginActivity.this,Welcome.class));
        return;
    }*/
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            final String emailid = email.getText().toString().trim();
            final String pwd = password.getText().toString().trim();
            if (TextUtils.isEmpty(emailid) || TextUtils.isEmpty(pwd)) {
                if (TextUtils.isEmpty(emailid)) {
                    email.setError("Enter email id");
                    email.requestFocus();
                }
                if (TextUtils.isEmpty(pwd)) {
                    password.setError("Enter password");
                    password.requestFocus();
                }
            } else{
                if(!Validation.isValidEmail(emailid)||!Validation.isValidPhone(emailid)){
                    email.setError("Enter Valid Email/Phone no");
                    email.requestFocus();
                }
                else{
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_LOGIN, new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            //receive JSON object
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Snackbar snackbar = Snackbar
                                    .make(getWindow().getDecorView().getRootView(), "Error in sending OTP. Retry!", Snackbar.LENGTH_LONG);
                            View sbView = snackbar.getView();
                            TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(Color.RED);
                            snackbar.show();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError
                        {
                            Map<String, String> params = new HashMap<>();
                            params.put("email", emailid);
                            params.put("password", String.valueOf(pwd));
                            return params;
                        }
                    };
                    MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
                }

        }
        }
        if(v.getId()==R.id.signup){
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.forgot){
            Toast.makeText(this, "FORGOT", Toast.LENGTH_SHORT).show();
        }
    }
}
