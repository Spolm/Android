package com.example.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

        // Obtiene un handle para el RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Crea un adapter y suministra los datos a ser mostrados.
        mAdapter = new WordListAdapter(this, mWordList);
        // Conecta el adapter con el RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Le da al RecyclerView un layout manager por defecto.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((view) -> {
            int wordListSize = mWordList.size();
            // Add a new word to the end of the wordList.
            mWordList.addLast("+ Word " + wordListSize);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
            // Scroll to the bottom.
            mRecyclerView.smoothScrollToPosition(wordListSize);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
