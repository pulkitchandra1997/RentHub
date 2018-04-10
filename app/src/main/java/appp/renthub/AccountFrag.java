package appp.renthub;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pranj on 01-04-2018.
 */

public class AccountFrag extends Fragment implements View.OnClickListener {
    TextView editprofileicon,viewprofileicon,viewprofile,editprofile,name,email,bday,address,mobile,gender,city,marrystatus,emailicon,phoneicon,bdayicon,statusicon,cityicon,gendericon,addressicon,tenantemail,tenantphone,tenantdob,tenantaddress,tenantgender,tenantcity,tenantstatus;
    TextView email2,bday2,address2,mobile2,gender2,city2,marrystatus2,emailicon2,phoneicon2,bdayicon2,statusicon2,cityicon2,gendericon2,addressicon2,logout,changepassword;
    EditText tenantemail2,tenantphone2,tenantdob2,tenantaddress2,tenantgender2;
    CardView viewcard,editcard;
    Spinner tenantcity2,tenantstatus2;
    LinearLayout viewlink,editlink;
    Button editbtn;
    SharedPreferences sp;
    SharedPreferences.Editor se;
    OWNER owner;
    USER user;

    @SuppressLint("ValidFragment")
    public AccountFrag(Object object, String type)
    {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.accountfrag,container,false);

        editbtn=v.findViewById(R.id.editbtn);
        editlink=v.findViewById(R.id.editlink);
        viewlink=v.findViewById(R.id.viewlink);
        viewcard=v.findViewById(R.id.viewcard);
        editcard=v.findViewById(R.id.editcard);
        tenantcity2=v.findViewById(R.id.tenantcity2);
        tenantstatus2=v.findViewById(R.id.tenantstatus2);
        name=v.findViewById(R.id.name);
        editprofile=v.findViewById(R.id.editprofile);
        viewprofile=v.findViewById(R.id.viewprofile);
        editprofileicon=v.findViewById(R.id.editprofileicon);
        tenantemail=v.findViewById(R.id.tenantemail);
        tenantaddress=v.findViewById(R.id.tenantaddress);
        tenantphone=v.findViewById(R.id.tenantphone);
        tenantdob=v.findViewById(R.id.tenantdob);
        tenantgender=v.findViewById(R.id.tenantgender);
        tenantstatus=v.findViewById(R.id.tenantstatus);
        tenantcity=v.findViewById(R.id.tenantcity);
        tenantemail2=v.findViewById(R.id.tenantemail2);
        tenantaddress2=v.findViewById(R.id.tenantaddress2);
        tenantphone2=v.findViewById(R.id.tenantphone2);
        tenantdob2=v.findViewById(R.id.tenantdob2);
        tenantgender2=v.findViewById(R.id.tenantgender2);
        email=v.findViewById(R.id.email);
        address=v.findViewById(R.id.address);
        mobile=v.findViewById(R.id.mobile);
        bday=v.findViewById(R.id.bday);
        gender=v.findViewById(R.id.gender);
        marrystatus=v.findViewById(R.id.marrystatus);
        city=v.findViewById(R.id.city);
        viewprofileicon=v.findViewById(R.id.viewprofileicon);
        emailicon=v.findViewById(R.id.emailicon);
        phoneicon=v.findViewById(R.id.phoneicon);
        bdayicon=v.findViewById(R.id.bdayicon);
        cityicon=v.findViewById(R.id.cityicon);
        gendericon=v.findViewById(R.id.gendericon);
        statusicon=v.findViewById(R.id.statusicon);
        addressicon=v.findViewById(R.id.addressicon);
        email2=v.findViewById(R.id.email2);
        address2=v.findViewById(R.id.address2);
        mobile2=v.findViewById(R.id.mobile2);
        bday2=v.findViewById(R.id.bday2);
        gender2=v.findViewById(R.id.gender2);
        marrystatus2=v.findViewById(R.id.marrystatus2);
        city2=v.findViewById(R.id.city2);
        emailicon2=v.findViewById(R.id.emailicon2);
        phoneicon2=v.findViewById(R.id.phoneicon2);
        bdayicon2=v.findViewById(R.id.bdayicon2);
        cityicon2=v.findViewById(R.id.cityicon2);
        gendericon2=v.findViewById(R.id.gendericon2);
        statusicon2=v.findViewById(R.id.statusicon2);
        addressicon2=v.findViewById(R.id.addressicon2);
        logout=v.findViewById(R.id.logout);
        changepassword=v.findViewById(R.id.changepassword);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
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
        addressicon2.setTypeface(font);
        editprofileicon.setTypeface(font);
        viewprofileicon.setTypeface(font);
        Typeface fontstyle = Typeface.createFromAsset(getActivity().getAssets(), "Oregon LDO Medium.ttf" );
        name.setTypeface(fontstyle);
        Typeface fontface = Typeface.createFromAsset(getActivity().getAssets(), "JosefinSans-Light.ttf" );
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
        marrystatus2.setTypeface(fontstyle);
        city2.setTypeface(fontstyle);
        bday2.setTypeface(fontstyle);
        mobile2.setTypeface(fontstyle);
        gender2.setTypeface(fontstyle);

        editprofile.setOnClickListener(this);
        editbtn.setOnClickListener(this);
        viewprofile.setOnClickListener(this);
        logout.setOnClickListener(this);
        changepassword.setOnClickListener(this);

        sp=getActivity().getSharedPreferences("RentHub_data",MODE_PRIVATE);
        se=sp.edit();
        return v;
    }

    @Override
    public void onClick(View v) {
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
        if (v.getId() == R.id.logout);
        {
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
            Intent intent=new Intent(getActivity(),LoginActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            }
            else{
                startActivity(intent);
            }
        }

    }
}
