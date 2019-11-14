package com.telpirion.demo.asynctasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.telpirion.demo.asynctasks.fragments.AsyncTaskFragment;
import com.telpirion.demo.asynctasks.fragments.AsyncTaskLoaderFragment;
import com.telpirion.demo.asynctasks.fragments.MapHolderFragment;
import com.telpirion.demo.asynctasks.fragments.TrafficCameraFragment;
import com.telpirion.demo.asynctasks.fragments.VolleyFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TrafficCameraFragment.TrafficCameraFragmentClickListener{

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        drawer,
                        toolbar,
                        R.string.nav_open_drawer, R.string.nav_close_drawer);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        AsyncTaskFragment fragment = new AsyncTaskFragment();
        swapFragments(fragment, true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void swapFragments(Fragment fragment, boolean isStart) {

        drawer.closeDrawer(GravityCompat.START);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        if (isStart) {
            fragmentTransaction.add(R.id.fragment_host, fragment);
        } else {
            fragmentTransaction.replace(R.id.fragment_host, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment;

        switch (menuItem.getItemId()) {

            case R.id.nav_async_loader:
                fragment = new AsyncTaskLoaderFragment();
                break;
            case R.id.nav_traffic_cam:
                fragment = new TrafficCameraFragment();
                break;
            case R.id.nav_volley:
                fragment = new VolleyFragment();
                break;
            case R.id.nav_async_task:
            default:
                fragment = new AsyncTaskFragment();
                break;
        }

        swapFragments(fragment, false);

        return false;
    }

    @Override
    public void onTrafficCameraFragmentClick(Bundle bundle) {
        MapHolderFragment fragment = new MapHolderFragment();
        fragment.setArguments(bundle);
        swapFragments(fragment, false);
    }
}
