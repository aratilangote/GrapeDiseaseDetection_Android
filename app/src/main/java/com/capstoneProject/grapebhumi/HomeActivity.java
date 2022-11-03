package com.capstoneProject.grapebhumi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private TextView txtView;
    NavigationView navView;
    DrawerLayout drawer;
    Toolbar toolbar;

    //Adapterimage flipper
    AdapterViewFlipper adapter;
    private static final int[] images = {R.drawable.farm, R.drawable.grape_farm, R.drawable.grapegrowth, R.drawable.grape_types};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        txtView = findViewById(R.id.txt_demo);
        frameLayout = findViewById(R.id.frame_container);

        frameLayout.setBackgroundColor(Color.CYAN);
        txtView.setText("Home");
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Navigation Drawer");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navView = findViewById(R.id.nav_drawer);
        navView.setNavigationItemSelectedListener(this);

        //Adapterimage flipper
        adapter = findViewById(R.id.adptflipper);
        ViewFlipperAdapter viewFlipper = new ViewFlipperAdapter(this, images);
        adapter.setAdapter(viewFlipper);
        adapter.setAutoStart(true);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_home:
                    frameLayout.setBackgroundColor(Color.CYAN);
                    txtView.setText("Home");
                    break;
                case R.id.nav_crop_Care:
                    frameLayout.setBackgroundColor(Color.RED);
                    txtView.setText("Crop Care");
                    break;
                case R.id.nav_video:
                    frameLayout.setBackgroundColor(Color.GREEN);
                    txtView.setText("Videos");
                    break;
                case R.id.nav_weather:
                    frameLayout.setBackgroundColor(Color.GRAY);
                    txtView.setText("Weather");
                    break;
            }
            return true;
        }
    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_contactUs:
                Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rateUs:
                Toast.makeText(this, "Rate Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Adapterimage flipper
    class ViewFlipperAdapter extends BaseAdapter {
        Context context;
        int[] images;

        LayoutInflater inflater;

        public ViewFlipperAdapter(Context mycontext, int[] myimages){
            this.context = mycontext;
            this.images = myimages;

            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.activity_adapterimages, null);

            ImageView img = view.findViewById(R.id.imgs);

            img.setImageResource(images[position]);
            return view;
        }
    }
}