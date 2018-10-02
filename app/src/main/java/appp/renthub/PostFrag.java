package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import appp.renthub.dbutil.RenthubConstant;
import appp.renthub.dbutil.RenthubManager;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by pranj on 05-04-2018.
 */

public class PostFrag extends Fragment implements View.OnClickListener{
    TextView addressicon,pincodeicon,cityicon,roomsicon,facilitesicon,amounticon,postcaption;
    EditText inputaddress,inputpincode,inputamount;
    Spinner roomstatus,inputcity;
    Button inputsubmit,inputnext,inputprevious,inputnext2,uploadsubmit;
    LinearLayout pageone,pagetwo,pagethree;
    CheckBox sofa,bed,refrigerator,ac,wifi,invertor,parking,tv,mess;
    String sofaid,bedid,refigratorid,acid,wifiid,invertorid,parkingid,tvid,messid;


    String city,address,amount,pincode,status,email;

    JSONObject jsonObject;
    ProgressBar login_progress;
    ScrollView login_form;
    PROFILE profile;
    CardView card1,card2;
    Button listproperty;

    //image upload declaration
    Bitmap bitmap;

    boolean check = true;

    Button SelectImageGallery,UploadImageServer;

    ImageView imageView;

    ProgressDialog progressDialog ;


    String ImagePath = "image_path" ;


    SharedPreferences sp;
    SharedPreferences.Editor se;

    String ServerUploadPath ="https://rentzhub.co.in/formpicupload.php" ;



    @SuppressLint("ValidFragment")
    public PostFrag(PROFILE profile) {
        this.profile = profile;
    }

    public PostFrag() {
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.postfrag, container, false);

        imageView = view.findViewById(R.id.imageView);

        SelectImageGallery = view.findViewById(R.id.buttonSelect);

        SelectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_PICK);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });


        card1=view.findViewById(R.id.card1);
        card2=view.findViewById(R.id.card2);

        listproperty=view.findViewById(R.id.listproperty);

        postcaption=view.findViewById(R.id.postcaption);
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
        inputnext2 = view.findViewById(R.id.inputnext2);
        UploadImageServer = view.findViewById(R.id.buttonUpload);

        UploadImageServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageUploadToServerFunction();

            }
        });


        pagetwo = view.findViewById(R.id.pagetwo);
        pagethree=view.findViewById(R.id.pagethree);
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
        inputnext2.setTypeface(font);
        inputsubmit.setTypeface(font);
        listproperty.setTypeface(font);


        inputnext.setOnClickListener(this);
        inputnext2.setOnClickListener(this);
        inputprevious.setOnClickListener(this);
        inputsubmit.setOnClickListener(this);
        listproperty.setOnClickListener(this);

        return view;
    }

    //image upload code
    @Override
    public void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void ImageUploadToServerFunction(){
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStreamObject ;

            byteArrayOutputStreamObject = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStreamObject);

            byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

            final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

            class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

                @Override
                protected void onPreExecute() {

                    super.onPreExecute();

                    progressDialog = ProgressDialog.show(getActivity(),"Image is Uploading","Please Wait",false,false);
                }
                @Override
                protected void onPostExecute(String string1) {

                    super.onPostExecute(string1);

                    // Dismiss the progress dialog after done uploading.
                    progressDialog.dismiss();

                    profile.setPicname(ConvertImage);

                    if(string1.equalsIgnoreCase("success")){
                        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Image Uploaded Successfully.");
                        builder.show();
                    }
                    else {
                        if(string1.contains("error1")){
                            AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                            builder.setIcon(R.mipmap.ic_launcher_round);
                            builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                            builder.setMessage("Error in updating profile pic. Retry with small Image size.");
                            builder.show();
                        }
                        if(string1.contains("error2")){
                            AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                            builder.setIcon(R.mipmap.ic_launcher_round);
                            builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                            builder.setMessage("Error in connection.");
                            builder.show();
                        }
                    }
                }


                @Override
                protected String doInBackground(Void... params) {

                    ImageProcessClass imageProcessClass = new ImageProcessClass();

                    HashMap<String, String> HashMapParams = new HashMap<String, String>();

                    HashMapParams.put("address", address);

                    HashMapParams.put(ImagePath, ConvertImage);

                    String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

                    return FinalData;
                }
            }
            AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

            AsyncTaskUploadClassOBJ.execute();
        }
        else
        {
            AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
            builder.setIcon(R.mipmap.ic_launcher_round);
            builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
            builder.setMessage("Select an Image");
            builder.show();
        }
    }
    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null)
                    {
                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check==true)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }
    }




    // image upload code ends

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

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

        }
    }
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.listproperty)
        {
            card1.setVisibility(View.GONE);
            card2.setVisibility(View.VISIBLE);
        }

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
                }
                if (TextUtils.isEmpty(address)) {
                    inputaddress.setError("Enter Address");
                    inputaddress.requestFocus();
                }
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

            if (v.getId()== R.id.inputnext2)
            {
                pagethree.setVisibility(View.VISIBLE);
                pagetwo.setVisibility(View.GONE);
                pageone.setVisibility(View.GONE);
            }

            if (v.getId() == R.id.inputprevious) {
                pageone.setVisibility(View.VISIBLE);
                pagetwo.setVisibility(View.GONE);
                pagethree.setVisibility(View.GONE);
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
                    jsonObject.put("email",profile.getEmail());
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
                    clearform();
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Place added successfully!");
                    builder.show();
                    inputnext2.setVisibility(View.VISIBLE);
                }
                else{
                    if(response.toLowerCase().contains("formerror0")){
                        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Address already registered.");
                        builder.show();
                    }
                    if(response.toLowerCase().contains("formerror1")){
                        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Server Error!");
                        builder.show();
                    }
                    if(response.toLowerCase().contains("formerror2")){
                        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Internet Error.Check Connection!");
                        builder.show();
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
                builder.setMessage("Connection error!");
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
}
