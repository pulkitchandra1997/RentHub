package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
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

    TextView homeicon, homeaddress, moneyicon, rent, permonth, facilities, acicon, wifiicon, bedicon, sofaicon, fridgeicon, powericon, parkingicon, messicon, tvicon, houseowner, ownname;
    TextView homecity, homepin, statusicon, housetype, statusvalue,rentvalue;
    ImageView homeimg, ownerpic;
    LinearLayout ac, bed, wifi, sofa, fridge, power, tv, parking, mess,outer;
    Button booknow, alreadybooked;
    ProgressBar login_progress;
    TableLayout viewprof;
    TableRow userprofile;
    String address,owneremail;
    SEARCHRESULT searchresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        Intent intent=getIntent();
        searchresult=(SEARCHRESULT)intent.getSerializableExtra("searchresult");

        homeicon =findViewById(R.id.homeicon);
        homeimg =findViewById(R.id.homeimg);
        ownerpic =findViewById(R.id.ownerpic);
        homeaddress =findViewById(R.id.homeaddress);
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

        homeaddress.setTypeface(f4);
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

        userprofile= findViewById(R.id.userprofile);
        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceDetails.this, OthersProfile.class);
                intent.putExtra("owneremail",searchresult.getOwneremail());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(PlaceDetails.this, R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });


        homecity.setText(searchresult.getCity());
        homeaddress.setText(searchresult.getAddress());
        homepin.setText(searchresult.getPincode());
        ownname.setText(searchresult.getOwnername());
        housetype.setText(searchresult.getStatus());
        rentvalue.setText(searchresult.getAmount());
        Picasso.with(this).load(searchresult.getPicname()).fit().centerCrop().into(homeimg);
        if (searchresult.getAcid().equals("0"))
        {
            ac.setVisibility(View.GONE);
        }
        if (searchresult.getWifiid().equals("0"))
        {
            wifi.setVisibility(View.GONE);
        }
        if (searchresult.getMessid().equals("0"))
        {
            mess.setVisibility(View.GONE);
        }
        if (searchresult.getParkingid().equals("0"))
        {
            parking.setVisibility(View.GONE);
        }
        if (searchresult.getBedid().equals("0"))
        {
            bed.setVisibility(View.GONE);
        }
        if (searchresult.getSofaid().equals("0"))
        {
            sofa.setVisibility(View.GONE);
        }
        if (searchresult.getRefrigeratorid().equals("0"))
        {
           fridge.setVisibility(View.GONE);
        }
        if (searchresult.getInvertorid().equals("0"))
        {
            power.setVisibility(View.GONE);
        }
        if (searchresult.getTvid().equals("0"))
        {
            tv.setVisibility(View.GONE);
        }
        if (searchresult.getRented().equals("1"))
        {
            booknow.setVisibility(View.GONE);
        }
        else
        {
            alreadybooked.setVisibility(View.GONE);

        }

    }



    @Override
    public void onClick(View v) {

    }


}
