package pizzasearch.f0ris.com.pizzasearchassignment;

import android.graphics.Color;
import android.util.SparseIntArray;

/**
 * Created by F0RIS on 05.03.2016.
 */
public interface Constants {

    int FETCH_COUNT = 10;

    String client_id = "F5JYW4HJ0MV4RSMSXK3OEQLSRUIHFY2CPPWHY4HR4UPDVGB0";
    String client_secret = "1K4PUIOD0CCLPK3TIZIZGLEDE4XAC05AC1U45FYRYW1J53KT";

    String PIZZA_BAR_QUERY_URL = "https://api.foursquare.com/v2/search/recommendations?"
            + "&client_id=" + client_id
            + "&client_secret=" + client_secret
            + "&v=20130815&locale=ru&m=foursquare" +
            "&categoryId=4bf58dd8d48988d1ca941735" + //pizzabar category
            "&mode=typed&intent=checkin";


    SparseIntArray rating_bg_colors = new SparseIntArray() {
        {
            put(8, Color.parseColor("#73CF42"));
            put(7, Color.parseColor("#C5DE35"));
            put(6, Color.parseColor("#FFC800"));
            put(5, Color.parseColor("#FF9600"));
            put(4, Color.parseColor("#FF6701"));
        }
    };

}
