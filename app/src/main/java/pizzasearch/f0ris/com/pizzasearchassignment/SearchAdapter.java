package pizzasearch.f0ris.com.pizzasearchassignment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import pizzasearch.f0ris.com.pizzasearchassignment.Models.SearchItem;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class SearchAdapter extends BaseAdapter {


    private final LayoutInflater inflator;
    private ArrayList<SearchItem> arrayList;
    private SparseIntArray rating_bg_colors = new SparseIntArray() {
        {
            put(8, Color.parseColor("#73CF42"));
            put(7, Color.parseColor("#C5DE35"));
            put(6, Color.parseColor("#FFC800"));
            put(5, Color.parseColor("#FF9600"));
            put(4, Color.parseColor("#FF6701"));
        }
    };

    public SearchAdapter(Context context) {
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayList = AppController.searchResultArray;

    }


    class ViewHolder {
        TextView name;
        ImageView photo;
        TextView distance;
        TextView rating;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflator.inflate(R.layout.search_result_item, parent, false);
            viewHolder = new ViewHolder();
//            viewHolder.photo = (CircleImageView) convertView.findViewById(R.id.profile_image);

            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.distance = (TextView) convertView.findViewById(R.id.distance);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.rating);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SearchItem item = arrayList.get(position);

        viewHolder.name.setText(item.name + item.name);
        viewHolder.distance.setText(String.format(AppController.getAppContext().getString(R.string.distance), String.valueOf(item.distance)));
        viewHolder.rating.setText(String.valueOf(item.rating));


        GradientDrawable rating_bg = (GradientDrawable) viewHolder.rating.getBackground();

        if (item.rating > 4)
            rating_bg.setColor(rating_bg_colors.get((int) Math.floor(item.rating)));
        else
            rating_bg.setColor(Color.RED);


        return convertView;
    }
}
