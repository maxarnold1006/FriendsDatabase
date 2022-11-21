package com.missouristate.arnold.friendsdatabase;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.missouristate.arnold.friendsdatabase.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onStart(){
        super.onStart();
        dbManager = new DatabaseManager( this );
        //updateView( );
    }

    public void updateView( ) {
            ArrayList<Friend> friends = dbManager.selectAll( );
            if( friends.size( ) > 0 ) {
                // create ScrollView and GridLayout
                ScrollView scrollView = new ScrollView( this );
                GridLayout grid = new GridLayout( this );
                grid.setRowCount( friends.size( ) );
                grid.setColumnCount( 4 );

                // create arrays of components
                TextView[] ids = new TextView[friends.size( )];
                TextView[][] namesAndPrices = new TextView[friends.size( )][3];

                // retrieve width of screen
                Point size = new Point( );
                getWindowManager( ).getDefaultDisplay( ).getSize( size );
                int width = size.x;

                int i = 0;

                for ( Friend friend : friends ) {
                    // create the TextView for the candy's id
                    ids[i] = new TextView( this );
                    ids[i].setGravity( Gravity.CENTER );
                    ids[i].setText( "" + friend.getId( ) );

                    // create the three EditTexts for the candy's name and price
                    namesAndPrices[i][0] = new TextView( this );
                    namesAndPrices[i][1] = new TextView( this );
                    namesAndPrices[i][2] = new TextView( this );
                    namesAndPrices[i][0].setGravity( Gravity.CENTER );
                    namesAndPrices[i][1].setGravity( Gravity.CENTER );
                    namesAndPrices[i][2].setGravity( Gravity.CENTER );
                    namesAndPrices[i][0].setText( "" + friend.getFirstName( ) );
                    namesAndPrices[i][1].setText( "" + friend.getLastName( ) );
                    namesAndPrices[i][2].setText( "" + friend.getEmail( ) );

                    namesAndPrices[i][0].setId( 10 * friend.getId( ) );
                    namesAndPrices[i][1].setId( 10 * friend.getId( ) + 1  );
                    namesAndPrices[i][2].setId( 10 * friend.getId( ) + 2  );

                    // add the elements to grid
                    grid.addView( ids[i], width / 10,
                            ViewGroup.LayoutParams.WRAP_CONTENT );
                    grid.addView( namesAndPrices[i][0], ( int ) ( width * .2 ),
                            ViewGroup.LayoutParams.WRAP_CONTENT );
                    grid.addView( namesAndPrices[i][1], ( int ) ( width * .2 ),
                            ViewGroup.LayoutParams.WRAP_CONTENT );
                    grid.addView( namesAndPrices[i][2], ( int ) ( width * .2 ),
                            ViewGroup.LayoutParams.WRAP_CONTENT );
                    i++;
                }
                scrollView.addView( grid );
                setContentView( scrollView );
            }
        }




        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                Log.w("MainActivity", "Add selected");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete selected");
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                Log.w("MainActivity", "Update selected");
                Intent updateIntent = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}