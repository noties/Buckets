package ru.noties.buckets.sample.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import ru.noties.buckets.Bucket;
import ru.noties.buckets.BucketAdapter;
import ru.noties.buckets.Buckets;

/**
 * Created by Dimaster on 28.12.2014.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "tag.MainFragment";

    private FragmentChildBucket mBucket;

    @Override
    public void onActivityCreated(Bundle sis) {
        super.onActivityCreated(sis);

        final Bucket<?> bucket = Buckets.getInstance().getBucket(getActivity());
        if (!bucket.hasChild(TAG)) {
            mBucket = new FragmentChildBucket();
            bucket.addChild(TAG, mBucket);
        } else {
            mBucket = bucket.getChild(TAG);
        }

        mView.setText(mBucket.getData());
    }

    private TextView mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle sis) {
        mView = new TextView(getActivity());
        return mView;
    }

    private static class FragmentChildBucket extends BucketAdapter {

        private CharSequence mData;

        public CharSequence getData() {
            if (TextUtils.isEmpty(mData)) {
                mData = "Child data, created: " + new Date();
            }
            return mData;
        }
    }
}
