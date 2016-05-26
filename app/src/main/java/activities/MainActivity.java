package activities;

import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.paulcurle.syndicatemanager.R;

import java.io.IOException;

import database.DbAdapter;
import database.DbHelper;
import fragments.AboutSyndicateManagerFragment;
import fragments.DrawDetailsFragment;
//import fragments.SettingsFragment;
import fragments.ShowResultsFragment;
import fragments.SyndicateMemberEditFragment;
import fragments.SyndicateMembersFragment;
import models.LotteryChoice;

/**
 * Created by Paul on 21/02/2016.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //    public static final String MyPREFERENCES = "MyPrefs" ;
    public static String Lottery = "lottery";
    //    public static final String Phone = "phoneKey";
//    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;
//    public DbAdapter dba;
    public DbHelper dbh;
    LotteryChoice lotteryChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dbh = new DbHelper(this);



//        try {
//
//            dbh.onCreate();
//
//        } catch (IOException ioe) {
//
//            throw new Error("Unable to create database");
//
//        }
//
//        try {
//
//            dbh.openDataBase();
//
//        }catch(SQLException sqle){
//
//            throw sqle;
//
//        }
//        try {
//            dbh.closeDB();
//        }catch(SQLException sqle){
//
//            throw sqle;
//
//
//        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        PreferenceManager.setDefaultValues(this, R.xml.preference, false);
//        PreferenceManager.setDefaultValues(this,Lottery, Context.MODE_PRIVATE, R.xml.preference, false);
//        setDefaults(Lottery);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.euro, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            //"Replace with your own action"
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
//
        //From Set Preferences trial
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection - there is only one!
//        Fragment fragment;
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragment = new SettingsFragment();
//
//        fragmentManager.beginTransaction()
//
//                .replace(R.id.container, fragment)
//
//                .commit();
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment;
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(id) {
            case R.id.nav_about:
                // Handle the about action
                fragment = new AboutSyndicateManagerFragment();
                fragmentManager.beginTransaction()

                        .replace(R.id.container, fragment)

                        .commit();
                break;

            case  R.id.nav_all_members:
                fragment = new SyndicateMembersFragment();
                fragmentManager.beginTransaction()

                        .replace(R.id.container, fragment)

                        .commit();
                break;

            case R.id.nav_draw_history:
                fragment = new ShowResultsFragment();
                fragmentManager.beginTransaction()

                        .replace(R.id.container, fragment)

                        .commit();
                break;
            case R.id.nav_new_member:
                //Add Syndicate Member
                fragment = new SyndicateMemberEditFragment();
                fragmentManager.beginTransaction()

                        .replace(R.id.container, fragment)

                        .commit();
                break;
            case R.id.nav_draw_details:
                fragment = new DrawDetailsFragment();
                fragmentManager.beginTransaction()

                        .replace(R.id.container, fragment)

                        .commit();
                break;

//            case R.id.nav_settings:
//                fragment = new SettingsFragment();
//                fragmentManager.beginTransaction()
//
//                        .replace(R.id.container, fragment)
//
//                        .commit();
//                break;
            case R.id.nav_share:
                break;

            case R.id.nav_send:
                break;

        }
/*

        <item android:id="@+id/nav_new_member"
            android:title="Add Syndicate Member" />
        <item android:id="@+id/nav_draw_details"
            android:title="Enter/Show Draw Details" />
 */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


