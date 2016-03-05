package pizzasearch.f0ris.com.pizzasearchassignment;

import android.os.Handler;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pizzasearch.f0ris.com.pizzasearchassignment.Interfaces.IRequest;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class RequestDealer {

//    protected static final String API_URL = "http://travel.bomavi.com.ua/api/";

    public static void searchPizzaBar(final Handler.Callback searchCallback) {

        String requestUrl = "https://api.foursquare.com/v2/search/recommendations?client_id=F5JYW4HJ0MV4RSMSXK3OEQLSRUIHFY2CPPWHY4HR4UPDVGB0&client_secret=1K4PUIOD0CCLPK3TIZIZGLEDE4XAC05AC1U45FYRYW1J53KT&v=20130815&locale=ru&m=foursquare&query=Пиццерия&limit=30&mode=typed&nearGeoId=72057594038637653&ll=47.97932583355205%2C37.80326843261719&wsid=DUHPHALX3SWNNW0VPQUHTINNUOLBN1";


        Map<String, String> params = new HashMap<String, String>();

        makeAdvancedRequest(Request.Method.GET, requestUrl, params, new IParseFunction() {
            @Override
            public void parse(JSONObject response) {
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
                /*
                if (answerHandler != null) {
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("error", errorMessage);
                    msg.setData(bundle);
                    msg.what = Constants.ERROR;
                    BaseRequestDealer.answerHandler.sendMessage(msg);
                }*/
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
