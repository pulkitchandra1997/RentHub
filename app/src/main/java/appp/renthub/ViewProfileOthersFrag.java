package appp.renthub;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Ayushi on 4/10/2018.
 */

public class ViewProfileOthersFrag extends Fragment {

    TextView username, otherdetails, dobicon, dob, userdob, cityicon, city, usercity, gendericon, gender, usergender, marryicon, marry, usermarry, addicon, add, useradd, placecount, rentedplaces;
    ImageView userpic;
    LinearLayout layoutoptional;
    Button callicon, msgicon, mailicon;

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


        return v;
    }
}




