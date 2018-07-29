package com.example.sarveshagrawal.canadiandetails.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sarveshagrawal.canadiandetails.Adapter.CanadaListAdapter;
import com.example.sarveshagrawal.canadiandetails.Data.Canada_List_Model;
import com.example.sarveshagrawal.canadiandetails.Data.Constants;
import com.example.sarveshagrawal.canadiandetails.Data.Prefrences;
import com.example.sarveshagrawal.canadiandetails.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Canada_List_Model> mList = new ArrayList();
    ListView listView;
    TextView title;
    ProgressBar progressBar;
    CanadaListAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onResume() {
        super.onResume();
        if (!checkConnect()) { // Checking Internet Connection.
            callAlertDilalog();
        }
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        listView = (ListView) findViewById(R.id.listView);

        if (savedInstanceState == null || !savedInstanceState.containsKey("key")) { // Searching List in SaveInstanceState
            getAPIList(); // Fetching JSON Data.
        } else {
            title.setText(Prefrences.getTittle(MainActivity.this));
            mList = savedInstanceState.getParcelableArrayList("key");
        }

        mSwipeRefreshLayout.setColorSchemeResources(R.color.titleTheme);

        adapter = new CanadaListAdapter(mList, MainActivity.this);
        listView.setDivider(null);
        listView.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh list Fatch new Data.
                getAPIList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getAPIList() {
        progressBar.setVisibility(View.VISIBLE);

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.GET, Constants.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Log.d("response", response);
                mList.clear();
                try {
                    JSONObject obj = new JSONObject(response);
                    title.setText(obj.optString("title"));
                    Prefrences.setTittle(obj.optString("title"),MainActivity.this);
                    JSONArray ary = obj.getJSONArray("rows");
                    for (int x = 0; x < ary.length(); x++) {
                        JSONObject object = ary.optJSONObject(x);
                        Canada_List_Model model = new Canada_List_Model(object.optString("title"),
                                object.optString("description"),object.optString("imageHref"));

                        mList.add(model);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);
                String message = "Please check internet connection and tryagain.";
                progressBar.setVisibility(View.GONE);
                if (volleyError instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (volleyError instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (volleyError instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
            }
        });

        sr.setRetryPolicy(new
                DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(sr);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", mList);
        super.onSaveInstanceState(outState);
    }

    private void callAlertDilalog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert!");
        builder.setMessage("Please check your Internet Connection.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean checkConnect() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
