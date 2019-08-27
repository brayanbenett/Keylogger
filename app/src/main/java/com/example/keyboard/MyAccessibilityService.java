package com.example.keyboard;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
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

            Toast.makeText(getApplicationContext(), "Warning Sensitive information found", Toast.LENGTH_LONG).show();

        }
        else {
            //System.out.println("ACCESSIBILITY SERVICE : " + eventText);
            Log.d("keyTag",eventText);
        }
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