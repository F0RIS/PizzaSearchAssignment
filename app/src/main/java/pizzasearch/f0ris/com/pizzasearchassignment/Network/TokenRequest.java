package pizzasearch.f0ris.com.pizzasearchassignment.Network;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pizzasearch.f0ris.com.pizzasearchassignment.Interfaces.IRequest;

/**
 * Created by F0RIS on 05.03.2016.
 */

public class TokenRequest extends JsonObjectRequest {

    private static IRequest callback;

    static Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
                VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                volleyError = error;
            }

            try {
                System.out.println(volleyError.getMessage());
                callback.onError(new JSONObject(volleyError.getMessage()).getString("error"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    static Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            System.out.println(response);
            callback.onResponse(response);
        }
    };

    public TokenRequest(int method, String url, JSONObject jsonRequest, IRequest callback) {
        super(method, url, jsonRequest, responseListener, TokenRequest.errorListener);
        TokenRequest.callback = callback;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }
}

