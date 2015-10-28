package com.antonioejemplos.androiddesingsupportlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1===============================
        //Primero ponemos la Toobar para que se comporte como una antigua ActionBar
        setupToolbar();

        //2===============================
        //Segundo ponemos a la escucha el FAB. Igual que cualquier otro botón
        setupFAB();

        //3==============================
        //Tercero ponemos un NavigationView:
        //A-Se le pone un menú @android:drawable/ic_menu_agenda
        //B-Se le debe incluir dentro de un DramerLayout... que englobe todo el layout donde se haga visible.
        //C-Se de debe crear un layout.xml para que haga de header navigation_header.xml
        //Se crea un objeto NavigationView y se le asigna un oyente...
        setupNavigationView();

        //4===============================
        //Cuarto utilizamos la SnackBar import android.support.design.widget.Snackbar;
        //Es semejante a un toast solo que sale en la barra de abajo.
        //Se le ha asociado al listener del FAB.


        //5==============================
        //Quinto incluimos un TabLayout para implementar pestañas
        //Se utiliza en combinación con un ViewPager
        //Se implementa el fragment PageFragment que infla su layout: fragment_page.xml
        //Se implmenta el adaptador PageAdapter para gestionar las pestañas
        setupTabLayout();

    }


    private void setupToolbar(){//Para que la Toolbar se comporte como una ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//Obligatorio importar import android.support.v7.widget.Toolbar;

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);//Icono desplegagle
        ab.setDisplayHomeAsUpEnabled(true);

        if (navigationView != null) {
            setupNavigationView();
        }

        setupNavigationView();


    }

    private void setupFAB(){
        //Se pone a la escucha el FAB igual que otro botón...
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Llamamos al Snackbar para que levante un mensaje...
                Snackbar
                        .make(findViewById(R.id.container), "Soy la Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", this).show();
            }
        });

    }


    private void setupNavigationView(){//Mapeamos el DramerLQayout y el navigationView y le ponemos un oyente..

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        //startActivity(new Intent(this, MainActivity.class));

                        Intent intent1=new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent1);

                        break;
                    case R.id.navigation_item_2:
                        //startActivity(new Intent(MainActivity.this, TextInputLayoutActivity.class));

                        Intent intent2=new Intent(MainActivity.this,TextInputLayoutActivity.class);
                        startActivity(intent2);
                        break;


                }



                return false;
            }
        });

    }

    private void setupTabLayout(){

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
