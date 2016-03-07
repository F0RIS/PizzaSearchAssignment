package pizzasearch.f0ris.com.pizzasearchassignment;

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
            "&mode=typed";


}
