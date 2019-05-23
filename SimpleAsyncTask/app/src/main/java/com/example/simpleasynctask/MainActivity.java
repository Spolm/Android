package com.example.simpleasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // The TextView where we will show results
    private TextView mTextView;

    //Key for saving the state of the TextView
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Guarda el estado del TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize mTextView
        mTextView = findViewById(R.id.textView1);

        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {

        // Put a message in the text view
        mTextView.setText(R.string.napping);

        // Start the AsyncTask.
        // The AsyncTask has a callback that will update the textview.
        new SimpleAsyncTask(mTextView).execute();

    }
}
