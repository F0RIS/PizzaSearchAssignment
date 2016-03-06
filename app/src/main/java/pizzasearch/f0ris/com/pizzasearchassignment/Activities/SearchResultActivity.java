package pizzasearch.f0ris.com.pizzasearchassignment.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import pizzasearch.f0ris.com.pizzasearchassignment.AppController;
import pizzasearch.f0ris.com.pizzasearchassignment.Models.SearchItem;
import pizzasearch.f0ris.com.pizzasearchassignment.R;
import pizzasearch.f0ris.com.pizzasearchassignment.Adapters.SearchAdapter;
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

        ((ListView) findViewById(R.id.search_result_list_view)).setAdapter(searchAdapter);

        ((LoadMoreListView) findViewById(R.id.search_result_list_view)).setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            public void onLoadMore() {
                // Do the work to load more items at the end of list here
                new LoadDataTask().execute();
            }
        });
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {


            // Simulates a background task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            AppController.searchResultArray.add(new SearchItem());
            AppController.searchResultArray.add(new SearchItem());
            AppController.searchResultArray.add(new SearchItem());
            AppController.searchResultArray.add(new SearchItem());

            // We need notify the adapter that the data have been changed
            searchAdapter.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            ((LoadMoreListView) findViewById(R.id.search_result_list_view)).onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            ((LoadMoreListView) findViewById(R.id.search_result_list_view)).onLoadMoreComplete();
        }
    }

}
