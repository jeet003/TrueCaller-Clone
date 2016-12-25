package com.example.jeet.truecallerapp;

/**
 * Created by jeet on 24/12/16.
 */
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyCustomDialog extends Activity
{
    TextView tv_client;
    String phone_no;
    Button dialog_ok;
    TextView relation;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.setFinishOnTouchOutside(false);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog);
            initializeContent();
            /*
            LayoutInflater layoutInflater =
                    (LayoutInflater)getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.dialog, null);
            final PopupWindow popupWindow = new PopupWindow(
                    popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

            /*WindowManager.LayoutParams params = getWindow().getAttributes();
            params.x = -100;
            params.height = 70;
            params.width = 1000;
            params.y = -50;

            this.getWindow().setAttributes(params);*/
            StringBuffer sb = new StringBuffer();
            phone_no    =   getIntent().getExtras().getString("phone_no");

            String strOrder = android.provider.CallLog.Calls.DATE + " DESC";


            Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,
                    null, null, strOrder);
            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
            sb.append("\nLast Call Log :");
            while (managedCursor.moveToNext()) {
                String phNum = managedCursor.getString(number);
                String callTypeCode = managedCursor.getString(type);


                long seconds=managedCursor.getLong(date);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy  hh:mm:ss a");
                String dateString = formatter.format(new Date(seconds));



                String strcallDate = managedCursor.getString(date);
                Date callDate = new Date(Long.valueOf(strcallDate));




                String callDuration = managedCursor.getString(duration);
                String callType = null;
                int callcode = Integer.parseInt(callTypeCode);
                switch (callcode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        callType = "Outgoing";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        callType = "Incoming";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        callType = "Missed";
                        break;
                }
                if(phone_no.equalsIgnoreCase(phNum))
                {
                    sb.append("\n----------------------------------");
                    sb.append("\nPhone Number:--- " + phNum + " \nCall Type:--- "
                            + callType + " \nCall Date:--- " + dateString
                            + " \nCall duration in sec :--- " + callDuration);
                    sb.append("\n----------------------------------");
                    break;
                }

            }
            managedCursor.close();






            tv_client.setText(""+phone_no +" is calling you");
            relation.setText(sb);
            dialog_ok.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    MyCustomDialog.this.finish();
//                    this.setFinishOnTouchOutside(false);
                    System.exit(0);
                }
            });
        }
        catch (Exception e)
        {
            Log.d("Exception", e.toString());
            e.printStackTrace();
        }
    }

    private void initializeContent()
    {
        tv_client   = (TextView) findViewById(R.id.tv_client);
        dialog_ok   = (Button) findViewById(R.id.dialog_ok);
        relation    = (TextView) findViewById(R.id.relation);
    }
}