package com.example.ari.itellu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ari.itellu.admin.Event.menuEvent;
import com.example.ari.itellu.admin.Informasi.menuInformasi;
import com.example.ari.itellu.admin.MenuAdmin;
import com.example.ari.itellu.admin.komunitas.menuKomunitas;
import com.example.ari.itellu.admin.pertanyaan.menuPertanyaan;
import com.example.ari.itellu.admin.ukm.menuUkm;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    private TextView mNamaUser, mEmailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        filterUser();
        if (FirebaseC.currentUser == null) {
            startActivity(new Intent(NavigationActivity.this, SignIn.class));
            finish();
        } else {
            Log.d("Current User", "Berhasil Login");
        }
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        mNamaUser = (TextView) header.findViewById(R.id.namaUser);
        mNamaUser.setText(FirebaseC.mAuth.getCurrentUser().getEmail().split("@")[0]);
        mEmailUser = (TextView) header.findViewById(R.id.emailUser);
        mEmailUser.setText(FirebaseC.mAuth.getCurrentUser().getEmail());



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            MenuHome fragment = new MenuHome();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1, fragment, "MenuHome").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.nav_telyu) {
            Log.d("nav_telyu", "Profile Telkom");
            menuInformasi fragment = new menuInformasi();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1, fragment, "menuInformasi").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.nav_ukm) {
            Log.d("nav_ukm", "Menu UKM");
            menuUkm fragment = new menuUkm();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1, fragment, "menuUkm").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.nav_komuniti) {
            Log.d("nav_komuniti", "Menu Komunitas");
            menuKomunitas fragment = new menuKomunitas();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1, fragment, "menuKomunitas").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.nav_event) {
            Log.d("nav_event", "Menu Event");
            menuEvent fragment = new menuEvent();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1, fragment, "MenuEvent").commit();
            getSupportFragmentManager().popBackStack();


        } else if (id == R.id.nav_maps) {
            Log.d("nav_maps", "Menu Maps");

        } else if (id == R.id.nav_help){
            Log.d("nav_help", "Menu Maps");
            menuPertanyaan fragment = new menuPertanyaan();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1, fragment, "MenuPertanyaan").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.nav_logout){
            FirebaseC.mAuth.signOut();
            FirebaseC.currentUser = null;
            Toast.makeText(NavigationActivity.this, "Logout Successfull", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(NavigationActivity.this, SignIn.class));
            finish();


        } else if (id == R.id.nav_admin){
            Log.d("Menu Admin:", "Content CRUD");
            MenuAdmin fragment = new MenuAdmin();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1, fragment, "MenuAdmin").commit();
            getSupportFragmentManager().popBackStack();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void filterUser() {
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem admin = menu.findItem(R.id.nav_admin);

        if (FirebaseC.mAuth.getCurrentUser().getEmail().equals("admin@admin.com")) {
            admin.setVisible(true);
        } else {
            admin.setVisible(false);

        }

    }
}
