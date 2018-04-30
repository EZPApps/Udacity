package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacity.android_joke_lib.JokeActivity;
import com.udacity.lib.JokeSmith;

public class MainActivity extends AppCompatActivity {

    int jokeCount = 0;
    public String joke;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
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

    public void tellJoke(View view) {
        JokeSmith jokesmith = new JokeSmith();

        new JokesAsyncTask(
        new JokesAsyncTask.JokeFetcher(){
            @Override
            public void onJokeFetched(String result) {
                Intent intent = new Intent(mContext, JokeActivity.class);
                intent.putExtra(JokeActivity.joke_key, result);
                startActivity(intent);
            }
        }).execute(new Pair<Context, String>(this, Integer.toString(jokeCount)));

        if (jokesmith.getNumberOfJokes() == jokeCount + 1)
            jokeCount = 0;
        else
            jokeCount++;
    }

    public void setJoke(String joke) {
        this.joke = joke;


    }


}
