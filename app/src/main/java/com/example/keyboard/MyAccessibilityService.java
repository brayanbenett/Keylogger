package com.example.keyboard;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MyAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        String eventText = null;

        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                eventText = "Typed: ";
                break;
        }
        eventText = eventText + event.getText();

        if(eventText.equals("Typed: [brayan]")) {
            //System.out.println("Your name is");
            Log.d("keyTag","Your name is");

/*
            AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext())
                    .setTitle("Warning")
                    .setMessage("Sensitive Information Found").setCancelable(true)
                    .create();

            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            //alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
            alertDialog.show();*/

            //Toast.makeText(getApplicationContext(), "Warning Sensitive information found", Toast.LENGTH_LONG).show();
            showToast();
        }
        else {
            //System.out.println("ACCESSIBILITY SERVICE : " + eventText);
            Log.d("keyTag",eventText);
        }
    }

    public void showToast(){

        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.toast_layout,null);

        TextView txtV = layout.findViewById(R.id.toast_txt);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,30);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();


    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {

        AccessibilityServiceInfo info = getServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.notificationTimeout = 100;
        this.setServiceInfo(info);

    }
}