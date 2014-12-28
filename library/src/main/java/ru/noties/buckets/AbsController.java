package ru.noties.buckets;

import android.app.Activity;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public abstract class AbsController<A extends Activity & IBucketUser, B extends Bucket>
        implements IController {

    private A mActivity;
    private B mBucket;

    private boolean mIsInvalidated;

    public AbsController(A activity, B bucket) {
        this.mActivity = activity;
        this.mBucket = bucket;
    }

    public A getActivity() {
        return mActivity;
    }

    public B getBucket() {
        return mBucket;
    }

    @Override
    public final void onInvalidate() {
        // release the sources

        mIsInvalidated = true;

        mActivity   = null;
        mBucket     = null;

        onRelease();
    }

    protected void onRelease() {}

    public boolean isInvalidated() {
        return mIsInvalidated;
    }
}
