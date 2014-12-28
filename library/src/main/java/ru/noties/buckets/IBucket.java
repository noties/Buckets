package ru.noties.buckets;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public interface IBucket {

    // is called when new bucket is created
    void onCreate();

    // is called when binded activity is recreated
    void onRecreate();

    // is called when binded activity is going to be recreated
    // so, release activity instance specific resources
    // view, controllers, etc.
    void onInvalidate();

    // is called when bucket binded activity is finishing
    // good for final release of resources
    void onDestroy();

}
