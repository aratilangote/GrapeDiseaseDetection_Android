package com.capstoneProject.grapebhumi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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
        bottomNavigationView.setBackgroundColor(getColor(R.color.green));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getColor(R.color.green));

        drawer = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navView = findViewById(R.id.nav_drawer);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           Fragment temp;
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        temp = new FragmentHome();
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_profile:
                        temp = new ProfileFragment();
                        bottomNavigationView.setVisibility(View.GONE);
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
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    case R.id.nav_termsconditions:
                        temp = new TermsConditionsFragment();
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    case R.id.nav_aboutUs:
                        temp = new AboutusFragment();
                        bottomNavigationView.setVisibility(View.GONE);
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
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        temp2 = new FragmentHome();
                        navView.setVisibility(View.GONE);
                        break;
                    case R.id.nav_crop_Care:
                        temp2 = new BottomCropCareFragment();
                        navView.setVisibility(View.GONE);
                        break;
                    case R.id.nav_video:
                        temp2 = new BottomVideosFragment();
                        navView.setVisibility(View.GONE);
                        break;
                    case R.id.nav_weather:
                        temp2 = new BottomWeatherFragment();
                        navView.setVisibility(View.GONE);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,temp2).commit();
                return true;
            }
        });

    }
}