package appp.renthub;

        import android.animation.Animator;
        import android.animation.AnimatorListenerAdapter;
        import android.app.ActivityOptions;
        import android.app.Fragment;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.text.Html;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.TableLayout;
        import android.widget.TextView;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

        import static android.os.Build.VERSION_CODES.JELLY_BEAN;

/**
 * Created by Ayushi on 4/9/2018.
 */

public class PlaceDetailsFrag extends Fragment implements View.OnClickListener {

    android.app.Fragment fragment = null;
    android.app.FragmentTransaction ft;

    TextView homeicon, homeadd, moneyicon, rent, permonth, facilities, acicon, wifiicon, bedicon, sofaicon, fridgeicon, powericon, parkingicon, messicon, tvicon, houseowner, ownname;
    TextView homecity, homepin, statusicon, housetype, statusvalue;
    ImageView homeimg, ownerpic;
    LinearLayout ac, bed, wifi, sofa, fridge, power, tv, parking, mess,outer;
    Button booknow, alreadybooked;
    ProgressBar progressBar;
    TableLayout viewprof;

    String address = "D1/43 Priyadarshini Yojna, Sitapur Road";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.placedetailsfrag, container, false);

        homeicon = v.findViewById(R.id.homeicon);
        homeimg = v.findViewById(R.id.homeimg);
        ownerpic = v.findViewById(R.id.ownerpic);
        homeadd = v.findViewById(R.id.homeadd);
        homecity= v.findViewById(R.id.homecity);
        homepin=v.findViewById(R.id.homepin);
        housetype= v.findViewById(R.id.housetype);
        statusvalue= v.findViewById(R.id.statusvalue);
        moneyicon = v.findViewById(R.id.moneyicon);
        statusicon= v.findViewById(R.id.statusicon);
        rent = v.findViewById(R.id.rent);
        permonth = v.findViewById(R.id.permonth);
        facilities = v.findViewById(R.id.facilities);
        houseowner = v.findViewById(R.id.houseowner);
        ownname = v.findViewById(R.id.ownname);
        booknow = v.findViewById(R.id.booknow);
        alreadybooked= v.findViewById(R.id.alreadybooked);
        viewprof=v.findViewById(R.id.viewprof);

        acicon = v.findViewById(R.id.acicon);
        wifiicon = v.findViewById(R.id.wifiicon);
        bedicon = v.findViewById(R.id.bedicon);
        sofaicon = v.findViewById(R.id.sofaicon);
        fridgeicon = v.findViewById(R.id.fridgeicon);
        powericon = v.findViewById(R.id.powericon);
        parkingicon = v.findViewById(R.id.parkingicon);
        messicon = v.findViewById(R.id.messicon);
        tvicon = v.findViewById(R.id.tvicon);


        ac = v.findViewById(R.id.ac);
        wifi = v.findViewById(R.id.wifi);
        bed = v.findViewById(R.id.bed);
        sofa = v.findViewById(R.id.sofa);
        fridge = v.findViewById(R.id.fridge);
        tv = v.findViewById(R.id.tv);
        mess = v.findViewById(R.id.mess);
        power = v.findViewById(R.id.power);
        parking = v.findViewById(R.id.parking);
        outer= v.findViewById(R.id.outer);


        Typeface f1 = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Solid-900.otf");

        Typeface f2 = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Regular-400.otf");

        Typeface f4 = Typeface.createFromAsset(getActivity().getAssets(), "Oregon LDO Medium.ttf");


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
        permonth.setTypeface(f4);
        houseowner.setTypeface(f4);
        ownname.setTypeface(f4);
        housetype.setTypeface(f4);
        statusvalue.setTypeface(f4);


        fromserver();
        booknow.setOnClickListener(this);
        viewprof.setOnClickListener(this);



        return v;

    }



    private void fromserver() {
        showProgress(true);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_LOGIN, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                if (response.equalsIgnoreCase("error")){
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Error in connection");
                    builder.show();
                    }
                else {

                    try {
                        JSONObject jsonObject=new JSONObject(response);
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
                        String ownermail=jsonObject.get("ownermail").toString();

                        ownname.setText(ownername);
                        homeadd.setText(address);
                        rent.setText(amount);

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
                params.put("address",address);
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

        if(v.getId()==R.id.booknow) {
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.outer, new PlaceDetailsFrag());
            ft.commit();
        }


        if(v.getId()==R.id.viewprof)
        {
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.outer, new ViewProfileOthersFrag());
            ft.commit();
        }

    }
}

