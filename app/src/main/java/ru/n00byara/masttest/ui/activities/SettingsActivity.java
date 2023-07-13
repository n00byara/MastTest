package ru.n00byara.masttest.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import ru.n00byara.masttest.R;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences settings;
    private static final String PREFS_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getUrl();
        Button button = findViewById(R.id.save_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUrl();
            }
        });
        setNavBar();
    }

    private void saveUrl() {
        EditText editText = findViewById(R.id.url_set);
        String url = editText.getText().toString();

        settings = getSharedPreferences(PREFS_URL, MODE_PRIVATE);
        SharedPreferences.Editor edit = settings.edit();

        edit.putString(PREFS_URL, url);
        edit.apply();
    }

    private void getUrl() {
        settings = getSharedPreferences(PREFS_URL, MODE_PRIVATE);
        String url = settings.getString(PREFS_URL, "");

        EditText editText = findViewById(R.id.url_set);
        editText.setText(url);
    }
    private boolean setNavBar() {
        DrawerLayout drawerLayout = findViewById(R.id.settings_drawer_layout_id);

        NavigationView navigationView = findViewById(R.id.nav_view_id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                if (id == R.id.citation_btn) {
                    intent = new Intent(SettingsActivity.this, QuotesActivity.class);
                    drawerLayout.close();
                    startActivity(intent);
                } else if (id == R.id.products_btn) {
                    intent = new Intent(SettingsActivity.this, ProductsActivity.class);
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