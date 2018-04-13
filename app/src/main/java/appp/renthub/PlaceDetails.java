package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PlaceDetails extends Activity implements View.OnClickListener{
    android.app.Fragment fragment = null;
    android.app.FragmentTransaction ft;

    TextView homeicon, homeadd, moneyicon, rent, permonth, facilities, acicon, wifiicon, bedicon, sofaicon, fridgeicon, powericon, parkingicon, messicon, tvicon, houseowner, ownname;
    TextView homecity, homepin, statusicon, housetype, statusvalue,rentvalue;
    ImageView homeimg, ownerpic;
    LinearLayout ac, bed, wifi, sofa, fridge, power, tv, parking, mess,outer;
    Button booknow, alreadybooked;
    ProgressBar login_progress;
    TableLayout viewprof;
    TableRow userprofile;
    String address,owneremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        Intent intent=getIntent();
        address=intent.getStringExtra("owneraddress");

        homeicon =findViewById(R.id.homeicon);
        homeimg =findViewById(R.id.homeimg);
        ownerpic =findViewById(R.id.ownerpic);
        homeadd =findViewById(R.id.homeadd);
        homecity=findViewById(R.id.homecity);
        homepin=findViewById(R.id.homepin);
        housetype=findViewById(R.id.housetype);
        statusvalue=findViewById(R.id.statusvalue);
        moneyicon =findViewById(R.id.moneyicon);
        statusicon=findViewById(R.id.statusicon);
        rent =findViewById(R.id.rent);
        permonth =findViewById(R.id.permonth);
        facilities =findViewById(R.id.facilities);
        houseowner = findViewById(R.id.houseowner);
        ownname =findViewById(R.id.ownname);
        booknow =findViewById(R.id.booknow);
        alreadybooked=findViewById(R.id.alreadybooked);
        viewprof=findViewById(R.id.viewprof);
        rentvalue=findViewById(R.id.rentvalue);

        acicon =findViewById(R.id.acicon);
        wifiicon =findViewById(R.id.wifiicon);
        bedicon =findViewById(R.id.bedicon);
        sofaicon =findViewById(R.id.sofaicon);
        fridgeicon =findViewById(R.id.fridgeicon);
        powericon =findViewById(R.id.powericon);
        parkingicon =findViewById(R.id.parkingicon);
        messicon =findViewById(R.id.messicon);
        tvicon =findViewById(R.id.tvicon);

        login_progress= findViewById(R.id.login_progress);


        ac =  findViewById(R.id.ac);
        wifi =  findViewById(R.id.wifi);
        bed =  findViewById(R.id.bed);
        sofa =  findViewById(R.id.sofa);
        fridge =  findViewById(R.id.fridge);
        tv =  findViewById(R.id.tv);
        mess =  findViewById(R.id.mess);
        power =  findViewById(R.id.power);
        parking =  findViewById(R.id.parking);
        outer=  findViewById(R.id.outer);


        Typeface f1 = Typeface.createFromAsset( getAssets(), "Font Awesome 5 Free-Solid-900.otf");

        Typeface f2 = Typeface.createFromAsset( getAssets(), "Font Awesome 5 Free-Regular-400.otf");

        Typeface f4 = Typeface.createFromAsset( getAssets(), "Oregon LDO Medium.ttf");


        homeicon.setTypeface(f1);
        moneyicon.setTypeface(f1);
        statusicon.setTypeface(f1);

        acicon.setTypeface(f1);
        fridgeicon.setTypeface(f1);
        powericon.setTypeface(f1);
        tvicon.setTypeface(f1);
        messicon.setTypeface(f1);
        parkingicon.setTypeface(f1);
        sofaicon.setTypeface(f1);
        bedicon.setTypeface(f1);
        wifiicon.setTypeface(f1);

        homeadd.setTypeface(f4);
        homecity.setTypeface(f4);
        homepin.setTypeface(f4);
        rent.setTypeface(f4);
        facilities.setTypeface(f4);
        rentvalue.setTypeface(f4);
        houseowner.setTypeface(f4);
        ownname.setTypeface(f4);
        housetype.setTypeface(f4);
        statusvalue.setTypeface(f4);
        rentvalue.setTypeface(f4);



        booknow.setOnClickListener(this);
        viewprof.setOnClickListener(this);
        fromserver();

        userprofile= findViewById(R.id.userprofile);
        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new ViewProfileOthers(owneremail);
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.commit();
            }
        });
    }

    @Override
    public void onClick(View v) {
        
    }
    private void fromserver() {
        showProgress(true);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_VIEW_ADDRESS, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                if (response.toLowerCase().contains("placeerror")) {
                    if(response.toLowerCase().contains("placeerror0")){
                        AlertDialog builder = new AlertDialog.Builder( PlaceDetails.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("No such place found");
                        builder.show();
                    }
                    if(response.toLowerCase().contains("placeerror1")){
                        AlertDialog builder = new AlertDialog.Builder( PlaceDetails.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Server Error!");
                        builder.show();
                    }
                    if(response.toLowerCase().contains("placeerror2")){
                        AlertDialog builder = new AlertDialog.Builder( PlaceDetails.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Internet Error.Check Connection!");
                        builder.show();
                    }
                }
                else {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        Toast.makeText(PlaceDetails.this, ""+jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        String address=jsonObject.get("address").toString();
                        String amount=jsonObject.get("amount").toString();
                        String istv=jsonObject.get("tvid").toString();
                        String iswifi=jsonObject.get("wifiid").toString();
                        String ismess=jsonObject.get("messid").toString();
                        String isfridge=jsonObject.get("refrigeratorid").toString();
                        String ispower=jsonObject.get("invertorid").toString();
                        String issofa=jsonObject.get("sofaid").toString();
                        String isbed=jsonObject.get("bedid").toString();
                        String isparking=jsonObject.get("parkingid").toString();
                        String isac=jsonObject.get("acid").toString();
                        String isrented=jsonObject.get("rented").toString();
                        String ownername=jsonObject.get("ownername").toString();
                        owneremail=jsonObject.get("owneremail").toString();
                        homecity.setText(jsonObject.get("city").toString());
                        homepin.setText(jsonObject.get("pincode").toString());
                        statusvalue.setText(jsonObject.get("status").toString());
                        Picasso.with(PlaceDetails.this).load(jsonObject.get("picname").toString()).fit().centerCrop().into(homeimg);
                        ownname.setText(ownername);
                        homeadd.setText(address);
                        rentvalue.setText(amount);
                        if(isrented=="1") {
                            booknow.setVisibility(View.GONE);
                        }
                        else
                        {
                            alreadybooked.setVisibility(View.GONE);
                        }
                        if(istv=="0")
                        {
                            tv.setVisibility(View.GONE);
                        }
                        if(iswifi=="0")
                        {
                            wifi.setVisibility(View.GONE);
                        }
                        if(ismess=="0")
                        {
                            mess.setVisibility(View.GONE);
                        }
                        if(isfridge=="0")
                        {
                            fridge.setVisibility(View.GONE);
                        }
                        if(ispower=="0")
                        {
                            power.setVisibility(View.GONE);
                        }
                        if(isparking=="0")
                        {
                            parking.setVisibility(View.GONE);
                        }
                        if(issofa=="0")
                        {
                            sofa.setVisibility(View.GONE);
                        }
                        if(isbed=="0")
                        {
                            bed.setVisibility(View.GONE);
                        }
                        if(isac=="0")
                        {
                            ac.setVisibility(View.GONE);
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
                AlertDialog builder = new AlertDialog.Builder(PlaceDetails.this).create();
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                builder.setMessage("Connection error! Retry");
                builder.show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("address",address);
                return params;
            }
        };
        MySingleton.getInstance( this).addToRequestQueue(stringRequest);
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


}
