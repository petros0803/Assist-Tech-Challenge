package com.example.myhotel.ui;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhotel.fragment.AvailableReservations;
import com.example.myhotel.R;
import com.example.myhotel.data.Singleton;
import com.example.myhotel.fragment.MyReservations;
import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    Fragment selectedFragment = null;
    private SwitchCompat swtch;
    private TextView txt_on;
    private TextView txt_off;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        swtch = findViewById(R.id.btn_switch);
        setContentView(R.layout.activity_main_menu);
        TextView txt = findViewById(R.id.txt_welcome);

        txt_on = findViewById(R.id.txt_on);
        txt_off = findViewById(R.id.txt_off);
        txt.setText("Book a room here " + Singleton.getInstance().getName());
        txt_off.setVisibility(View.GONE);
        openDrawer();
        //Switching between fragments
        fragmentSwitching();
        //

    }

    public void openDrawer() {
        ImageView menuopen = findViewById(R.id.menu_open);
        drawer = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        menuopen.setOnClickListener(v -> {
            Log.i("MENU", "Pressing Menu BUTTON");
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationView drawerNav = findViewById(R.id.navigation_drawer);
        drawerNav.getMenu();

        drawerNav.setNavigationItemSelectedListener(item -> {
            drawer.closeDrawers();
            switch (item.getItemId()) {
                case R.id.edit_profile_menu:
                    Toast.makeText(MainMenu.this, "STIll in development", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.logout_menu:
                    Singleton.getInstance().setToken(null);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                    break;
            }
            return true;
        });
    }

    public void fragmentSwitching() {
        selectedFragment = new AvailableReservations();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        swtch = findViewById(R.id.btn_switch);
        TextView txt = findViewById(R.id.txt_welcome);
        swtch.setOnClickListener(v -> {
            if (!swtch.isChecked()) {
                txt_off.setVisibility(View.GONE);
                txt_on.setVisibility(View.VISIBLE);
                txt.setText("Book a room here " + Singleton.getInstance().getName());
                selectedFragment = new AvailableReservations();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            } else {
                txt_on.setVisibility(View.GONE);
                txt_off.setVisibility(View.VISIBLE);
                txt.setText("These are your reservations:");
                selectedFragment = new MyReservations();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}