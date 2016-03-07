package pizzasearch.f0ris.com.pizzasearchassignment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import pizzasearch.f0ris.com.pizzasearchassignment.Models.SearchItem;

/**
 * Created by F0RIS on 22.11.2015.
 */
public class Utils {

    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) AppController.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return false;
        }
        return true;
    }

    private static String getCacheFileName() throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = AppController.getAppContext()
                .getPackageManager()
                .getPackageInfo(AppController.getAppContext().getPackageName(), 0);
        return packageInfo.applicationInfo.dataDir + "/search_cache.tmp";
    }

    public static void storeResultsOnDisk() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream(getCacheFileName());
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(AppController.searchResultArray);
                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public static void loadResultsFromDisk(final Handler.Callback searchCallback) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    FileInputStream fis = new FileInputStream(getCacheFileName());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    AppController.searchResultArray = (ArrayList<SearchItem>) ois.readObject();
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                searchCallback.handleMessage(null);
            }
        }.execute();


    }
}
