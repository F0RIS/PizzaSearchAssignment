package pizzasearch.f0ris.com.pizzasearchassignment.Interfaces;

import org.json.JSONObject;

/**
 * Created by F0RIS on 05.03.2016.
 */
public interface IRequest {
    void onError(String message);
    void onResponse(JSONObject response);
}
