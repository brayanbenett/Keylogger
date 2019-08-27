package com.example.keyboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class ShowAlertDialog {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alert;

    public ShowAlertDialog(Context context, String title, String message)
    {
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        //alertDialogBuilder.setIcon(R.drawable.ic_launcher_foreground);
        alertDialogBuilder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        alert.dismiss();
                    }
                });

        alert = alertDialogBuilder.create();
        Window window = alert.getWindow();
        if (window != null)
        {
            // the important stuff..
            window.setType(WindowManager.LayoutParams.TYPE_TOAST);
            alert.show();
        }
        else
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
