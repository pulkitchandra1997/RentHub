package appp.renthub;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by pranj on 05-04-2018.
 */

public class PostFrag extends Fragment implements View.OnClickListener{
    TextView addressicon,pincodeicon,cityicon,roomsicon,facilitesicon,amounticon;
    EditText inputaddress,inputpincode,inputamount;
    Spinner roomstatus,inputcity;
    Button inputsubmit,inputnext,inputprevious;
    LinearLayout pageone,pagetwo;
    CheckBox sofa,bed,refigrator,ac;
    Activity activity = getActivity();
    String city,address,amount,pincode,status;
    private boolean doubleBackToExitPressedOnce;


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.postfrag, container, false);
        inputaddress = view.findViewById(R.id.inputaddress);
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
        refigrator = view.findViewById(R.id.refigrator);
        ac = view.findViewById(R.id.ac);
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


        return view;
    }
   /* @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
          //  super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(activity, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }*/


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


        }

    }
