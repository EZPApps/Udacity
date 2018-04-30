package com.udacity.gradle.builditbigger.androidTest;

import org.junit.runner.RunWith;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;

import com.udacity.gradle.builditbigger.JokesAsyncTask;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    @Test
    public void testDoInBackground() throws Exception {

        String joke;

        JokesAsyncTask task = new JokesAsyncTask(new JokesAsyncTask.JokeFetcher() {
            @Override
            public void onJokeFetched(String result) {
            }
        });


        task.execute(new Pair<Context, String>(InstrumentationRegistry.getTargetContext(), "0"));

        Thread.sleep(5000);

        joke = task.get();
        assertNotNull("Joke is null", joke);
        assertTrue("Joke is empty string", !joke.isEmpty());
    }
}
