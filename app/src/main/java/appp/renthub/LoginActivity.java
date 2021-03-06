package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import static android.os.Build.VERSION_CODES.*;

public class LoginActivity extends Activity implements View.OnClickListener{
    EditText email;
    EditText password;
    TextView pwdicon,usericon,forgot,signup;
    Button login;
    SharedPreferences sp;
    SharedPreferences.Editor se;
    String emailid,pwd;
    ProgressBar login_progress;
    ScrollView login_form;
    LinearLayout mainLayout;

    public void hidekeyboard()
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mainLayout=findViewById(R.id.mainlayout);
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
        forgot=findViewById(R.id.forgotpwd);
        forgot.setOnClickListener(this);
        sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);
        se=sp.edit();

        login_progress=findViewById(R.id.login_progress);
        login_form=findViewById(R.id.login_form);
        Intent intent=getIntent();
        if(intent!=null){
            email.setText(intent.getStringExtra("email"));
        }

    }

        //ON BACK PRESS
        @Override
        public void onBackPressed()
        {
            this.startActivity(new Intent(LoginActivity.this,Welcome.class));
            this.finish();
            return;
        }

        //PROGRESS BAR CODE
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            login_progress.setVisibility(show ? View.VISIBLE : View.GONE);
            login_progress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    login_progress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.setVisibility(show ? View.VISIBLE : View.GONE);

        }
    }

    //ONCLICK METHOD
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            password.clearFocus();
            email.clearFocus();
            emailid = email.getText().toString().trim();
            pwd = password.getText().toString().trim();
            if (TextUtils.isEmpty(emailid) || TextUtils.isEmpty(pwd)) {
                if (TextUtils.isEmpty(pwd)) {
                    password.setError("Enter password");
                    password.requestFocus();
                }
                if (TextUtils.isEmpty(emailid)) {
                    email.setError("Enter email id");
                    email.requestFocus();
                }
            } else{
                if(!Validation.isValidEmail(emailid)&&!Validation.isValidPhone(emailid)){
                    email.setError("Enter Valid Email/Phone no");
                    email.requestFocus();
                }
                else{
                    hidekeyboard();
                    toserver();
                }
            }
        }
        if(v.getId()==R.id.signup){
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
            if (android.os.Build.VERSION.SDK_INT >= JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.forgotpwd)
        {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordOtp.class);
            if (android.os.Build.VERSION.SDK_INT >= JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }


    //SERVER LOGIN CHECK
    private void toserver() {
        showProgress(true);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_LOGIN, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                if (response.toLowerCase().contains("loginerror")) {
                    showProgress(false);
                    if(response.toLowerCase().contains("loginerror0")){
                        AlertDialog builder = new AlertDialog.Builder(LoginActivity.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Incorrect Email/ID or Password");
                        builder.show();
                    }
                    if(response.toLowerCase().contains("loginerror1")){
                        AlertDialog builder = new AlertDialog.Builder(LoginActivity.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Server Error!");
                        builder.show();
                    }
                    if(response.toLowerCase().contains("loginerror2")){
                        AlertDialog builder = new AlertDialog.Builder(LoginActivity.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Internet Error.Check Connection!");
                        builder.show();
                    }
                } else {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        tosharedpreference(jsonObject);
                        Intent intent;
                            PROFILE profile=new PROFILE(jsonObject.getString("email"),jsonObject.getString("name"),jsonObject.getString("phone"),jsonObject.getString("dob"),jsonObject.getString("marriagestatus"),jsonObject.getString("city"),jsonObject.getString("permanentaddress"),jsonObject.getString("pincode"),jsonObject.getString("gender"),jsonObject.getString("password"),jsonObject.getString("verified"),jsonObject.getString("type"),jsonObject.getString("picname"));
                            if(jsonObject.getString("type").equalsIgnoreCase("owner"))
                            intent = new Intent(LoginActivity.this, OwnerProfile.class);
                            else
                                intent = new Intent(LoginActivity.this, UserProfile.class);
                        intent.putExtra("profile", profile);
                        if (android.os.Build.VERSION.SDK_INT >= JELLY_BEAN) {
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this, R.anim.fade_in, R.anim.fade_out);
                            startActivity(intent, options.toBundle());
                        } else {
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                showProgress(false);
                boolean haveConnectedWifi = false;
                boolean haveConnectedMobile = false;
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo[] netInfo = cm.getAllNetworkInfo();
                for (NetworkInfo ni : netInfo) {
                    if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                        if (ni.isConnected())
                            haveConnectedWifi = true;
                    if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                        if (ni.isConnected())
                            haveConnectedMobile = true;
                }
                if( !haveConnectedWifi && !haveConnectedMobile)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                    alertDialog.setMessage("No Internet Connection");
                    alertDialog.setIcon(R.mipmap.ic_launcher_round);
                    alertDialog.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    alertDialog.show();
                }
                else {
                    AlertDialog builder = new AlertDialog.Builder(LoginActivity.this).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Connection error! Retry");
                    builder.show();
                }
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("email",emailid);
                params.put("password",pwd);
                return params;
            }
        };
        MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
    }

    private void tosharedpreference(JSONObject jsonObject) {
        try {
            se.putString("type", jsonObject.getString("type"));
            se.putString("email", jsonObject.getString("email"));
            se.putString("name", jsonObject.getString("name"));
            se.putString("phone", jsonObject.getString("phone"));
            se.putString("dob", jsonObject.getString("dob"));
            se.putString("marriagestatus", jsonObject.getString("marriagestatus"));
            se.putString("city", jsonObject.getString("city"));
            se.putString("permanentaddress", jsonObject.getString("permanentaddress"));
            se.putString("pincode", jsonObject.getString("pincode"));
            se.putString("gender", jsonObject.getString("gender"));
            se.putString("password", jsonObject.getString("password"));
            se.putString("verified",jsonObject.getString("verified"));
            se.putString("picname",jsonObject.getString("picname"));
            se.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
