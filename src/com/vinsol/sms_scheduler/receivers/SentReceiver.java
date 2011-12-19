package com.vinsol.sms_scheduler.receivers;

import com.vinsol.sms_scheduler.DBAdapter;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SentReceiver extends BroadcastReceiver{
	
	DBAdapter mdba;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		int part = (int)intent.getIntExtra("PART", 0);
		long id = (long)intent.getLongExtra("ID", 0);
		int msgSize = (int)intent.getIntExtra("SIZE", 0);
		String number = intent.getStringExtra("NUMBER");
		
		Intent mIntent;
		PendingIntent pi;
		AlarmManager am;
		Log.i("MESSAGE", "ID in SentReceiver : " + id);
		mdba = new DBAdapter(context);
		switch (getResultCode())
         { 	
			
             case Activity.RESULT_OK:
            	 mdba.open();
            	 Cursor cur = mdba.fetchSpanForSms(id);
            	 cur.moveToFirst();
            	 String receiverName = cur.getString(cur.getColumnIndex(DBAdapter.KEY_SPAN_DN));
                 
                 mdba.increaseSent(id);
                 mdba.close();
                 
                 
                 mIntent = new Intent();
                 mIntent.putExtra("ID", id);
                 mIntent.setAction("My special action");
                 pi = PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
         		
         		 am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
         		 am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
         		 
//         		 mIntent.setAction("update action");
//         		 pi = PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//        		 am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
        		 
                 
                 break;
                 
             case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
            	
            	 mIntent = new Intent();
                 mIntent.putExtra("ID", id);
                 mIntent.setAction("My special action");
                 pi = PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
         		
         		 am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
         		 am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
//                 Toast.makeText(context, "Generic failure",
//                         Toast.LENGTH_SHORT).show();
                 break;
                 
             case SmsManager.RESULT_ERROR_NO_SERVICE:
            	
            	 mIntent = new Intent();
                 mIntent.putExtra("ID", id);
                 mIntent.setAction("My special action");
                 pi = PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
         		
         		 am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
         		 am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
//                 Toast.makeText(context, "No service", 
//                         Toast.LENGTH_SHORT).show();
                 break;
                 
             case SmsManager.RESULT_ERROR_NULL_PDU:
            	 mIntent = new Intent();
                 mIntent.putExtra("ID", id);
                 mIntent.setAction("My special action");
                 pi = PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
         		
         		 am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
         		 am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
//                 Toast.makeText(context, "Null PDU", 
//                         Toast.LENGTH_SHORT).show();
                 break;
                 
             case SmsManager.RESULT_ERROR_RADIO_OFF:
            	
            	 mIntent = new Intent();
                 mIntent.putExtra("ID", id);
                 mIntent.setAction("My special action");
                 pi = PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
         		
         		 am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
         		 am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
//                 Toast.makeText(context, "Radio off", 
//                         Toast.LENGTH_SHORT).show();
                 break;
         }
	}

	
}