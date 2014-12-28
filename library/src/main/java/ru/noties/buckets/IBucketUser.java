package ru.noties.buckets;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public interface IBucketUser<C extends IController, B extends Bucket<C>> {

    B createDataBucket();
    C createController(B bucket);

}
