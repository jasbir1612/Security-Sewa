package com.developers.roadsecure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Hospital extends AppCompatActivity {
    private GPSTracker gps=new GPSTracker(this);
    private double longitude=gps.getLongitude();
    private double latitude=gps.getLatitude();
    private String url="https://api.foursquare.com/v2/venues/explore?client_id=A5D2AJCGQOUMLIMACCPQPECUKHV3BL1FHQO4AHMQBG0OUVYE&client_secret=2KJ0ILGRGICNRQXKBMH5TP0YLQXQKG2AN2WN0VF1CARBD0BL&v=20130815&ll="+latitude+","+longitude+"&query=hospital";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
//mil gyi error ruk
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject root = new JSONObject(response);
                            JSONArray summary=root.optJSONArray("reasons");
                            System.out.println(summary);
//
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(Hospital.this).add(request);

    }
}
