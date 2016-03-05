package pizzasearch.f0ris.com.pizzasearchassignment.Models;

import android.graphics.Bitmap;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class SearchItem implements Comparable<SearchItem>{

    public String name;
    public String photo_url;
    public Bitmap photo;

    public int distance;
    public float rating;


    @Override
    public int compareTo(SearchItem another) {
        return this.distance - another.distance;
    }
}
