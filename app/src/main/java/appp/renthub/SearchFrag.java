package appp.renthub;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.os.Build.VERSION_CODES.JELLY_BEAN;

/**
 * Created by pranj on 01-04-2018.
 */

public class SearchFrag extends Fragment implements AdapterView.OnItemClickListener,SearchView.OnQueryTextListener{
    ListView list,searchlist;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] cityNameList;
    ArrayList<CityNames> arraylist = new ArrayList <CityNames>();
    ArrayList<SEARCHRESULT> searchresults=new ArrayList<>();
    RESULTADAPTER resultadapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.searchfrag,container,false);
        cityNameList = new String[]{"Lucknow", "Kanpur",
                "Delhi", "Gurgaon", "Mumbai", "Pune", "Kolkata", "Chennai", "Bangalore", "Ahmedabad", "Hyderabad",
                "Jaipur", "Ghaziabad", "Noida", "Patna", "Chandigarh", "Allahabad"};

        // Locate the ListView in listview_main.xml
        list =  v.findViewById(R.id.listview);

        for (int i = 0; i < cityNameList.length; i++) {
            CityNames cityNames = new CityNames(cityNameList[i]);
            // Binds all strings into an array
            arraylist.add(cityNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getActivity(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) v.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        searchlist=v.findViewById(R.id.searchlist);


        return v;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchcity(query);
        return false;
    }

    private void searchcity(final String query) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_SEARCH_CITY, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                if (response.toLowerCase().contains("error")) {

                } else {
                    try {
                        searchresults.clear();
                        JSONObject jsonObject=new JSONObject(response);
                        int count=Integer.parseInt(jsonObject.getString("count").toString());
                        if(count>0){
                            while (count>0){
                                count--;
                                String data[]=jsonObject.get(String.valueOf(count)).toString().split("--%--");
                                searchresults.add(new SEARCHRESULT(data[0],data[1],data[2]));
                            }
                            Toast.makeText(getActivity(), searchresults.toString(), Toast.LENGTH_SHORT).show();
                            resultadapter=new RESULTADAPTER(getActivity(),searchresults);
                            searchlist.setAdapter(resultadapter);
                            searchlist.setVisibility(View.VISIBLE);
                        }
                        else{

                        }
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Snackbar snackbar = Snackbar
                        .make(getView(), "Connection error! Retry", Snackbar.LENGTH_LONG);
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
                params.put("city",query);
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        if (newText.equalsIgnoreCase("")) {
            list.setVisibility(View.GONE);
        }else {
            list.setVisibility(View.VISIBLE);
        }
        return false;
    }


    @Override
    public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
        String queryString = parent.getItemAtPosition(position).toString();
        editsearch.setQuery(queryString,false);
        list.setVisibility(View.INVISIBLE);
        editsearch.clearFocus();
        searchcity(queryString);
    }


}
