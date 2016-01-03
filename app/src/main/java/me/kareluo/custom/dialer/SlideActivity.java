package me.kareluo.custom.dialer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felix on 16/1/3.
 */
public class SlideActivity extends Activity {
    private ListView mListView;
    private ListAdapter mAdapter;

    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        mDatas = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            mDatas.add("" + i);
        }


        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new ListAdapter();
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = mAdapter.getItem(position);
                Toast.makeText(SlideActivity.this, item + "被点击了", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public String getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = getLayoutInflater().inflate(R.layout.layout_item, parent, false);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_item);

            ImageView icon = (ImageView) inflate.findViewById(R.id.iv_icon);
            icon.setImageResource(R.drawable.icon);

            textView.setText(getItem(position));
            return inflate;
        }
    }
}
