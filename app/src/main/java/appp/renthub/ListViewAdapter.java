package appp.renthub;

/**
 * Created by pranj on 08-04-2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<CityNames> cityNamesList = null;
    private ArrayList<CityNames> arraylist;

    public ListViewAdapter(Context context, List<CityNames> cityNamesList) {
        mContext = context;
        this.cityNamesList = cityNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<CityNames>();
        this.arraylist.addAll(cityNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return cityNamesList.size();
    }

    @Override
    public CityNames getItem(int position) {
        return cityNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();

            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(cityNamesList.get(position).getCityname());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        cityNamesList.clear();
        if (charText.length() == 0) {
            cityNamesList.addAll(arraylist);
        } else {
            for (CityNames wp : arraylist) {
                if (wp.getCityname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    cityNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}