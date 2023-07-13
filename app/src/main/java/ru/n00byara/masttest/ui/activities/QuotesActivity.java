package ru.n00byara.masttest.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.n00byara.masttest.R;
import ru.n00byara.masttest.components.clientapi.APIClient;
import ru.n00byara.masttest.components.clientapi.pojo.Quotes;
import ru.n00byara.masttest.components.clientapi.routers.Routers;
import ru.n00byara.masttest.ui.adapters.comments.QuotesAdapter;

public class QuotesActivity extends AppCompatActivity {
    private final String TAG = "boolean";
    SharedPreferences settings;
    private static final String PREFS_URL = "url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        String url = getUrl();

        if (url.equals("https://dummyjson.com/")) {
            setQuotes(url);
            setNavBar();
        } else {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }
    private String  getUrl() {
        settings = getSharedPreferences(PREFS_URL, MODE_PRIVATE);
        return settings.getString(PREFS_URL, "");
    }
    public void setQuotes(String url) {
        Routers apiInterface = APIClient.getClient(url).create(Routers.class);
        Call<Quotes> call = apiInterface.getQuotes();

        call.enqueue(new Callback<Quotes>() {
            @Override
            public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                Quotes resourses = response.body();
                List<Quotes.Quote> quotes = resourses.quotes;

                RecyclerView recyclerView = findViewById(R.id.citation_recycler_view);

                QuotesAdapter adapter = new QuotesAdapter(QuotesActivity.this, quotes);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Quotes> call, Throwable t) {
//                call.cancel();
            }
        });
    }
    private boolean setNavBar() {
        DrawerLayout drawerLayout = findViewById(R.id.citation_drawer_layout_id);

        NavigationView navigationView = findViewById(R.id.nav_view_id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                if (id == R.id.products_btn) {
                    intent = new Intent(QuotesActivity.this, ProductsActivity.class);
                    drawerLayout.close();
                    startActivity(intent);
                } else if (id == R.id.settings_btn) {
                    intent = new Intent(QuotesActivity.this, SettingsActivity.class);
                    drawerLayout.close();
                    startActivity(intent);
                } else {
                    drawerLayout.close();
                }

                return false;
            }
        });
        return false;
    }
}