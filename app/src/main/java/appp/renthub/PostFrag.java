package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
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

import appp.renthub.dbutil.RenthubConstant;
import appp.renthub.dbutil.RenthubManager;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by pranj on 05-04-2018.
 */

@SuppressLint("ValidFragment")
public class PostFrag extends Fragment implements View.OnClickListener{
    TextView addressicon,pincodeicon,cityicon,roomsicon,facilitesicon,amounticon;
    EditText inputaddress,inputpincode,inputamount;
    Spinner roomstatus,inputcity;
    Button inputsubmit,inputnext,inputprevious;
    LinearLayout pageone,pagetwo;
    CheckBox sofa,bed,refrigerator,ac,wifi,invertor,parking,tv,mess;
    String sofaid,bedid,refigratorid,acid,wifiid,invertorid,parkingid,tvid,messid;

    String city,address,amount,pincode,status,email;

    JSONObject jsonObject;
    ProgressBar login_progress;
    ScrollView login_form;
    PROFILE profile;

    @SuppressLint("ValidFragment")
    public PostFrag(PROFILE profile)
    {

    }
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.postfrag, container, false);
        inputaddress = view.findViewById(R.id.inputaddress);
        login_progress=view.findViewById(R.id.login_progress);
        login_form=view.findViewById(R.id.login_form);
        inputpincode = view.findViewById(R.id.inputpincode);
        inputamount = view.findViewById(R.id.inputamount);
        addressicon = view.findViewById(R.id.addressicon);
        pincodeicon = view.findViewById(R.id.pincodeicon);
        cityicon = view.findViewById(R.id.cityicon);
        roomsicon = view.findViewById(R.id.roomsicon);
        facilitesicon = view.findViewById(R.id.facilitesicon);
        amounticon = view.findViewById(R.id.amounticon);
        roomstatus = view.findViewById(R.id.roomstatus);
        inputcity = view.findViewById(R.id.inputcity);
        inputnext = view.findViewById(R.id.inputnext);
        pagetwo = view.findViewById(R.id.pagetwo);
        pageone = view.findViewById(R.id.pageone);
        sofa = view.findViewById(R.id.sofa);
        bed = view.findViewById(R.id.bed);
        refrigerator = view.findViewById(R.id.refrigerator);
        ac = view.findViewById(R.id.ac);
        invertor = view.findViewById(R.id.invertor);
        tv = view.findViewById(R.id.tv);
        wifi = view.findViewById(R.id.wifi);
        mess = view.findViewById(R.id.mess);
        parking = view.findViewById(R.id.parking);
        inputprevious = view.findViewById(R.id.inputprevious);
        inputsubmit = view.findViewById(R.id.inputsubmit);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Solid-900.otf");
        pincodeicon.setTypeface(font);
        addressicon.setTypeface(font);
        cityicon.setTypeface(font);
        roomsicon.setTypeface(font);
        facilitesicon.setTypeface(font);
        amounticon.setTypeface(font);
        inputprevious.setTypeface(font);
        inputnext.setTypeface(font);
        inputsubmit.setTypeface(font);

        inputnext.setOnClickListener(this);
        inputprevious.setOnClickListener(this);
        inputsubmit.setOnClickListener(this);

        return view;
    }
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            login_form.setVisibility(show ? View.GONE : View.VISIBLE);
            login_form.animate().setDuration(500).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    login_form.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

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
            login_form.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.inputnext)
        {
            city = (String) inputcity.getSelectedItem();
            address = inputaddress.getText().toString().trim();
            pincode = inputpincode.getText().toString().trim();
            status = (String) roomstatus.getSelectedItem();
            amount= inputamount.getText().toString().trim();

            if (((String) inputcity.getSelectedItem()).equalsIgnoreCase("Select City") || TextUtils.isEmpty(address) || TextUtils.isEmpty(pincode)|| TextUtils.isEmpty(amount) || ((String) roomstatus.getSelectedItem()).equalsIgnoreCase("Select type of room")) {
                if (((String) roomstatus.getSelectedItem()).equalsIgnoreCase("Select type of room")) {
                    Toast.makeText(getActivity(), "Select Room Status", Toast.LENGTH_SHORT).show();
                    roomstatus.requestFocus();
                    roomstatus.performClick();
                }
                if (TextUtils.isEmpty(pincode)) {
                    inputpincode.setError("Enter Pincode");
                    inputpincode.requestFocus();
                }

                if (TextUtils.isEmpty(amount)) {
                    inputamount.setError("Enter Amount");
                    inputamount.requestFocus();
                }
                if (((String) inputcity.getSelectedItem()).equalsIgnoreCase("Select City")) {

                    Toast.makeText(getActivity(), "Select City", Toast.LENGTH_SHORT).show();
                    inputcity.requestFocus();
                    inputcity.performClick();
                }
                if (TextUtils.isEmpty(address)) {
                    inputaddress.setError("Enter Address");
                    inputaddress.requestFocus();
                }
                inputaddress.requestFocus();
            }
            else if (pincode.length() < 6 || pincode.length()>6)
                 {
                     inputpincode.setError("Pincode must be 6 digits");
                     inputpincode.requestFocus();
                 }
                    else
                 {
                     pagetwo.setVisibility(View.VISIBLE);
                     pageone.setVisibility(View.GONE);
                 }
            }
            if (v.getId() == R.id.inputprevious) {
                pageone.setVisibility(View.VISIBLE);
                pagetwo.setVisibility(View.GONE);
            }
            if (v.getId() == R.id.inputsubmit)
            {
            if (sofa.isChecked() || bed.isChecked() || ac.isChecked() || tv.isChecked() || parking.isChecked() || invertor.isChecked() || refrigerator.isChecked() || mess.isChecked() || wifi.isChecked()) {
                if (sofa.isChecked()) {
                    sofaid = "1";
                } else {
                    sofaid = "0";
                }
                if (bed.isChecked()) {
                    bedid = "1";
                } else {
                    bedid = "0";
                }
                if (ac.isChecked()) {
                    acid = "1";
                } else {
                    acid = "0";
                }
                if (tv.isChecked()) {
                    tvid = "1";
                } else {
                    tvid = "0";
                }
                if (parking.isChecked()) {
                    parkingid = "1";
                } else {
                    parkingid = "0";
                }
                if (invertor.isChecked()) {
                    invertorid = "1";
                } else {
                    invertorid = "0";
                }
                if (refrigerator.isChecked()) {
                    refigratorid = "1";
                } else {
                    refigratorid = "0";
                }
                if (mess.isChecked()) {
                    messid = "1";
                } else {
                    messid = "0";
                }
                if (wifi.isChecked()) {
                    wifiid = "1";
                } else {
                    wifiid = "0";
                }
            }else {
                acid=sofaid=wifiid=bedid=tvid=refigratorid=messid=invertorid=parkingid="0";
            }
                jsonObject=new JSONObject();
                try {
                    jsonObject.put("status",status);
                    jsonObject.put("city",city);
                    jsonObject.put("address",address);
                    jsonObject.put("pincode",pincode);
                    jsonObject.put("amount",amount);
                    jsonObject.put("email",email);
                    jsonObject.put("acid",acid);
                    jsonObject.put("wifiid",wifiid);
                    jsonObject.put("tvid",tvid);
                    jsonObject.put("invertorid",invertorid);
                    jsonObject.put("parkingid",parkingid);
                    jsonObject.put("refrigeratorid",refigratorid);
                    jsonObject.put("bedid",bedid);
                    jsonObject.put("sofaid",sofaid);
                    jsonObject.put("messid",messid);
                    toserver();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    private void toserver() {
        showProgress(true);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_RENT_FORM, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                showProgress(false);
                if(response.equalsIgnoreCase("success"))
                {
                    tosqlite();
                    clearform();
                }
                if(response.equalsIgnoreCase("error")){
                    Snackbar snackbar = Snackbar
                            .make(getActivity().getWindow().getDecorView().getRootView(), "Error in connection!", Snackbar.LENGTH_LONG);
                    View sbView = snackbar.getView();
                    TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Snackbar snackbar = Snackbar
                        .make(getActivity().getWindow().getDecorView().getRootView(), "Connection error! Retry", Snackbar.LENGTH_LONG);
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
                params.put("data",jsonObject.toString());
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void clearform() {
    inputaddress.setText("");
    inputamount.setText("");
    inputcity.setSelection(-1);
    inputpincode.setText("");
    roomstatus.setSelection(-1);
    ac.setChecked(false);
        bed.setChecked(false);
        wifi.setChecked(false);
        sofa.setChecked(false);
        refrigerator.setChecked(false);
        parking.setChecked(false);
        mess.setChecked(false);
        tv.setChecked(false);
        invertor.setChecked(false);
    }

    private void tosqlite() {
        ContentValues contentValues=new ContentValues();
        contentValues.put(RenthubConstant.COL_ADDRESS,address);
        contentValues.put(RenthubConstant.COL_CITY,city);
        contentValues.put(RenthubConstant.COL_STATUS,status);
        contentValues.put(RenthubConstant.COL_PINCODE,pincode);
        contentValues.put(RenthubConstant.COL_AMOUNT,amount);
        contentValues.put(RenthubConstant.COL_TVID,tvid);
        contentValues.put(RenthubConstant.COL_ACID,acid);
        contentValues.put(RenthubConstant.COL_BEDID,bedid);
        contentValues.put(RenthubConstant.COL_MESSID,messid);
        contentValues.put(RenthubConstant.COL_REFRIGERATORID,refigratorid);
        contentValues.put(RenthubConstant.COL_INVERTORID,invertorid);
        contentValues.put(RenthubConstant.COL_WIFI,wifiid);
        contentValues.put(RenthubConstant.COL_SOFAID,sofaid);
        contentValues.put(RenthubConstant.COL_PARKINGID,parkingid);
        contentValues.put(RenthubConstant.COL_RENTED,"0");
        RenthubManager renthubManager=new RenthubManager(getActivity());
        SQLiteDatabase sqLiteDatabase=renthubManager.openDB();
        Long l=sqLiteDatabase.insert(RenthubConstant.TABLE_NAME,null,contentValues);
        if(l>0) {
            Snackbar snackbar = Snackbar
                    .make(getActivity().getWindow().getDecorView().getRootView(), "Posted", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else{
            Snackbar snackbar = Snackbar
                    .make(getActivity().getWindow().getDecorView().getRootView(), "Error in posting.", Snackbar.LENGTH_LONG);
        snackbar.show();    }
}}
