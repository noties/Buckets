package ru.noties.buckets.sample.ui;

import java.util.Date;

import ru.noties.buckets.Bucket;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public class MainBucket extends Bucket<MainController> {

    private CharSequence mData;

    public CharSequence getData() {
        return mData;
    }

    public void createData() {
        mData = "Activity data, created at: " + new Date();
    }

    @Override
    protected void onCreated() {

    }

    @Override
    protected void onRecreated() {

    }

    @Override
    protected void onDestroyed() {

    }

    @Override
    protected void onInvalidated() {

    }
}
