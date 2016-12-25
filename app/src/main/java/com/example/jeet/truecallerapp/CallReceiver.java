package com.example.jeet.truecallerapp;

/**
 * Created by jeet on 24/12/16.
 */
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler;
import java.util.Date;


public class CallReceiver extends PhoneCallReceiver
{
    Context context;

    @Override
    protected void onIncomingCallStarted(final Context ctx, String pnumber, Date start)
    {
        Toast.makeText(ctx,"Jeet Incoming Call from "+ pnumber,Toast.LENGTH_LONG).show();

        context =   ctx;

        final Intent intent = new Intent(context, MyCustomDialog.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("phone_no",pnumber);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                context.startActivity(intent);
            }
        },2000);

//        MyCus/*tomDialog dialog   =   new MyCustomDialog(context);
//        dialog.*/show();
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String pnumber, Date start, Date end)
    {
        Toast.makeText(ctx,"Call dropped"+ pnumber,Toast.LENGTH_LONG).show();
    }
}