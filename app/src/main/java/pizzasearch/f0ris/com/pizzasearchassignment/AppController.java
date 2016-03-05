package pizzasearch.f0ris.com.pizzasearchassignment;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class AppController extends Application {

    private static Context context;
    private static AppController instance;
    private static RequestQueue requestQueue;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppController.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AppController.context;
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
