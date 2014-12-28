package ru.noties.buckets;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public abstract class BucketAdapter<C extends IController> extends Bucket<C> {

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
