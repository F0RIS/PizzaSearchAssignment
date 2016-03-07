package pizzasearch.f0ris.com.pizzasearchassignment;

import android.graphics.Color;
import android.util.SparseIntArray;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by F0RIS on 05.03.2016.
 */
public interface Constants {

    int FETCH_COUNT = 10;

    SparseIntArray rating_bg_colors = new SparseIntArray() {
        {
            put(8, Color.parseColor("#73CF42"));
            put(7, Color.parseColor("#C5DE35"));
            put(6, Color.parseColor("#FFC800"));
            put(5, Color.parseColor("#FF9600"));
            put(4, Color.parseColor("#FF6701"));
        }
    };

    DisplayImageOptions imageLoaderOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisc(true)
            .cacheInMemory(true)
            .resetViewBeforeLoading(true)
            .showImageForEmptyUri(R.drawable.no_photo)
//                .showImageOnFail(fallbackImage)
//                .showImageOnLoading(fallbackImage)
            .build();

}
