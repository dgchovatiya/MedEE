package com.example.mitul.hospitalfinder.Activity.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Activity.LoginActivity;
import com.example.mitul.hospitalfinder.Adapter.Admin.Admin_main_ViewPager_adpter;
import com.example.mitul.hospitalfinder.R;

public class AdminMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    Toolbar toolbar1;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;

    ImageView imgvw;
    TextView email;
    TabLayout admin_main_tablayout;
    ViewPager admin_main_viewpager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        //getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.admin_color)); //status bar or the time bar at the top
        }
       initview();
        toolbar1 = (android.support.v7.widget.Toolbar) findViewById(R.id.ad_toolb);
        setSupportActionBar(toolbar1);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawer = (DrawerLayout) findViewById(R.id.ad_dawer);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.ad_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navView = navigationView.inflateHeaderView(R.layout.navigatiton_hedar_ad);
        imgvw = (ImageView) navView.findViewById(R.id.iv_ad_lg1_hd);
        email = (TextView) navView.findViewById(R.id.txt_ad_lg1_hd_email);
        //setkarvu header

        admin_main_tablayout.addTab(admin_main_tablayout.newTab().setText("PENDING REQUEST"));
        admin_main_tablayout.addTab(admin_main_tablayout.newTab().setText("CONFIRM  REQUEST"));

        Admin_main_ViewPager_adpter viewPager_adpter = new Admin_main_ViewPager_adpter(getSupportFragmentManager(), admin_main_tablayout.getTabCount(),context);
        admin_main_viewpager.setAdapter(viewPager_adpter);

        admin_main_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(admin_main_tablayout));

        admin_main_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                admin_main_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initview() {

        context=AdminMainActivity.this;

        admin_main_tablayout = (TabLayout) findViewById(R.id.admin_main_tablayout);
        admin_main_viewpager = (ViewPager) findViewById(R.id.admin_main_viewpager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ad_logout) {

            //startActivity(new Intent(home.this, homeappliencew.class));
            Log.e("Logout", "logout");
            startActivity(new Intent(AdminMainActivity.this, LoginActivity.class));
            finish();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.hs_lg1_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

}
