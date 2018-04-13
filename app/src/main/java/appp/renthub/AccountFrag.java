package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pranj on 01-04-2018.
 */

public class AccountFrag extends Fragment implements View.OnClickListener {
    TextView editprofileicon, viewprofileicon, viewprofile, editprofile, name, email, bday, address, mobile, gender, city, marrystatus, emailicon, phoneicon, bdayicon, statusicon, cityicon, gendericon, addressicon, tenantemail, tenantphone, tenantdob, tenantaddress, tenantgender, tenantcity, tenantstatus;
    TextView pincode2,email2, bday2, address2, mobile2, gender2, city2, marrystatus2, emailicon2, phoneicon2, bdayicon2, statusicon2, cityicon2, gendericon2, addressicon2, logout, changepassword,pincodeicon2,pwdicon,pwdicon1,pwdicon2;
    EditText tenantemail2, tenantphone2, tenantdob2, tenantaddress2, tenantgender2,tenantpincode2,oldpassword,newpassword,confirmnewpwd;
    CardView viewcard, editcard,passwordcard;
    Spinner tenantcity2, tenantstatus2;
    LinearLayout viewlink, editlink;
    Button editbtn,change;
    SharedPreferences sp;
    SharedPreferences.Editor se;
    PROFILE profile;
    String editaddress, editstatus, editcity, editphone,editpincode,oldpwd,newpwd,confirmpwd;
    JSONObject jsonObject;
    ProgressBar progressBar;

    @SuppressLint("ValidFragment")
    public AccountFrag(PROFILE profile) {
        this.profile = profile;
    }

    public AccountFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.accountfrag, container, false);

        editcard=v.findViewById(R.id.editcard);
        editbtn = v.findViewById(R.id.editbtn);
        editlink = v.findViewById(R.id.editlink);
        viewlink = v.findViewById(R.id.viewlink);
        viewcard = v.findViewById(R.id.viewcard);
        viewcard = v.findViewById(R.id.viewcard);
        passwordcard = v.findViewById(R.id.passwordcard);
        tenantcity2 = v.findViewById(R.id.tenantcity2);
        pincodeicon2=v.findViewById(R.id.pincodeicon2);
        tenantstatus2 = v.findViewById(R.id.tenantstatus2);
        name = v.findViewById(R.id.name);
        pincode2=v.findViewById(R.id.pincode2);
        editprofile = v.findViewById(R.id.editprofile);
        viewprofile = v.findViewById(R.id.viewprofile);
        tenantpincode2=v.findViewById(R.id.tenantpincode2);
        editprofileicon = v.findViewById(R.id.editprofileicon);
        tenantemail = v.findViewById(R.id.tenantemail);
        tenantaddress = v.findViewById(R.id.tenantaddress);
        tenantphone = v.findViewById(R.id.tenantphone);
        tenantdob = v.findViewById(R.id.tenantdob);
        tenantgender = v.findViewById(R.id.tenantgender);
        tenantstatus = v.findViewById(R.id.tenantstatus);
        tenantcity = v.findViewById(R.id.tenantcity);
        tenantemail2 = v.findViewById(R.id.tenantemail2);
        tenantaddress2 = v.findViewById(R.id.tenantaddress2);
        tenantphone2 = v.findViewById(R.id.tenantphone2);
        tenantdob2 = v.findViewById(R.id.tenantdob2);
        tenantgender2 = v.findViewById(R.id.tenantgender2);
        email = v.findViewById(R.id.email);
        address = v.findViewById(R.id.address);
        mobile = v.findViewById(R.id.mobile);
        bday = v.findViewById(R.id.bday);
        gender = v.findViewById(R.id.gender);
        marrystatus = v.findViewById(R.id.marrystatus);
        city = v.findViewById(R.id.city);
        viewprofileicon = v.findViewById(R.id.viewprofileicon);
        emailicon = v.findViewById(R.id.emailicon);
        phoneicon = v.findViewById(R.id.phoneicon);
        bdayicon = v.findViewById(R.id.bdayicon);
        cityicon = v.findViewById(R.id.cityicon);
        gendericon = v.findViewById(R.id.gendericon);
        statusicon = v.findViewById(R.id.statusicon);
        addressicon = v.findViewById(R.id.addressicon);
        email2 = v.findViewById(R.id.email2);
        address2 = v.findViewById(R.id.address2);
        mobile2 = v.findViewById(R.id.mobile2);
        bday2 = v.findViewById(R.id.bday2);
        gender2 = v.findViewById(R.id.gender2);
        marrystatus2 = v.findViewById(R.id.marrystatus2);
        city2 = v.findViewById(R.id.city2);
        emailicon2 = v.findViewById(R.id.emailicon2);
        phoneicon2 = v.findViewById(R.id.phoneicon2);
        bdayicon2 = v.findViewById(R.id.bdayicon2);
        cityicon2 = v.findViewById(R.id.cityicon2);
        gendericon2 = v.findViewById(R.id.gendericon2);
        statusicon2 = v.findViewById(R.id.statusicon2);
        addressicon2 = v.findViewById(R.id.addressicon2);
        pwdicon=v.findViewById(R.id.pwdicon);
        pwdicon1=v.findViewById(R.id.pwdicon1);
        pwdicon2=v.findViewById(R.id.pwdicon2);
        oldpassword=v.findViewById(R.id.oldpassword);
        newpassword=v.findViewById(R.id.newpassword);
        confirmnewpwd=v.findViewById(R.id.confirmnewpwd);
        change=v.findViewById(R.id.change);
        logout = v.findViewById(R.id.logout);
        changepassword = v.findViewById(R.id.changepassword);
        progressBar=v.findViewById(R.id.login_progress);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Solid-900.otf");
        pwdicon.setTypeface(font);
        pwdicon1.setTypeface(font);
        pwdicon2.setTypeface(font);
        emailicon.setTypeface(font);
        phoneicon.setTypeface(font);
        bdayicon.setTypeface(font);
        cityicon.setTypeface(font);
        gendericon.setTypeface(font);
        statusicon.setTypeface(font);
        addressicon.setTypeface(font);
        emailicon2.setTypeface(font);
        phoneicon2.setTypeface(font);
        bdayicon2.setTypeface(font);
        cityicon2.setTypeface(font);
        gendericon2.setTypeface(font);
        statusicon2.setTypeface(font);
        pincodeicon2.setTypeface(font);
        addressicon2.setTypeface(font);
        editprofileicon.setTypeface(font);
        viewprofileicon.setTypeface(font);
        Typeface fontstyle = Typeface.createFromAsset(getActivity().getAssets(), "Oregon LDO Medium.ttf");
        name.setTypeface(fontstyle);
        Typeface fontface = Typeface.createFromAsset(getActivity().getAssets(), "JosefinSans-Light.ttf");
        tenantaddress.setTypeface(fontface);
        tenantemail.setTypeface(fontface);
        tenantstatus.setTypeface(fontface);
        tenantcity.setTypeface(fontface);
        tenantdob.setTypeface(fontface);
        tenantphone.setTypeface(fontface);
        tenantgender.setTypeface(fontface);
        tenantaddress2.setTypeface(fontface);
        tenantemail2.setTypeface(fontface);
        tenantdob2.setTypeface(fontface);
        tenantphone2.setTypeface(fontface);
        tenantgender2.setTypeface(fontface);

        address.setTypeface(fontstyle);
        email.setTypeface(fontstyle);
        marrystatus.setTypeface(fontstyle);
        city.setTypeface(fontstyle);
        bday.setTypeface(fontstyle);
        mobile.setTypeface(fontstyle);
        gender.setTypeface(fontstyle);
        address2.setTypeface(fontstyle);
        email2.setTypeface(fontstyle);
        pincode2.setTypeface(fontstyle);
        marrystatus2.setTypeface(fontstyle);
        city2.setTypeface(fontstyle);
        bday2.setTypeface(fontstyle);
        mobile2.setTypeface(fontstyle);
        gender2.setTypeface(fontstyle);
        change.setTypeface(font);

        editprofile.setOnClickListener(this);
        editbtn.setOnClickListener(this);
        viewprofile.setOnClickListener(this);
        logout.setOnClickListener(this);
        changepassword.setOnClickListener(this);
        change.setOnClickListener(this);

        sp = getActivity().getSharedPreferences("RentHub_data", MODE_PRIVATE);
        se = sp.edit();
        fill();
        return v;
    }

    private void fill() {
        tenantemail.setText(profile.getEmail());
        tenantaddress.setText(profile.getPermanentaddress());
        tenantcity.setText(profile.getCity());
        tenantdob.setText(profile.getDob());
        tenantgender.setText(profile.getGender());
        tenantphone.setText(profile.getPhone());
        tenantstatus.setText(profile.getMarriagestatus());
        name.setText(profile.getName());
        name.setAllCaps(true);
        tenantemail2.setText(profile.getEmail());
        tenantphone2.setText(profile.getPhone());
        tenantdob2.setText(profile.getDob());
        tenantgender2.setText(profile.getGender());
        tenantaddress2.setText(profile.getPermanentaddress());
        tenantpincode2.setText(profile.getPincode());
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.editbtn) {
            editaddress = tenantaddress2.getText().toString().trim();
            editcity = tenantcity2.getSelectedItem().toString();
            editstatus = tenantstatus2.getSelectedItem().toString();
            editphone = tenantphone2.getText().toString().trim();
            editpincode=tenantpincode2.getText().toString().trim();
            if (TextUtils.isEmpty(editaddress) || TextUtils.isEmpty(editphone) || editcity.equalsIgnoreCase("select city") || editstatus.equalsIgnoreCase("select marital status")||TextUtils.isEmpty(editpincode)) {
                if (TextUtils.isEmpty(editaddress)) {
                    tenantaddress2.setError("Enter address");
                    tenantaddress2.requestFocus();
                }
                if (TextUtils.isEmpty(editphone)) {
                    tenantphone2.setError("Enter Phone");
                    tenantphone2.requestFocus();
                }
                if (TextUtils.isEmpty(editpincode)) {
                    tenantpincode2.setError("Enter Pincode");
                    tenantpincode2.requestFocus();
                }
                if (editcity.equalsIgnoreCase("select city"))
                    Toast.makeText(getActivity(), "Select city", Toast.LENGTH_SHORT).show();
                if (editstatus.equalsIgnoreCase("select marital status"))
                    Toast.makeText(getActivity(), "Select Marriage Status", Toast.LENGTH_SHORT).show();
            } else {
                if (!Validation.isValidPhone(editphone)) {
                    tenantphone2.setError("Enter Valid Phone no");
                    tenantphone2.requestFocus();
                } else {
                    if(editpincode.length()==6)
                    updateprofile(editphone, editcity, editstatus, editaddress,editpincode);
                    else {
                        tenantpincode2.setError("Enter Valid pincode");
                        tenantpincode2.requestFocus();
                    }
                }
            }
        }
        if (v.getId() == R.id.editprofile) {
            editcard.setVisibility(View.VISIBLE);
            viewcard.setVisibility(View.GONE);
            viewlink.setVisibility(View.VISIBLE);
            editlink.setVisibility(View.GONE);
        }
        if (v.getId() == R.id.viewprofile) {
            viewcard.setVisibility(View.VISIBLE);
            editcard.setVisibility(View.GONE);
            editlink.setVisibility(View.VISIBLE);
            viewlink.setVisibility(View.GONE);
        }
        if (v.getId() == R.id.changepassword){
            passwordcard.setVisibility(View.VISIBLE);

        }
        /*Change Password*/
        if(v.getId() == R.id.change) {
            oldpwd = oldpassword.getText().toString().trim();
            newpwd = newpassword.getText().toString().trim();
            confirmpwd = confirmnewpwd.getText().toString().trim();
            if (TextUtils.isEmpty(oldpwd) || TextUtils.isEmpty(newpwd) || TextUtils.isEmpty(confirmpwd)) {
                if (TextUtils.isEmpty(oldpwd)) {
                    oldpassword.setError("Enter Old Password");
                    oldpassword.requestFocus();
                }
                if (TextUtils.isEmpty(newpwd)) {
                    newpassword.setError("Enter New Password");
                    newpassword.requestFocus();

                }
                if (TextUtils.isEmpty(confirmpwd)) {
                    confirmnewpwd.setError("Confirm Your  New password");
                    confirmnewpwd.requestFocus();
                }
                oldpassword.requestFocus();
            }else
            if(oldpwd.length()<8||newpwd.length()<8||confirmpwd.length()<8)
            {
                if (oldpwd.length()<8)
                {
                    oldpassword.setError("Password must be more than 8 characters");
                    oldpassword.requestFocus();
                }
                if (newpwd.length()<8)
                {
                    newpassword.setError("Password must be more than 8 characters");
                    newpassword.requestFocus();
                }
                if (confirmpwd.length()<8)
                {
                    confirmnewpwd.setError("Password must be more than 8 characters");
                    confirmnewpwd.requestFocus();
                }
                oldpassword.requestFocus();
            }else {
                if (!newpwd.equals(confirmpwd) || !profile.getPassword().equals(oldpwd))
                {
                    if (!profile.getPassword().equals(oldpwd)) {
                        oldpassword.setError("Incorrect Password");
                        oldpassword.requestFocus();
                    }if (!newpwd.equals(confirmpwd))
                    {
                        confirmnewpwd.setError("Both passwords should be same");
                        confirmnewpwd.requestFocus();
                    }
                }else {
                    updatepassword();
                }
            }
        }
        /*Change Password ends*/
            if (v.getId() == R.id.logout) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        se.remove("email");
                        se.remove("password");
                        se.remove("name");
                        se.remove("type");
                        se.remove("phone");
                        se.remove("dob");
                        se.remove("marriagestatus");
                        se.remove("city");
                        se.remove("permanentaddress");
                        se.remove("pincode");
                        se.remove("gender");
                        se.remove("verified");
                        se.commit();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.fade_in, R.anim.fade_out);
                            startActivity(intent, options.toBundle());
                        } else {
                            startActivity(intent);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create();
                builder.show();
            }

        }



    private void updatepassword() {
        showProgress(true);
        jsonObject= new JSONObject();
        try {
            jsonObject.put("email", profile.getEmail());
            jsonObject.put("password", newpwd);

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_UPDATE_PASSWORD, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                showProgress(false);
                if(response.equalsIgnoreCase("success")){
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Password Updated Successfully!");
                    builder.show();

                }
                if(response.equalsIgnoreCase("error")){
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Error in Connection! Please Retry.");
                    builder.show();
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
                params.put("data",jsonObject.toString());
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void updateprofile(final String editphone, final String editcity, final String editstatus, final String editaddress,final String editpincode) {
        showProgress(true);
        jsonObject= new JSONObject();
        try {
            jsonObject.put("phone", editphone);
            jsonObject.put("email", profile.getEmail());
            jsonObject.put("city", editcity);
            jsonObject.put("marriagestatus", editstatus);
            jsonObject.put("permanentaddress", editaddress);
            jsonObject.put("pincode",editpincode);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_UPDATE_PROFILE, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                showProgress(false);
                if(response.equalsIgnoreCase("success")){
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Profile Updated");
                    builder.show();

                    se.putString("phone",editphone);
                    se.putString("marriagestatus",editstatus);
                    se.putString("city",editcity);
                    se.putString("permanentaddress",editaddress);
                    se.putString("pincode",editpincode);
                    profile.setCity(editcity);
                    profile.setMarriagestatus(editstatus);
                    profile.setPermanentaddress(editaddress);
                    profile.setPincode(editpincode);
                    profile.setPhone(editphone);
                    fill();
                    }
                if(response.equalsIgnoreCase("error")){
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Error in connection");
                    builder.show();
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
                params.put("data",jsonObject.toString());
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
}
