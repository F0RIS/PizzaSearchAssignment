package pizzasearch.f0ris.com.pizzasearchassignment.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;

import pizzasearch.f0ris.com.pizzasearchassignment.Models.SearchItem;
import pizzasearch.f0ris.com.pizzasearchassignment.R;

public class VenueDetailActivity extends AppCompatActivity {

    private SearchItem curVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        curVenue = (SearchItem) getIntent().getExtras().getSerializable("venue");

        fillData();

    }

    private void fillData() {

        ((TextView) findViewById(R.id.name)).setText(curVenue.name);
        ((TextView) findViewById(R.id.category)).setText(curVenue.category);
        ((TextView) findViewById(R.id.address)).setText(curVenue.address);
        ((TextView) findViewById(R.id.address)).setText(curVenue.address);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(curVenue.photo_url, ((ImageView) findViewById(R.id.photo)));


    }

}
