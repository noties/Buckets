package ru.noties.buckets;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseIntArray;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public class DataApplicationCallbacks implements Application.ActivityLifecycleCallbacks {

    private static final String ARG_HASH = "arg_data_provider_hash";

    private final SparseIntArray mBucketUsers;
    private final SparseArray<Bucket> mArray;
    {
        mBucketUsers = new SparseIntArray();
        mArray = new SparseArray<>();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        if (!(activity instanceof IBucketUser)) {
            return;
        }

        final int hash = activity.hashCode();
        mBucketUsers.put(hash, 0);

        final IBucketUser iBucketUser = (IBucketUser) activity;

        if (savedInstanceState != null
                && savedInstanceState.containsKey(ARG_HASH)) {

            // activity was recreated & contains hash - so rebind
            final int prevHash = savedInstanceState.getInt(ARG_HASH, -1);
            final Bucket data = mArray.get(prevHash, null);

            if (isBucketUser(prevHash)) {
                mBucketUsers.delete(prevHash);
            }

            if (data != null) {
                mArray.remove(prevHash);
                mArray.put(hash, data);

                // noinspection unchecked
                data.setController(iBucketUser.createController(data));

                data.onRecreate();
                return;
            }

        }

        // create new bucket
        final Bucket newBucket = ((IBucketUser) activity).createDataBucket();

        // noinspection unchecked
        newBucket.setController(iBucketUser.createController(newBucket));
        newBucket.onCreate();

        mArray.put(hash, newBucket);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        final int hash = activity.hashCode();

        if (!isBucketUser(hash)) {
            return;
        }

        // put hash in state
        if (!activity.isFinishing()) {
            outState.putInt(ARG_HASH, hash);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        final int hash = activity.hashCode();

        if (!isBucketUser(hash)) {
            return;
        }

        final Bucket bucket = mArray.get(hash, null);

        if (bucket != null) {
            // noinspection unchecked
            bucket.setController(null);
        }

        if (activity.isFinishing()) {

            // clear data
            if (bucket != null) {
                bucket.onDestroy();
            }

            mArray.remove(hash);

            // trim unused
            if (mArray.size() == 0) {
                mArray.clear();
            }

            mBucketUsers.delete(hash);

            if (mBucketUsers.size() == 0) {
                mBucketUsers.clear();
            }

            return;
        }

        // activity is going to be recreated
        if (bucket != null) {
            bucket.onInvalidated();
        }
    }

    private boolean isBucketUser(int hashCode) {
        return mBucketUsers.get(hashCode, -1) != -1;
    }

    Bucket getBucket(int hash) {
        return mArray.get(hash, null);
    }
}
