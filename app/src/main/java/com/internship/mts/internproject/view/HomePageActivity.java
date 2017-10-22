package com.internship.mts.internproject.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.internship.mts.internproject.CreateCouponActivity;
import com.internship.mts.internproject.ChatActivity;
import com.internship.mts.internproject.CreateDiscountActivity;
import com.internship.mts.internproject.LoginActivity;
import com.internship.mts.internproject.ProfilePageActivity;
import com.internship.mts.internproject.R;
import com.internship.mts.internproject.databinding.HomePageActivityBinding;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    //TODO: Check network connection
    public static Intent newIntent(Activity activity) {
        return new Intent(activity, HomePageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomePageActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.home_page_activity);
        setSupportActionBar(binding.homePageActivityToolbar);

        drawer = binding.homePageActivityDrawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.homePageActivityToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        binding.homePageActivityNavView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_page_activity_fragment_container, new DiscountFragment()).commit();
        setTitle(getString(R.string.nav_menu_discount));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
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

        if (id == R.id.nav_discount) {
            homePageFragmentSwitcher(new DiscountFragment(), getString(R.string.nav_menu_discount));

        } else if (id == R.id.nav_coupon) {
            homePageFragmentSwitcher(new CouponFragment(), getString(R.string.nav_menu_coupon));

        } else if (id == R.id.nav_message) {
            startActivity(ChatActivity.newIntent(this));

        } else if (id == R.id.nav_profile) {
            startActivity(ProfilePageActivity.newIntent(this));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onShareCouponClicked(View view){
        startActivity(CreateCouponActivity.newIntent(this));
    }

    public void onShareDiscountClicked(View view){
        startActivity(CreateDiscountActivity.newIntent(this));
    }

    private void homePageFragmentSwitcher(Fragment fragment, String title){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_page_activity_fragment_container, fragment).commit();
        setTitle(title);
    }
}
