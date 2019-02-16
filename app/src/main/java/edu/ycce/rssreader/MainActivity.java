package edu.ycce.rssreader;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FeedListFragment.Listener, SourceListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new FeedListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, fragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_source:
                Intent intent = new Intent(this, AddSourceActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;
        Bundle bundle = new Bundle();

        switch (id) {
            //case R.id.nav_all:
            case R.id.nav_business:
                bundle.putString("category", "Business");
                fragment = new SourceListFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_entertainment:
                bundle.putString("category", "Entertainment");
                fragment = new SourceListFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_sport:
                bundle.putString("category", "Sport");
                fragment = new SourceListFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_technology:
                bundle.putString("category", "Technology");
                fragment = new SourceListFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_other:
                bundle.putString("category", "Other");
                fragment = new SourceListFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_about:
                fragment = new AboutFragment();
                break;
            case R.id.nav_add_source:
                intent = new Intent(this, AddSourceActivity.class);
                break;
            case R.id.nav_sources:
                bundle.putString("category", "");
                fragment = new SourceListFragment();
                fragment.setArguments(bundle);
                break;
            default:
                fragment = new FeedListFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        } else {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void itemClicked(long id){
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra(DescriptionActivity.ID, (int)id);
        startActivity(intent);
    }

    @Override
    public void sourceClicked(long id){
        Bundle bundle = new Bundle();
        bundle.putLong("id", (int) id);
        Fragment fragment = new FeedListFragment();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
