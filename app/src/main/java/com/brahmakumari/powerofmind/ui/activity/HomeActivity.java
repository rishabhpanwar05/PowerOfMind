package com.brahmakumari.powerofmind.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.brahmakumari.powerofmind.R;

import com.brahmakumari.powerofmind.ui.fragment.AudioFragment;
import com.brahmakumari.powerofmind.ui.fragment.ArticlesFragment;
import com.brahmakumari.powerofmind.ui.fragment.CLFragment;
import com.brahmakumari.powerofmind.ui.fragment.LiveDarshanFragment;
import com.brahmakumari.powerofmind.ui.fragment.EventsFragment;
import com.brahmakumari.powerofmind.ui.fragment.HomeFragment;
import com.brahmakumari.powerofmind.ui.fragment.MessageFragment;
import com.brahmakumari.powerofmind.ui.fragment.NewsFragment;
import com.brahmakumari.powerofmind.ui.fragment.NewsandEventsFragment;
import com.brahmakumari.powerofmind.ui.fragment.VideoFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomeFragment.OnFragmentInteractionListener,
ArticlesFragment.OnFragmentInteractionListener,
LiveDarshanFragment.OnFragmentInteractionListener,MessageFragment.OnFragmentInteractionListener ,
        NewsandEventsFragment.OnFragmentInteractionListener,VideoFragment.OnFragmentInteractionListener,
        AudioFragment.OnFragmentInteractionListener,NewsFragment.OnFragmentInteractionListener,EventsFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        navigationView.setCheckedItem(R.id.nav_home);

        //NOTE:  Open fragment1 initially.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new HomeFragment());
        ft.commit();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_message) {
            fragment = new MessageFragment();
        } else if (id == R.id.nav_video) {
            fragment = new VideoFragment();
        } else if (id == R.id.nav_audio) {
            fragment = new AudioFragment();
        } else if (id == R.id.nav_news) {
            fragment = new NewsandEventsFragment();
        } else if (id == R.id.nav_darshan) {
            fragment = new LiveDarshanFragment();
        } else if (id == R.id.nav_centre) {
            fragment = new CLFragment();
        } else if (id == R.id.nav_articles) {
            fragment = new ArticlesFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
