package appp.renthub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by pranj on 01-04-2018.
 */

public class SearchFrag extends Fragment implements AdapterView.OnItemClickListener,SearchView.OnQueryTextListener{
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] cityNameList;
    ArrayList<CityNames> arraylist = new ArrayList <CityNames>();

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

        return v;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchcity();
        return false;
    }

    private void searchcity() {

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
        editsearch.setQuery(queryString,true);
        searchcity();
    }
}
