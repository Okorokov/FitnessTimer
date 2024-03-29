package com.example.fitnesstimer.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fitnesstimer.R;
import com.example.fitnesstimer.adapter.DessertAdapter;

public class MainActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private boolean appBarExpanded = true;
    private RecyclerView recyclerView;

    private DessertAdapter dessertAdapter;
    private Menu collapsedMenu;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        fabAdd =(FloatingActionButton) findViewById(R.id.fab_add);
/*
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.title_name));


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.header);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(vibrantColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        //  Use when your list size is constant for better performance
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        dessertAdapter = new DessertAdapter(this);
        recyclerView.setAdapter(dessertAdapter);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Log.d(MainActivity.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 200) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                }
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(MainActivity.class.getSimpleName(), "setOnClickListener: ");
                Intent intent=new Intent(MainActivity.this, DetailTimerActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null
                && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed
            collapsedMenu.add("Add")
                    .setIcon(R.drawable.ic_add_white_24dp)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else {
            //expanded
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*
            case android.R.id.home:
                finish();
                return true;*/
            case R.id.action_settings:
                Intent intent=new Intent(MainActivity.this, HistoryTimerActivity.class);
                startActivity(intent);
                return true;
        }
        if (item.getTitle() == "Add") {
            //Toast.makeText(this, "clicked add", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this, DetailTimerActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
