package appp.renthub;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pranj on 01-04-2018.
 */

public class AccountFrag extends Fragment {
    TextView name,email,bday,address,mobile,gender,city,marrystatus,emailicon,phoneicon,bdayicon,statusicon,cityicon,gendericon,addressicon,tenantemail,tenantphone,tenantdob,tenantaddress,tenantgender,tenantcity,tenantstatus;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.accountfrag,container,false);
        name=v.findViewById(R.id.name);
        tenantemail=v.findViewById(R.id.tenantemail);
        tenantaddress=v.findViewById(R.id.tenantaddress);
        tenantphone=v.findViewById(R.id.tenantphone);
        tenantdob=v.findViewById(R.id.tenantdob);
        tenantgender=v.findViewById(R.id.tenantgender);
        tenantstatus=v.findViewById(R.id.tenantstatus);
        tenantcity=v.findViewById(R.id.tenantcity);
        email=v.findViewById(R.id.email);
        address=v.findViewById(R.id.address);
        mobile=v.findViewById(R.id.mobile);
        bday=v.findViewById(R.id.bday);
        gender=v.findViewById(R.id.gender);
        marrystatus=v.findViewById(R.id.marrystatus);
        city=v.findViewById(R.id.city);
        emailicon=v.findViewById(R.id.emailicon);
        phoneicon=v.findViewById(R.id.phoneicon);
        bdayicon=v.findViewById(R.id.bdayicon);
        cityicon=v.findViewById(R.id.cityicon);
        gendericon=v.findViewById(R.id.gendericon);
        statusicon=v.findViewById(R.id.statusicon);
        addressicon=v.findViewById(R.id.addressicon);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        emailicon.setTypeface(font);
        phoneicon.setTypeface(font);
        bdayicon.setTypeface(font);
        cityicon.setTypeface(font);
        gendericon.setTypeface(font);
        statusicon.setTypeface(font);
        addressicon.setTypeface(font);
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
        address.setTypeface(fontstyle);
        email.setTypeface(fontstyle);
        marrystatus.setTypeface(fontstyle);
        city.setTypeface(fontstyle);
        bday.setTypeface(fontstyle);
        mobile.setTypeface(fontstyle);
        gender.setTypeface(fontstyle);
        return v;
    }
}
