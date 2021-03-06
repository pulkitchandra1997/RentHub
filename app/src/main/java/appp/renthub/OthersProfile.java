package appp.renthub;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.sql.Types.NULL;

public class OthersProfile extends Activity implements View.OnClickListener{

    TextView  otherdetails, dobicon, dob, userdob, cityicon, city, usercity, gendericon, gender, usergender, marryicon, marry, usermarry, addicon, add, useradd, placecount, rentedplaces;
    ImageView userpic;
    LinearLayout layoutoptional;
    Button callicon, msgicon, mailicon;
    TableLayout showrented;
    String useremail, email , phone;
    ProgressBar login_progress;
    AwesomeTextView username;
    JSONObject jsonObject;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_profile);

        sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);
        useremail=sp.getString("email","email");


        Intent intent=getIntent();
        email=intent.getStringExtra("owneremail");


        login_progress=findViewById(R.id.login_progress);

        username = findViewById(R.id.username);
        userpic = findViewById(R.id.userpic);
        otherdetails = findViewById(R.id.otherdetails);
        dob = findViewById(R.id.dob);
        city = findViewById(R.id.city);
        gender = findViewById(R.id.gender);
        marry = findViewById(R.id.marry);
        add = findViewById(R.id.add);
        rentedplaces = findViewById(R.id.rentedplaces);
        placecount = findViewById(R.id.placecount);
        showrented = findViewById(R.id.showrented);


        dobicon = findViewById(R.id.dobicon);
        cityicon = findViewById(R.id.cityicon);
        gendericon = findViewById(R.id.gendericon);
        marryicon = findViewById(R.id.marryicon);
        addicon = findViewById(R.id.addicon);


        userdob = findViewById(R.id.userdob);
        usercity = findViewById(R.id.usercity);
        usergender = findViewById(R.id.usergender);
        usermarry = findViewById(R.id.usermarry);
        useradd = findViewById(R.id.useradd);

        layoutoptional = findViewById(R.id.layoutoptional);
        mailicon = findViewById(R.id.mailicon);
        placecount = findViewById(R.id.placecount);
        callicon = findViewById(R.id.callicon);
        msgicon = findViewById(R.id.msgicon);


        Typeface f1 = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf");

        Typeface f2 = Typeface.createFromAsset(getAssets(), "Oregon LDO Medium.ttf");

        Typeface f3 = Typeface.createFromAsset(getAssets(), "JosefinSans-Light.ttf");


        username.setTypeface(f2);
        otherdetails.setTypeface(f2);
        dob.setTypeface(f2);
        city.setTypeface(f2);
        gender.setTypeface(f2);
        marry.setTypeface(f2);
        add.setTypeface(f2);
        rentedplaces.setTypeface(f2);
        placecount.setTypeface(f2);

        dobicon.setTypeface(f1);
        cityicon.setTypeface(f1);
        addicon.setTypeface(f1);
        marryicon.setTypeface(f1);
        gendericon.setTypeface(f1);
        mailicon.setTypeface(f1);
        msgicon.setTypeface(f1);
        callicon.setTypeface(f1);


        useradd.setTypeface(f3);
        usermarry.setTypeface(f3);
        usergender.setTypeface(f3);
        userdob.setTypeface(f3);
        usercity.setTypeface(f3);

        callicon.setOnClickListener(this);
        msgicon.setOnClickListener(this);
        mailicon.setOnClickListener(this);

        fromserver();
    }

    private void fromserver() {
        showProgress(true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.URL_VIEW_OTHERS_PROFILE, new Response.Listener<String>() {
            @SuppressLint("ResourceType")
            @Override
            public void onResponse(String response) {

                showProgress(false);
                if (response.equalsIgnoreCase("error0")) {
                    AlertDialog builder = new AlertDialog.Builder(OthersProfile.this).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Error in connection");
                    builder.show();
                } else {

                    try {

                        jsonObject = new JSONObject(response);


                        String name = jsonObject.get("name").toString();
                        String type = jsonObject.get("type").toString();
                        String dob = jsonObject.get("dob").toString();
                        String marriage = jsonObject.get("marriagestatus").toString();
                        String city = jsonObject.get("city").toString();
                        String address = jsonObject.get("permanentaddress").toString();
                        String gender = jsonObject.get("gender").toString();
                        String pin = jsonObject.get("pincode").toString();
                        String count = jsonObject.get("noofplaces").toString();
                        /*email=jsonObject.get("email").toString();*/
                        phone=jsonObject.getString("phone").toString();
                        String picname=jsonObject.getString("picname").toString();

                        String verified = jsonObject.get("verified").toString();

                        if (picname.equals("http://rentzhub.co.in/images/users/")){
                            userpic.setImageResource(R.drawable.defaultpic);
                        }else
                        {
                            Picasso.with(OthersProfile.this).load(picname).fit().centerCrop().into(userpic);
                        }


                        phone = jsonObject.get("phone").toString().trim();

                        username.setText(name);
                        userdob.setText(dob);
                        usercity.setText(city);
                        usergender.setText(gender);
                        usermarry.setText(marriage);
                        useradd.setText(address);

                        if (type == "tenant") {
                            showrented.setVisibility(View.GONE);
                        }

                        placecount.setText(count);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog builder = new AlertDialog.Builder(OthersProfile.this).create();
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                builder.setMessage("Connection error! Retry");
                builder.show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                return params;
            }
        };
        MySingleton.getInstance(OthersProfile.this).addToRequestQueue(stringRequest);
    }


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


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.msgicon) //disabled
        {
            Intent intent=new Intent(this,ComposeMessage.class);
            intent.putExtra("receivermail",email);
            startActivity(intent);

        }

        if (v.getId() == R.id.mailicon) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, email);
            Log.d("useremail",useremail);
            intent.setData(Uri.parse(useremail));
            //intent.putExtra(Intent.Action_Send,useremail);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Reg. RentzHub-mailer");
        /*intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
        intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");*/
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));
        }

        if (v.getId() == R.id.callicon) {
            if (!(TextUtils.isEmpty(phone))) {
                Intent i = new Intent(Intent.ACTION_CALL);
                Uri u = Uri.parse("tel:"+phone);
                i.setData(u);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(i);
            }
        }

    }

}
