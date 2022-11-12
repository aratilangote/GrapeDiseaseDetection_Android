package com.capstoneProject.grapebhumi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    NavigationView navView;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawer;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navView = findViewById(R.id.nav_drawer);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           Fragment temp;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        temp = new HomeFragment();
                        break;
                    case R.id.nav_profile:
                        temp = new ProfileFragment();
                        break;
                    case R.id.nav_share:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "http://javatpoint.com");
                        intent.setType("text/plain");
                        startActivity(Intent.createChooser(intent,"Share via"));
                        break;
                    case R.id.nav_privacypolicy:
                        Intent intent1 = new Intent(HomeActivity.this, PrivacyPolicyFragment.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_termsconditions:
                        temp = new TermsConditionsFragment();
                        break;
                    case R.id.nav_aboutUs:
                        temp = new AboutusFragment();
                        break;
                    case R.id.nav_Logout:

                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, temp).commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment temp2;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        temp2 =new HomeFragment();
                        break;
                    case R.id.nav_crop_Care:
                        temp2 = new BottomCropCareFragment();
                        break;
                    case R.id.nav_video:
                        temp2 = new BottomVideosFragment();
                        break;
                    case R.id.nav_weather:
                        temp2 = new BottomWeatherFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,temp2).commit();
                return true;
            }
        });

    }
}