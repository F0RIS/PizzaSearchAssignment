package pizzasearch.f0ris.com.pizzasearchassignment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import pizzasearch.f0ris.com.pizzasearchassignment.Adapters.SearchAdapter;
import pizzasearch.f0ris.com.pizzasearchassignment.AppController;
import pizzasearch.f0ris.com.pizzasearchassignment.Network.RequestDealer;
import pizzasearch.f0ris.com.pizzasearchassignment.R;
import pizzasearch.f0ris.com.pizzasearchassignment.Views.LoadMoreListView;

public class SearchResultActivity extends AppCompatActivity {

    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchAdapter = new SearchAdapter(this);

        final LoadMoreListView listView = (LoadMoreListView) findViewById(R.id.search_result_list_view);
        listView.setAdapter(searchAdapter);

        listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            public void onLoadMore() {
                RequestDealer.searchPizzaBar(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        searchAdapter.notifyDataSetChanged();
                        listView.onLoadMoreComplete();
                        return true;
                    }
                });
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResultActivity.this, VenueDetailActivity.class);
                intent.putExtra("venue",AppController.searchResultArray.get(position));
                startActivity(intent);
            }
        });
    }
}