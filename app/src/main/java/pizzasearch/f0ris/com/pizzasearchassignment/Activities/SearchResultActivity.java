package pizzasearch.f0ris.com.pizzasearchassignment.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import pizzasearch.f0ris.com.pizzasearchassignment.R;
import pizzasearch.f0ris.com.pizzasearchassignment.SearchAdapter;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SearchAdapter searchAdapter = new SearchAdapter(this);

        ((ListView) findViewById(R.id.search_result_list_view)).setAdapter(searchAdapter);
    }

}
