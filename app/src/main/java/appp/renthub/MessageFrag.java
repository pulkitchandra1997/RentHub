package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
*
        * Created by pranj on 01-04-2018.
*/


public class MessageFrag extends Fragment implements View.OnClickListener{



    ArrayList<INBOXRESULT> inboxresults=new ArrayList <>();
    ArrayList<SENTITEMSRESULT> sentitemsresults=new ArrayList <>();


    INBOXADAPTER inboxadapter;
    SENTITEMSADAPTER sentitemsadapter;
    ListView inboxlist,sentlist;
    PROFILE profile;
    ProgressBar login_progress;
    TextView inboxtext,senttext;
    String name;
    String data[];
    CardView inboxbtn,sentitembtn;

    @SuppressLint("ValidFragment")
    public MessageFrag(PROFILE profile) {
        this.profile = profile;
    }

    public MessageFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.messagefrag,container,false);





        inboxbtn=v.findViewById(R.id.inboxbtn);
        sentitembtn=v.findViewById(R.id.sentitembtn);

        inboxtext=v.findViewById(R.id.inboxtext);
        senttext=v.findViewById(R.id.senttext);


        login_progress=v.findViewById(R.id.login_progress);

        inboxlist =  v.findViewById(R.id.inboxlist);
        sentlist=v.findViewById(R.id.sentlist);


        inboxbtn.setOnClickListener((View.OnClickListener) this);
        sentitembtn.setOnClickListener((View.OnClickListener) this);


        sentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ViewSentMessage.class);
                intent.putExtra("sentresult",sentitemsresults.get(position));
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        inboxlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ViewInboxMessage.class);
                intent.putExtra("inboxresult",inboxresults.get(position));
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        fillinboxmsg();

        fillsentmsg();

        return v;
    }
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


    public void fillinboxmsg()
    {
        showProgress(true);
        inboxlist.setAdapter(null);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_INBOXMESSAGES, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                if (response.toLowerCase().contains("messageerror")) {
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Error in connection");
                    builder.show();
                } else {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        int count=Integer.parseInt(jsonObject.getString("count").toString());
                        if(count>0){
                            while (count>0){


                                count--;
                                String data[]=jsonObject.get(String.valueOf(count)).toString().split("--%--");

                                inboxresults.add(new INBOXRESULT(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9]));
                            }



                            inboxadapter=new INBOXADAPTER(getActivity(),inboxresults);
                            inboxlist.setAdapter(inboxadapter);
                        }
                        else{

                        }
                    } catch (Exception e) {
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
                builder.setMessage("Error in connection");
                builder.show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("email",profile.getEmail());
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }


    public void fillsentmsg()
    {
        showProgress(true);
        sentlist.setAdapter(null);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_SENTMESSAGES, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                if (response.toLowerCase().contains("messageerror")) {
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Error in connection");
                    builder.show();
                } else {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        int count=Integer.parseInt(jsonObject.getString("count").toString());
                        if(count>0){
                            while (count>0){


                                count--;
                                String data[]=jsonObject.get(String.valueOf(count)).toString().split("--%--");

                                sentitemsresults.add(new SENTITEMSRESULT(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9]));
                            }


                            sentitemsadapter=new SENTITEMSADAPTER(getActivity(),sentitemsresults);
                            sentlist.setAdapter(sentitemsadapter);
                        }
                        else{

                        }
                    } catch (Exception e) {
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
                builder.setMessage("Error in connection");
                builder.show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("email",profile.getEmail());
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.inboxbtn)
        {
            sentlist.setVisibility(View.GONE);
            inboxlist.setVisibility(View.VISIBLE);
            inboxbtn.setCardBackgroundColor(Color.WHITE);
            inboxtext.setTextColor(Color.parseColor("#424242"));
            sentitembtn.setCardBackgroundColor(Color.parseColor("#3d84ee"));
            senttext.setTextColor(Color.WHITE);
        }
        if (v.getId()==R.id.sentitembtn)
        {
            inboxlist.setVisibility(View.GONE);
            sentlist.setVisibility(View.VISIBLE);
            sentitembtn.setCardBackgroundColor(Color.WHITE);
            senttext.setTextColor(Color.parseColor("#424242"));
            inboxbtn.setCardBackgroundColor(Color.parseColor("#3d84ee"));
            inboxtext.setTextColor(Color.WHITE);
        }
    }
}


