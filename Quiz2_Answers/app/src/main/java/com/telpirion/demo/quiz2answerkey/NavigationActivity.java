package com.telpirion.demo.quiz2answerkey;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class NavigationActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =
                fragmentManager.beginTransaction();
        transaction.add(R.id.the_fragment_dude, new HelloFragment());
        transaction.commit();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        drawer,
                        toolbar,
                        R.string.nav_open_drawer,
                        R.string.nav_close_drawer);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    /*
    +2 points for overriding onOptionsItemSelected

    Deductions:

    -2: The correct method to override for clicks to the app bar is onOptionsItemSelected.
    -1: The precise name of the method to override is onOptionsItemSelected
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        /*
        +1 point for correct switch/case (or if/else) with fragment declared outside.

        -0.5: `id == findViewByID(R.id<something>) compares an int to a View.
        -0.5: Need to to create instances of SearchFragment() and HelloFragment()
        */
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.action_hello:
                fragment = new HelloFragment();
                break;
            case R.id.action_search:
            default:
                fragment = new SearchFragment();
                break;

        }

        /*
         +1 point for fragment transaction
          */
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        /*
         +1 point for using replace() + commit()

         Deductions:
         -0.5: FragmentTransaction need to call commit() to execute change.
          */
        transaction.replace(R.id.the_fragment_dude, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }

    @Override
    /*
    +2 points for overriding onNavigationItemSelected

    Deductions:
    -2: The correct method to override for clicks to the navigation drawer is onNavigationItemSelected.
    -1: The onNavigationItemSelected() method takes a MenuItem as an argument.
     */
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment newFragment;

        /*
        +1 point for correct switch/case (or if/else) with fragment declared outside.

        Deductions:
        -0.5: Need to use MenuItem.getItemId() for switch statement

        */
        switch(menuItem.getItemId()) {
            case R.id.nav_hurray:
                newFragment = new HurrayFragment();
                break;

            case R.id.nav_boo:
            default:
                newFragment = new BooFragment();
                break;
        }


        drawer.closeDrawer( GravityCompat.START);

        /*
         +1 point for fragment transaction

         Deductions:
         -0.5: Missing call to getSupportFragmentManager().beginTransaction().
         -1: Method needs to create a FragmentTransaction object.
          */
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        /*
         +1 point for using replace() + commit()

         Deductions:
         -1: Method ndeds to call FragmentTransaction.replace() and FragmentTransaction.commit().
          */
        transaction.replace(R.id.the_fragment_dude, newFragment); // fraggy_container in test
        transaction.addToBackStack(null);
        transaction.commit();

        return false;
    }
}
