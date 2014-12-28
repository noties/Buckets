package ru.noties.buckets.sample;

import android.app.Application;

import ru.noties.buckets.Buckets;
import ru.noties.debug.Debug;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Debug.init(true);
        Buckets.getInstance().init(this);
    }
}
