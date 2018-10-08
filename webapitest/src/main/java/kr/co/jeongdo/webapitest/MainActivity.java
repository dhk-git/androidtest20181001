package kr.co.jeongdo.webapitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.button);
        final TextView textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                        Request.Method.GET
                        ,"http://jims.jeongdo.co.kr/jims3/api/workact/workorder?startdt=2018-09-01&enddt=2018-10-10"
                        , null
                        , new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject item = response.getJSONObject(1);
                            textView.setText(item.getString("ITEM_NAME"));
                            ListView listView = (ListView) findViewById(R.id.listView);

                            ArrayList<Listviewitem> data = new ArrayList<>();
                            JSONObject item1;
                            for (int i = 0; i<response.length();i++){
                                item1 = response.getJSONObject(i);
                                data.add(new Listviewitem(item1.getString("ITEM_CODE"), item1.getString("ITEM_NAME") ));
                            }
                            ListviewAdapter adapter = new ListviewAdapter(MainActivity.this, R.layout.item , data);
                            listView.setAdapter(adapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage());
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

        Button postTestButton = findViewById(R.id.postTestButton);
        postTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PostTestActivity.class);
                startActivity(intent);
            }
        });

    }
}
