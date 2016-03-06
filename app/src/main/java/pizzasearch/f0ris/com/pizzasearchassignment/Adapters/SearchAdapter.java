package pizzasearch.f0ris.com.pizzasearchassignment.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import pizzasearch.f0ris.com.pizzasearchassignment.AppController;
import pizzasearch.f0ris.com.pizzasearchassignment.Models.SearchItem;
import pizzasearch.f0ris.com.pizzasearchassignment.R;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class SearchAdapter extends BaseAdapter {


    private final LayoutInflater inflater;
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

    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public SearchAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayList = AppController.searchResultArray;

        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
//                .showImageForEmptyUri(fallbackImage)
//                .showImageOnFail(fallbackImage)
//                .showImageOnLoading(fallbackImage)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        imageLoader.init(config);

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

        final ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.search_result_item, parent, false);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);

            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.distance = (TextView) convertView.findViewById(R.id.distance);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.rating);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final SearchItem item = arrayList.get(position);

        viewHolder.name.setText(item.name);
        viewHolder.distance.setText(String.format(AppController.getAppContext().getString(R.string.distance), String.valueOf(item.distance)));
        viewHolder.rating.setText(String.valueOf(item.rating));


        imageLoader.displayImage(item.photo_url,viewHolder.photo, options);

        GradientDrawable rating_bg = (GradientDrawable) viewHolder.rating.getBackground();

        if (item.rating > 4)
            rating_bg.setColor(rating_bg_colors.get((int) Math.floor(item.rating)));
        else
        if (item.rating == 0) //no rating
            viewHolder.rating.setVisibility(View.GONE);
        else //low rating
            rating_bg.setColor(Color.RED);


        return convertView;
    }
}
