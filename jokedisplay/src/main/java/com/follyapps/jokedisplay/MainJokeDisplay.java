package com.follyapps.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Merwiji on 5/10/2017.
 */

public class MainJokeDisplay extends AppCompatActivity {
    public static final String JOKE_EXTRA = "joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_display);

        // retrieving the bundle
//        Bundle bundle;
//        Intent intentThatStartedThisActivity = getIntent();
//        bundle = intentThatStartedThisActivity.getExtras();
//        String jokeToShow = bundle.getString("BUNDLE_JOKE");

        String joke = getIntent().getStringExtra(JOKE_EXTRA);
        TextView textView = (TextView) findViewById(R.id.joke_display);
        textView.setText(joke);
    }


}
