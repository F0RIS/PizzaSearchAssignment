package pizzasearch.f0ris.com.pizzasearchassignment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by F0RIS on 07.03.2016.
 */
public class GPSDealer implements LocationListener {
    private LocationManager locationManager;
    private Context context;

    private static double longitude;
    private static double latitude;
    private static boolean emulateNY = false;
    private static boolean locationDetected = false;

    public static boolean isLocationDetected() {
        return locationDetected;
    }

    public GPSDealer() {
        context = AppController.getAppContext();

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000, 1, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        locationDetected = true;

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Toast.makeText(context, "Location detected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
        Toast.makeText(context, "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(context, "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }

    public void printLL() {
        String msg = " Latitude: " + latitude
                + " Longitude: " + longitude;

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void emulateNYLocation(boolean val) {
        emulateNY = val;
    }

    public static double getLatitude() {
        if (emulateNY)
            return 40.7127837;
        else
            return latitude;
    }

    public static double getLongitude() {
        if (emulateNY)
            return -74.0059413;
        else
            return longitude;
    }

}
