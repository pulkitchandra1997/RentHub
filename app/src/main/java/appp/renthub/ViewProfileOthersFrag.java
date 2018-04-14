package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ayushi on 4/10/2018.
 */

public class ViewProfileOthersFrag extends Fragment implements View.OnClickListener {

    android.app.Fragment fragment = null;
    android.app.FragmentTransaction ft;
    ProgressBar progressBar;

    TextView username, otherdetails, dobicon, dob, userdob, cityicon, city, usercity, gendericon, gender, usergender, marryicon, marry, usermarry, addicon, add, useradd, placecount, rentedplaces;
    ImageView userpic;
    LinearLayout layoutoptional;
    Button callicon, msgicon, mailicon;
    TableLayout showrented;
    String email,phone;


    @SuppressLint("ValidFragment")
    public ViewProfileOthersFrag(String email){
        this.email=email;
    }
    public ViewProfileOthersFrag(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.view_profile_others_frag,container,false);

        username=v.findViewById(R.id.username);
        userpic=v.findViewById(R.id.userpic);
        otherdetails=v.findViewById(R.id.otherdetails);
        dob=v.findViewById(R.id.dob);
        city=v.findViewById(R.id.city);
        gender=v.findViewById(R.id.gender);
        marry=v.findViewById(R.id.marry);
        add=v.findViewById(R.id.add);
        rentedplaces=v.findViewById(R.id.rentedplaces);
        placecount=v.findViewById(R.id.placecount);
        showrented=v.findViewById(R.id.showrented);


        dobicon=v.findViewById(R.id.dobicon);
        cityicon=v.findViewById(R.id.cityicon);
        gendericon=v.findViewById(R.id.gendericon);
        marryicon=v.findViewById(R.id.marryicon);
        addicon=v.findViewById(R.id.addicon);


        userdob=v.findViewById(R.id.userdob);
        usercity=v.findViewById(R.id.usercity);
        usergender=v.findViewById(R.id.usergender);
        usermarry=v.findViewById(R.id.usermarry);
        useradd=v.findViewById(R.id.useradd);

        layoutoptional=v.findViewById(R.id.layoutoptional);
        callicon=v.findViewById(R.id.callicon);
        msgicon=v.findViewById(R.id.msgicon);
        mailicon=v.findViewById(R.id.mailicon);
        placecount=v.findViewById(R.id.placecount);


        Typeface f1 = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Solid-900.otf" );

        Typeface f2 = Typeface.createFromAsset(getActivity().getAssets(), "Oregon LDO Medium.ttf" );

        Typeface f3 = Typeface.createFromAsset(getActivity().getAssets(), "JosefinSans-Light.ttf" );


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


        return v;
    }


    private void fromserver() {
        showProgress(true);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_VIEW_OTHERS_PROFILE, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                if (response.toLowerCase().contains("profileerror")) {
                    if(response.toLowerCase().contains("profileerror0")){
                        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("No such Profile found");
                        builder.show();
                    }
                    if(response.toLowerCase().contains("profileerror1")){
                        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Server Error!");
                        builder.show();
                    }
                    if(response.toLowerCase().contains("profileerror2")){
                        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Internet Error.Check Connection!");
                        builder.show();
                    }
                }
                else {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String name=jsonObject.get("name").toString();
                        String type=jsonObject.get("type").toString();
                        String dob=jsonObject.get("dob").toString();
                        String marriage=jsonObject.get("marriagestatus").toString();
                        String city=jsonObject.get("city").toString();
                        String address=jsonObject.get("permanentaddress").toString();
                        String gender=jsonObject.get("gender").toString();
                        String pin=jsonObject.get("pincode").toString();
                        String count=jsonObject.get("noofplaces").toString();

                        /*email=jsonObject.get("email").toString();*/
                        phone=jsonObject.get("phone").toString().trim();


                        username.setText(name);
                        userdob.setText(dob);
                        usercity.setText(city);
                        usergender.setText(gender);
                        usermarry.setText(marriage);
                        useradd.setText(address);

                        if(type=="tenant") {
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
            public void onErrorResponse(VolleyError error)
            {
                showProgress(false);
                AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
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
                params.put("email",email);
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }


    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.mailicon) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, email);
        /*intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
        intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
        intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");*/
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));
        }


        if(v.getId()==R.id.msgicon) //disabled
        {
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.outer, new ViewProfileOthersFrag());
            ft.commit();
        }

        if(v.getId()==R.id.phoneicon)
        {
            if (!(TextUtils.isEmpty(phone))) {
                Intent i = new Intent(Intent.ACTION_CALL);
                Uri u = Uri.parse("tel:" + phone);
                i.setData(u);
                startActivity(i);
            }
        }

    }
}




