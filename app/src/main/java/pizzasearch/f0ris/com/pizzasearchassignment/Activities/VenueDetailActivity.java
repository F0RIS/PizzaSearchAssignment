package pizzasearch.f0ris.com.pizzasearchassignment.Activities;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;

import pizzasearch.f0ris.com.pizzasearchassignment.Constants;
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

        TextView ratingView = (TextView) findViewById(R.id.rating);
        ratingView.setText(String.valueOf(curVenue.rating));

        GradientDrawable rating_bg = (GradientDrawable) ratingView.getBackground();

        if (curVenue.rating > 4)
            rating_bg.setColor(Constants.rating_bg_colors.get((int) Math.floor(curVenue.rating)));
        else if (curVenue.rating == 0) //no rating
            ratingView.setVisibility(View.GONE);
        else //low rating
            rating_bg.setColor(Color.RED);

    }

}
