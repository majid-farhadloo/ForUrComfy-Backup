package com.example.majid.forurcomfy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.majid.forurcomfy.Common.Current;
import com.example.majid.forurcomfy.ShoppingCart.ShoppingCartWindow;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Button tacoBell = (Button) findViewById(R.id.tacobell);
        final Button pandaExpress = (Button) findViewById(R.id.pandaExpress);
        final Button subway = (Button) findViewById(R.id.subway);
        final Button myRestaurant = (Button) findViewById(R.id.myRestaurant);

        myRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OrderIntent = new Intent(Home.
                        this, ReturnActivity.class);
                Home.this.startActivity(OrderIntent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Current.currentUser.getEmail());



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
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.deliverer) {
            // Handle the camera action
            Intent intent = new Intent(Home.this, DeliverGuyActivity.class); // this second home should be deliverer
            startActivity(intent);
        } else if (id == R.id.user) {
//            Intent intent = new Intent(Home.this, LoginActivity.class); // this second home should be deliverer
//            startActivity(intent);


        } else if (id == R.id.nav_menu) {
            Intent intent = new Intent(Home.this, Home.class); // this second home should be deliverer
            startActivity(intent);

        } else if (id == R.id.nav_cart) {
            Intent intent = new Intent(Home.this, ShoppingCartWindow.class); // this second home should be cartactivity
            startActivity(intent);

        } else if (id == R.id.nav_payment) {
            Intent intent = new Intent(Home.this, CheckoutActivity.class); // this second home should be deliverer
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(Home.this, LoginActivity.class); // this second home should be deliverer
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
