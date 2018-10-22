package com.example.com.twoactivitiescallback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText mMessagedEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    public static final  int TEXT_REQUEST = 1;
    public static final String EXTRA_MESSAGE =  "com.example.com.twoactivitiescallback.extra.MESSAGE";

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",  mReplyTextView.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessagedEditText = (EditText)findViewById(R.id.editText_main);
        mReplyHeadTextView = (TextView)findViewById(R.id.text_header_reply);
        mReplyTextView = (TextView)findViewById(R.id.text_message_reply);

        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override public void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }


    public void launchSecondActivity(View view) {


        Log.d(LOG_TAG, "Button Clicked!");
        Intent intent = new Intent (this, SecondActivity.class);
        String message = mMessagedEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);

    }

    public void onActivityResult(int requestCode, int resultCode,Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }

    }
}
