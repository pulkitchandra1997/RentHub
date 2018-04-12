package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConfirmPassword extends Activity implements View.OnClickListener{
    EditText oldpassword,newpassword;
    TextView pwdicon,pwdicon1;
    Button change;
    String newpwd,oldpwd,email;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        oldpassword=findViewById(R.id.oldpassword);
        newpassword=findViewById(R.id.newpassword);
        pwdicon=findViewById(R.id.pwdicon);
        pwdicon1=findViewById(R.id.pwdicon1);
        change=findViewById(R.id.change);
        change.setOnClickListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        change.setTypeface(font);
        pwdicon.setTypeface(font);
        pwdicon1.setTypeface(font);
        progress=findViewById(R.id.login_progress);
        change.setOnClickListener(this);
        Intent intent=getIntent();
        if(intent!=null){
            email=intent.getStringExtra("email");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.change) {
         oldpwd = oldpassword.getText().toString().trim();
         newpwd = newpassword.getText().toString().trim();
            if (TextUtils.isEmpty(oldpwd) || TextUtils.isEmpty(newpwd)) {
                if (TextUtils.isEmpty(oldpwd)) {
                    oldpassword.setError("Enter Your Password");
                    oldpassword.requestFocus();
                }
                if (TextUtils.isEmpty(newpwd)) {
                    newpassword.setError("Confirm Your password");
                    newpassword.requestFocus();
                }
            } else

                if(!Validation.isValidPassword(oldpwd)|| !Validation.isValidPassword(newpwd)||!oldpwd.equals(newpwd)){
                    if(!Validation.isValidPassword(oldpwd)){
                        oldpassword.setError("Password must be atleast 8 characters");
                        oldpassword.requestFocus();
                    }
                    if(!oldpwd.equals(newpwd)){
                        newpassword.setError("Re-Type Correct password");
                        newpassword.requestFocus();
                    }
                }
                else {
                    updatepassword();
                }
            }
        }

    private void updatepassword() {
        showProgress(true);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_CHANGE_PASSWORD, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                if(response.equalsIgnoreCase("1")){
                    AlertDialog alertDialog = new AlertDialog.Builder(ConfirmPassword.this).create();
                    alertDialog.setMessage("Password changed. Opening Login activity");
                    alertDialog.setIcon(R.mipmap.ic_launcher_round);
                    alertDialog.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    alertDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ConfirmPassword.this, LoginActivity.class);
                            intent.putExtra("email",email);
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                ActivityOptions options = ActivityOptions.makeCustomAnimation(ConfirmPassword.this, R.anim.fade_in, R.anim.fade_out);
                                startActivity(intent, options.toBundle());
                            } else {
                                startActivity(intent);
                            }
                        }
                    }, 2500);
            }
                else {
                    if(response.equalsIgnoreCase("error")){
                        AlertDialog builder = new AlertDialog.Builder(ConfirmPassword.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Connection error! Retry");
                        builder.show();
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
                    AlertDialog alertDialog = new AlertDialog.Builder(ConfirmPassword.this).create();
                    alertDialog.setMessage("No Internet Connection");
                    alertDialog.setIcon(R.mipmap.ic_launcher_round);
                    alertDialog.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    alertDialog.show();
                }
                else {
                    AlertDialog builder = new AlertDialog.Builder(ConfirmPassword.this).create();
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
                params.put("email", email);
                params.put("password", newpwd);
                return params;
            }
        };
        MySingleton.getInstance(ConfirmPassword.this).addToRequestQueue(stringRequest);
    }


    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            progress.setVisibility(show ? View.VISIBLE : View.GONE);
            progress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progress.setVisibility(show ? View.VISIBLE : View.GONE);

        }
    }
}
