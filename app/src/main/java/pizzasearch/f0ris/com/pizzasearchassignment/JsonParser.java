package pizzasearch.f0ris.com.pizzasearchassignment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pizzasearch.f0ris.com.pizzasearchassignment.Models.SearchItem;

/**
 * Created by F0RIS on 05.03.2016.
 */
public class JsonParser {


    public static void parseVenue(JSONObject response) {

        try {
            JSONArray jsonArray = response.getJSONObject("response").getJSONObject("group").getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                SearchItem item = new SearchItem();

                JSONObject parentObject = (JSONObject) jsonArray.get(i);
                JSONObject object = parentObject.getJSONObject("venue");

                item.name = object.getString("name");
                item.rating = Float.parseFloat(object.optString("rating", "0"));

                item.distance = object.getJSONObject("location").getInt("distance");

                JSONArray jsonArray1 = object.getJSONObject("location").getJSONArray("formattedAddress");

                try {
                    item.address = (String) jsonArray1.get(0);
                    item.address += ", " + jsonArray1.get(1);
                } catch (Exception e) {
                }

                item.category = ((JSONObject) object.getJSONArray("categories").get(0)).getString("name");

                try {
                    object = ((JSONObject) jsonArray.get(i)).getJSONObject("photo");
                    item.photo_url = object.getString("prefix") + "240x240" + object.getString("suffix");
                } catch (JSONException e) {
                }

                AppController.searchResultArray.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
