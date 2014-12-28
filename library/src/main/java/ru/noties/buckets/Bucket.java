package ru.noties.buckets;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public abstract class Bucket<C extends IController> implements IBucket {

    private final Map<String, IBucket> mChildren;
    {
        mChildren = new HashMap<>();
    }

    private C mController;
    private boolean mIsInvalidated;

    public void addChild(String key, IBucket value) {
        mChildren.put(key, value);
    }

    public void removeChild(String key) {
        mChildren.remove(key);
    }

    public boolean hasChild(String tag) {
        return mChildren.containsKey(tag);
    }

    @SuppressWarnings("unchecked")
    public <T> T getChild(String key) {
        return (T) mChildren.get(key);
    }

    @Override
    public final void onCreate() {

        onCreated();

        // 99.9% there are no children
        if (mChildren.size() == 0) {
            return;
        }

        for (Map.Entry<String, IBucket> entry: mChildren.entrySet()) {
            final IBucket bucket= entry.getValue();
            bucket.onCreate();
        }
    }

    @Override
    public final void onInvalidate() {

        mIsInvalidated = true;

        if (mController != null) {
            mController.onInvalidate();
        }

        onInvalidated();

        if (mChildren.size() != 0) {
            for (Map.Entry<String, IBucket> entry: mChildren.entrySet()) {
                final IBucket bucket= entry.getValue();
                bucket.onInvalidate();
            }
        }
    }

    @Override
    public final void onRecreate() {

        mIsInvalidated = false;

        onRecreated();

        if (mChildren.size() != 0) {
            for (Map.Entry<String, IBucket> entry: mChildren.entrySet()) {
                final IBucket bucket= entry.getValue();
                bucket.onRecreate();
            }
        }
    }

    @Override
    public final void onDestroy() {

        onDestroyed();

        if (mChildren.size() == 0) {
            return;
        }

        for (Map.Entry<String, IBucket> entry: mChildren.entrySet()) {
            final IBucket bucket= entry.getValue();
            bucket.onDestroy();
        }
    }

    // small gap between onInvalidate & onRecreate
    public boolean isAlive() {
        return !mIsInvalidated;
    }

    public final void setController(C controller) {
        this.mController = controller;
    }

    public C getController() {
        return mController;
    }

    protected void onControllerSet() {}

    protected abstract void onCreated();
    protected abstract void onRecreated();
    protected abstract void onDestroyed();
    protected abstract void onInvalidated();

}
