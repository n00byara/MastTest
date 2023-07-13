package ru.n00byara.masttest.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.n00byara.masttest.R;
import ru.n00byara.masttest.components.clientapi.APIClient;
import ru.n00byara.masttest.components.clientapi.pojo.Products;
import ru.n00byara.masttest.components.clientapi.routers.Routers;
import ru.n00byara.masttest.ui.adapters.products.ProductAdapter;

public class ProductsActivity extends AppCompatActivity {
    SharedPreferences settings;
    private static final String PREFS_URL = "url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        String url = getUrl();

        if (url.equals("https://dummyjson.com/")) {
            setProducts(url);
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
    public void setProducts(String url) {
        Routers apiInterface = APIClient.getClient(url).create(Routers.class);
        Call<Products> call = apiInterface.getProducts();

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                Products resourse = response.body();
                List<Products.Product> products = resourse.products;

                RecyclerView recyclerView = findViewById(R.id.products_recycler_view);

                ProductAdapter adapter = new ProductAdapter(ProductsActivity.this, products);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                call.cancel();
            }
        });

    }

    private boolean setNavBar() {
        DrawerLayout drawerLayout = findViewById(R.id.products_drawer_layout_id);

        NavigationView navigationView = findViewById(R.id.nav_view_id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                if (id == R.id.citation_btn) {
                    intent = new Intent(ProductsActivity.this, QuotesActivity.class);
                    drawerLayout.close();
                    startActivity(intent);
                } else if (id == R.id.settings_btn) {
                    intent = new Intent(ProductsActivity.this, SettingsActivity.class);
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