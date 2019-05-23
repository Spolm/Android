package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.example.powerreceiver.MainActivity.ACTION_CUSTOM_BROADCAST;


public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = "intent action desconocida ";
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power conectado!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power desconectado!";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Broadcast Personalizado Recibido";
                    break;
            }
            //Muestre el toast.
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }


    }
}
