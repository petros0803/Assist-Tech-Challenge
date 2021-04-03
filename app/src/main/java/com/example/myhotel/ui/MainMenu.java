package com.example.myhotel.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhotel.R;
import com.example.myhotel.data.Singleton;
import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private TextView txt;
    private ImageView menuopen;
    private NavigationView drawerNav;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
       txt=findViewById(R.id.txt_welcome);
       menuopen=findViewById(R.id.menu_open);
       txt.setText("Book a room here "+Singleton.getInstance().getName());

        drawer=(DrawerLayout) findViewById(R.id.drawerLayout);
        toggle=new ActionBarDrawerToggle(
                this,
                drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        menuopen.setOnClickListener(v -> {

            Log.i("MENU","Pressing Menu BUTTON");
            if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }else {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawerNav = findViewById(R.id.navigation_drawer);
        drawerNav.getMenu();
        drawerNav.setNavigationItemSelectedListener(item -> {
            drawer.closeDrawers();
            switch (item.getItemId())
            {
                case R.id.edit_profile_menu:
                    Toast.makeText(MainMenu.this, "STIll in development", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.logout_menu:
                    Singleton.getInstance().setToken(null);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                    return true;
            }

            return true;
        });
    }
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
        }
}