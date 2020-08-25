package com.raju.attendancetracker;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

public class ShortAttendanceService extends BroadcastReceiver {
    SqllitDatabase mydb;
    public static final String CHANNEL_ID = "notifyLemubit";
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context,view_att_helper.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(view_att_helper.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);
        mydb=new SqllitDatabase(context);
        Cursor res=mydb.getAllData();
        String str="nothing";
        while(res.moveToNext()){
            str=res.getString(1);
        }

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_looks_two)
                .setContentTitle("Hello " + str)
                .setContentText("Your Attendance are Too short please maintain your attendance")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
        notificationManager.notify(200,builder.build());
    }
}
