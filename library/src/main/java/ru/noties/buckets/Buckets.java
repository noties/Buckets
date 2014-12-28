package ru.noties.buckets;

import android.app.Activity;
import android.app.Application;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public class Buckets {

    private static volatile Buckets sInstance = null;

    public static Buckets getInstance() {
        Buckets localInstance = sInstance;
        if (localInstance == null) {
            synchronized (Buckets.class) {
                localInstance = sInstance;
                if (localInstance == null) {
                    localInstance = sInstance = new Buckets();
                }
            }
        }
        return localInstance;
    }

    private DataApplicationCallbacks mCallbacks;

    public void init(Application application) {
        mCallbacks = new DataApplicationCallbacks();
        application.registerActivityLifecycleCallbacks(mCallbacks);
    }

    // should we create it again if we are dataUser, but bucket is null?
    // or it will be created in callbacks? hm...
    @SuppressWarnings("unchecked")
    public <T> T getBucket(Activity activity) {
        return (T) getBucket(activity.hashCode());
    }

    @SuppressWarnings("unchecked")
    public <T> T getBucket(int hash) {
        return (T) mCallbacks.getBucket(hash);
    }
}
