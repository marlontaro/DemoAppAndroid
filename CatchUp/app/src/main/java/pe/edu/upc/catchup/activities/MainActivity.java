package pe.edu.upc.catchup.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.adapters.SourcesAdapter;
import pe.edu.upc.catchup.models.Source;
import pe.edu.upc.catchup.network.NewsApi;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity {
    RecyclerView sourcesRecyclerView;
    RecyclerView.LayoutManager sourcesLayoutManager;
    SourcesAdapter sourcesAdapter;
    List<Source> sources;
    private static String TAG = "CatchUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sourcesRecyclerView = (RecyclerView) findViewById(R.id.sourcesRecyclerView);
        sourcesLayoutManager = new LinearLayoutManager(this);
        sourcesAdapter = new SourcesAdapter();
        sources = new ArrayList<>();
        sourcesAdapter.setSources(sources);
        sourcesRecyclerView.setLayoutManager(sourcesLayoutManager);
        sourcesRecyclerView.setAdapter(sourcesAdapter);
        updateSources();
    }

    private void updateSources() {
        AndroidNetworking.get(NewsApi.SOURCES_URL)
                .addQueryParameter("language", "en")
                .setPriority(Priority.LOW)
                .setTag(TAG)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("error")) {
                                Log.d(TAG, response.getString("message"));
                                return;
                            }
                            sources = Source.build(response.getJSONArray("sources"));
                            sourcesAdapter.setSources(sources);
                            sourcesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Log.d(TAG, "Orientation Changed:" + Integer.toString(newConfig.orientation));
//        //spanCount = (newConfig.orientation == ORIENTATION_LANDSCAPE) ? 3 : 2;
//        ((GridLayoutManager)sourcesRecyclerView.getLayoutManager()).setSpanCount(spanCount);
//    }
}
