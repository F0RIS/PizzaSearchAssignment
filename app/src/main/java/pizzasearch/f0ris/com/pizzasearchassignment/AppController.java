package pizzasearch.f0ris.com.pizzasearchassignment;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import pizzasearch.f0ris.com.pizzasearchassignment.Models.SearchItem;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class AppController extends Application {

    private static AppController instance;
    private static RequestQueue requestQueue;
    public static ArrayList<SearchItem> searchResultArray = new ArrayList<>(50);


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    public static AppController getInstance() {
        return AppController.instance;
    }

    public static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(AppController.getAppContext());
        }

        return requestQueue;
    }
}
