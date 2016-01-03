package me.kareluo.custom.dialer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by felix on 16/1/2.
 */
public class ContentActivity extends Activity implements View.OnClickListener {

    private Button bt_cancel, bt_certain;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
        bt_certain = (Button) findViewById(R.id.bt_certain);
        et_content = (EditText) findViewById(R.id.et_content);
        bt_cancel.setOnClickListener(this);
        bt_certain.setOnClickListener(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        et_content.setText(name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cancel:
                finish();
                break;
            case R.id.bt_certain:
                Intent intent = new Intent();
                intent.putExtra("name", et_content.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
