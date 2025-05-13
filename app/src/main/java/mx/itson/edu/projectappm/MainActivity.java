package mx.itson.edu.projectappm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Aquí está el nombre correcto
        getMenuInflater().inflate(R.menu.menu_nav, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent = null;

            if (item.getItemId() == R.id.nav_add) {
                intent = new Intent(MainActivity.this, CrearReporteActivity.class);
            } else if (item.getItemId() == R.id.nav_report) {
                intent = new Intent(MainActivity.this, ConsultarReporteActivity.class);
            } else if (item.getItemId() == R.id.nav_contact) {
                intent = new Intent(MainActivity.this, ContactoActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
            }

            return true;
        });



    }
}