package com.dry.messagestest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import android.telephony.SmsManager;

/**
 * A class to listen to the situation about sending and arriving of SMS.
 *
 * @author DuRuyao
 * Create 19/03/20
 */
public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMSMethod.SMS_SEND_ACTION)) {
            try {

                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "Success to send", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context, "Failed to send, unknown reason", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(context, "Failed to send, radio is off", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context, "Failed to send, PDU is unusual", Toast.LENGTH_SHORT).show();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else if (intent.getAction().equals(SMSMethod.SMS_DELIVERED_ACTION)) {
            try {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "Message arrived", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        /* 短信未送达 */
                        Toast.makeText(context, "Cannot arrived, unknown reason", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(context, "Cannot arrived, receiver's radio is off", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context, "Cannot arrived, receiver's PDU is unusual", Toast.LENGTH_SHORT).show();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

