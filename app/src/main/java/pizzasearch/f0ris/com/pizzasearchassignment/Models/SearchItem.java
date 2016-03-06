package pizzasearch.f0ris.com.pizzasearchassignment.Models;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class SearchItem implements Comparable<SearchItem>, Serializable {

    public String name;
    public String photo_url;
    public String category;
    public String address;


    public int distance;
    public float rating;

    @Override
    public int compareTo(@NonNull SearchItem another) {
        return this.distance - another.distance;
    }
}
