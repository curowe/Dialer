package me.kareluo.custom.dialer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by felix on 16/1/1.
 */
public class DialerActivity extends Activity implements View.OnClickListener {

    private EditText et_number, et_content;

    private TextView tv_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialer);
        findViewById(R.id.bt_dial).setOnClickListener(this);
        findViewById(R.id.bt_send).setOnClickListener(this);


        findViewById(R.id.entry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DialerActivity.this, ContentActivity.class);
                intent.putExtra("content_id", R.layout.activity_relative);
                startActivity(intent);
            }
        });


        tv_name = (TextView) findViewById(R.id.tv_name);

        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DialerActivity.this, ContentActivity.class);
                intent.putExtra("name", tv_name.getText().toString());

                startActivityForResult(intent, 1);
            }
        });
    }

    public void turnList(View v){
        startActivity(new Intent(this, SlideActivity.class));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    tv_name.setText(name);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        et_number = (EditText) findViewById(R.id.et_number);
        et_content = (EditText) findViewById(R.id.et_content);
        String number = et_number.getText().toString();
        String content = et_content.getText().toString();
        switch (v.getId()) {
            case R.id.bt_dial:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;
            case R.id.bt_send:
                SmsManager smssender = SmsManager.getDefault();
                ArrayList<String> sms = smssender.divideMessage(content);
//                for (int i = 0; i < sms.size(); i++) {
//                    String string = sms.get(i);
//                    smssender.sendTextMessage(number, null, sms.get(i), null, null);
//                }
                for (String string : sms) {
                    smssender.sendTextMessage(number, null, string, null, null);
                }
                break;

        }


    }
}
