package pizzasearch.f0ris.com.pizzasearchassignment.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import pizzasearch.f0ris.com.pizzasearchassignment.AppController;
import pizzasearch.f0ris.com.pizzasearchassignment.GPSDealer;
import pizzasearch.f0ris.com.pizzasearchassignment.Network.RequestDealer;
import pizzasearch.f0ris.com.pizzasearchassignment.R;
import pizzasearch.f0ris.com.pizzasearchassignment.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Handler.Callback searchCallback;
    private ProgressDialog progressDialog;
    private GPSDealer gpsDealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        searchCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (progressDialog != null)
                    progressDialog.dismiss();
                startActivity(new Intent(MainActivity.this, SearchResultActivity.class));
                return true;
            }
        };

        gpsDealer = new GPSDealer();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:

                if (!Utils.isNetworkConnected()) {
                    Utils.loadResultsFromDisk(searchCallback);
                    Toast.makeText(this, R.string.loaded_from_cache, Toast.LENGTH_SHORT).show();
                    break;
                }

                boolean emulateNY = ((CheckBox) findViewById(R.id.emulate_NY_checkBox)).isChecked();
                GPSDealer.emulateNYLocation(emulateNY);

                if (GPSDealer.isLocationDetected() || emulateNY) {
                    progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage(getString(R.string.search));
                    progressDialog.show();

                    AppController.searchResultArray.clear(); //deleting previous search result
                    RequestDealer.searchPizzaBar(searchCallback);
                } else
                    Toast.makeText(this, getString(R.string.no_location), Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
