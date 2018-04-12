package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
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

public class SignUpVerify extends Activity implements View.OnClickListener {
    String type=null,emailtext=null,otptext=null;
    int num;
    TextView usericon,otpicon,login,resendotp1,resendotp2;
    Button otp,signup;
    EditText emailinput;
    com.beardedhen.androidbootstrap.AwesomeTextView signuptype;
    com.chaos.view.PinView pinView;
    SharedPreferences sp;
    SharedPreferences.Editor se;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_verify);
        signuptype=findViewById(R.id.signuptype);
        usericon=findViewById(R.id.usericon);
        otpicon=findViewById(R.id.otpicon);
        otp=findViewById(R.id.otp);
        resendotp1=findViewById(R.id.resendotp1);
        resendotp2=findViewById(R.id.resendotp2);
        resendotp2.setOnClickListener(this);
        emailinput=findViewById(R.id.emailinput);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        usericon.setTypeface(font);
        otpicon.setTypeface(font);
        otp.setTypeface(font);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(this);
        signup.setTypeface(font);
        pinView=findViewById(R.id.pinView);
        pinView.setEnabled(false);
        otp.setOnClickListener(this);
        Intent intent=getIntent();
        if(intent!=null){
            type=intent.getStringExtra("type");
            signuptype.setText("as "+type);
        }
        sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);
        se=sp.edit();
    }
    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(SignUpVerify.this,SignUp.class));
        return;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login){
            Intent intent = new Intent(SignUpVerify.this, LoginActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SignUpVerify.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.otp) {
                emailinput.clearFocus();
                emailtext = emailinput.getText().toString().trim();
                if (TextUtils.isEmpty(emailtext)) {
                    emailinput.setError("Enter Email ID");
                    emailinput.requestFocus();
                } else {
                    if (!Validation.isValidEmail(emailtext)) {
                        emailinput.setError("Enter Correct Email");
                        emailinput.requestFocus();
                    }
                    else{
                        sendotp();
                        pinView.setEnabled(true);
                        pinView.requestFocus();
                    }
                }
        }
        if(v.getId()==R.id.signup){
            otptext=pinView.getText().toString().trim();
            pinView.clearFocus();
            if(TextUtils.isEmpty(otptext)){
                pinView.setError("Enter OTP");
                pinView.requestFocus();
            }
            else
                checkotp();
        }
        if(v.getId()==R.id.resendotp2){
            sendotp();
        }
    }
    private void checkotp() {
        if(num!=0 || sp!=null) {
            int num2=sp.getInt("otp_sent",0);
            if(Integer.valueOf(otptext)==num2 || Integer.valueOf(otptext)==num){
                se.remove("otp_sent");
                se.commit();
                Intent intent = new Intent(SignUpVerify.this, SignUpForm.class);
                intent.putExtra("type",type);
                intent.putExtra("email",emailtext);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(SignUpVerify.this, R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
            else{
                pinView.setError("Incorrect OTP");
                pinView.requestFocus();
            }
        }
    }
    private void sendotp() {
        num=OTP_GENERATION.generateRandomNumber();
        se.putInt("otp_sent", num);
        se.commit();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_SEND_OTP, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("1") ){
                if (otp.getVisibility() == View.VISIBLE) {
                    otp.setVisibility(View.GONE);
                    signup.setVisibility(View.VISIBLE);
                    resendotp1.setVisibility(View.VISIBLE);
                    new CountDownTimer(120000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            long min, sec;
                            if (millisUntilFinished > 60000) {
                                min = millisUntilFinished / 60000;
                                sec = (millisUntilFinished - (min * 60000)) / 1000;
                                resendotp1.setText("Resend Otp in " + min + " min " + sec + " sec");
                            } else {
                                resendotp1.setText("Resend Otp in " + millisUntilFinished / 1000 + " sec");
                            }
                        }
                        public void onFinish() {
                            resendotp1.setVisibility(View.GONE);
                            resendotp2.setVisibility(View.VISIBLE);
                        }
                    }.start();
                } else {
                    resendotp1.setVisibility(View.VISIBLE);
                }
            }
            else{
                    if(response.equalsIgnoreCase("0")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpVerify.this);
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Email already Registered.");
                        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SignUpVerify.this, LoginActivity.class);
                                intent.putExtra("email",emailtext);
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                    ActivityOptions options = ActivityOptions.makeCustomAnimation(SignUpVerify.this, R.anim.fade_in, R.anim.fade_out);
                                    startActivity(intent, options.toBundle());
                                } else {
                                    startActivity(intent);
                                }
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                    if(response.equalsIgnoreCase("error")){
                        AlertDialog builder = new AlertDialog.Builder(SignUpVerify.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Error in sending OTP. Retry!");
                        builder.show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
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
                    AlertDialog alertDialog = new AlertDialog.Builder(SignUpVerify.this).create();
                    alertDialog.setMessage("No Internet Connection");
                    alertDialog.setIcon(R.mipmap.ic_launcher_round);
                    alertDialog.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    alertDialog.show();
                }
                else {
                    AlertDialog builder = new AlertDialog.Builder(SignUpVerify.this).create();
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
                params.put("email", emailtext);
                params.put("otp", String.valueOf(num));
                return params;
            }
        };
        MySingleton.getInstance(SignUpVerify.this).addToRequestQueue(stringRequest);
    }
}
