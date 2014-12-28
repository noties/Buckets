package ru.noties.buckets.sample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import ru.noties.buckets.Buckets;
import ru.noties.buckets.IBucketUser;
import ru.noties.buckets.sample.R;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 28.12.2014.
 */
public class MainActivity extends Activity implements IBucketUser<MainController, MainBucket> {

    private TextView mView;

    @Override
    public void onCreate(Bundle sis) {
        super.onCreate(sis);

        setContentView(R.layout.activity_main);

        mView = (TextView) findViewById(R.id.text1);

        final MainBucket bucket = Buckets.getInstance().getBucket(this);
        final MainController controller = bucket.getController();
        controller.checkForData();


        if (sis == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_frame, new MainFragment())
                    .commit();
        }
    }

    public void setTextData(CharSequence text) {
        mView.setText(text);
    }

    @Override
    public MainBucket createDataBucket() {
        return new MainBucket();
    }

    @Override
    public MainController createController(MainBucket bucket) {
        return new MainController(this, bucket);
    }
}
