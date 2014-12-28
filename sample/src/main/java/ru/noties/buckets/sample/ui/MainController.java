package ru.noties.buckets.sample.ui;

import android.text.TextUtils;

import ru.noties.buckets.AbsController;
import ru.noties.debug.Debug;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public class MainController extends AbsController<MainActivity, MainBucket> {

    public MainController(MainActivity activity, MainBucket bucket) {
        super(activity, bucket);
    }

    public void checkForData() {
        final MainBucket bucket = getBucket();
        if (TextUtils.isEmpty(bucket.getData())) {
            Debug.i("Activity data is empty, creating");
            bucket.createData();
        } else {
            Debug.i("Activity data is not empty");
        }
        final CharSequence data = bucket.getData();
        final MainActivity activity = getActivity();
        activity.setTextData(data);
    }
}
