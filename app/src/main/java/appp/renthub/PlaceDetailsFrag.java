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
 * Created by Ayushi on 4/9/2018.
 */

public class PlaceDetailsFrag extends Fragment {

    TextView homeicon, homeadd, moneyicon, rent, permonth, facilities, acicon,wifiicon, bedicon, sofaicon, fridgeicon, powericon, parkingicon, messicon, tvicon, houseowner, ownername;
    ImageView homeimg, ownerpic;
    LinearLayout ac, bed, wifi, sofa, fridge, power, tv, parking, mess;
    Button book;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.placedetailsfrag,container,false);

        homeicon=v.findViewById(R.id.homeicon);
        homeimg= v.findViewById(R.id.homeimg);
        ownerpic=v.findViewById(R.id.ownerpic);
        homeadd=v.findViewById(R.id.homeadd);
        moneyicon=v.findViewById(R.id.moneyicon);
        rent=v.findViewById(R.id.rent);
        permonth=v.findViewById(R.id.permonth);
        facilities=v.findViewById(R.id.facilities);
        houseowner=v.findViewById(R.id.houseowner);
        ownername=v.findViewById(R.id.ownername);
        book=v.findViewById(R.id.book);

        acicon=v.findViewById(R.id.acicon);
        wifiicon=v.findViewById(R.id.wifiicon);
        bedicon=v.findViewById(R.id.bedicon);
        sofaicon=v.findViewById(R.id.sofaicon);
        fridgeicon=v.findViewById(R.id.fridgeicon);
        powericon=v.findViewById(R.id.powericon);
        parkingicon=v.findViewById(R.id.parkingicon);
        messicon=v.findViewById(R.id.messicon);
        tvicon=v.findViewById(R.id.tvicon);


        ac=v.findViewById(R.id.ac);
        wifi=v.findViewById(R.id.wifi);
        bed=v.findViewById(R.id.bed);
        sofa=v.findViewById(R.id.sofa);
        fridge=v.findViewById(R.id.fridge);
        tv=v.findViewById(R.id.tv);
        mess=v.findViewById(R.id.mess);
        power=v.findViewById(R.id.power);
        parking=v.findViewById(R.id.parking);



        Typeface f1 = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Solid-900.otf" );

        Typeface f2 = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Regular-400.otf" );

        Typeface f4 = Typeface.createFromAsset(getActivity().getAssets(), "Oregon LDO Medium.ttf" );


        homeicon.setTypeface(f1);
        moneyicon.setTypeface(f2);
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
        rent.setTypeface(f4);
        facilities.setTypeface(f4);
        permonth.setTypeface(f4);
        houseowner.setTypeface(f4);
        ownername.setTypeface(f4);



        return v;
    }
}

