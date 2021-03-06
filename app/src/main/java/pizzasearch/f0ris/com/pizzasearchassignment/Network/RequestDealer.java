package pizzasearch.f0ris.com.pizzasearchassignment.Network;

import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pizzasearch.f0ris.com.pizzasearchassignment.AppController;
import pizzasearch.f0ris.com.pizzasearchassignment.Constants;
import pizzasearch.f0ris.com.pizzasearchassignment.GPSDealer;
import pizzasearch.f0ris.com.pizzasearchassignment.Interfaces.IRequest;
import pizzasearch.f0ris.com.pizzasearchassignment.JsonParser;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class RequestDealer {

    final static String PIZZA_BAR_QUERY_URL = "https://api.foursquare.com/v2/search/recommendations?"
            + "&client_id=F5JYW4HJ0MV4RSMSXK3OEQLSRUIHFY2CPPWHY4HR4UPDVGB0"
            + "&client_secret=1K4PUIOD0CCLPK3TIZIZGLEDE4XAC05AC1U45FYRYW1J53KT"
            + "&v=20130815&locale=ru&m=foursquare" +
            "&query=pizza&keywords=pizza,pizzabar" +
            "&sortByDistance=1";


    public static void searchPizzaBar(final Handler.Callback searchCallback) {

        //setting request params
        String requestUrl = PIZZA_BAR_QUERY_URL
                + "&limit=" + String.valueOf(AppController.searchResultArray.size() + Constants.FETCH_COUNT)
                + "&ll=" + GPSDealer.getLatitude() + "%2C" + GPSDealer.getLongitude();

        Map<String, String> params = new HashMap<>();
        makeAdvancedRequest(Request.Method.GET, requestUrl, params, new IParseFunction() {
            @Override
            public void parse(JSONObject response) {
                AppController.searchResultArray.clear();
                JsonParser.parseVenue(response);
                searchCallback.handleMessage(null);
            }
        });

    }


    interface IParseFunction {
        void parse(JSONObject response);
    }

    protected static void makeAdvancedRequest(int method, String url, Map<String, String> params, final IParseFunction parseFunction) {

        System.gc();
        JsonObjectRequest jsonObjectRequest = new TokenRequest(
                method, url, new JSONObject(params), new IRequest() {

            @Override
            public void onError(String errorMessage) {
                System.out.println("onError:" + errorMessage);
            }

            @Override
            public void onResponse(JSONObject response) {
                System.out.println("onResponse");
                if (parseFunction != null)
                    parseFunction.parse(response);
            }
        });

        AppController.getRequestQueue().add(jsonObjectRequest);
    }

}
