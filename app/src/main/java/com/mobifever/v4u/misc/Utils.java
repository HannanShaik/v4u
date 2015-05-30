package com.mobifever.v4u.misc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class Utils {

    @SuppressLint("ResourceAsColor")
    public static void showAlertCrouton(Activity activity, int resId) {
        // Careful: croutons don't need resolved color! getResources().getColor() will cause a crash!
        Crouton.makeText(activity, resId, Style.ALERT).show();
    }

    @SuppressLint("ResourceAsColor")
    public static void showAlertCrouton(Activity activity, String message) {
        // Careful: croutons don't need resolved color! getResources().getColor() will cause a crash!
        Crouton.makeText(activity, message, Style.ALERT).show();
    }

    @SuppressLint("ResourceAsColor")
    public static Crouton getInfiniteAlertCrouton(Activity activity, String message) {
        Configuration configuration = new Configuration.Builder().setDuration(Configuration.DURATION_INFINITE).build();
        return Crouton.makeText(activity, message, Style.ALERT)
                .setConfiguration(configuration);
    }

    @SuppressLint("ResourceAsColor")
    public static void showInfiniteDurationAlertCrouton(Activity activity, String message) {
        // Careful: croutons don't need resolved color! getResources().getColor() will cause a crash!
        Configuration configuration = new Configuration.Builder().setDuration(Configuration.DURATION_INFINITE).build();
        final Crouton crouton = Crouton.makeText(activity, message, Style.ALERT)
                .setConfiguration(configuration);
        crouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crouton.hide(crouton);
            }
        });
        crouton.show();
    }

    public static void showInfoCrouton(Activity activity, int resId) {
        Crouton.makeText(activity, resId, Style.INFO).show();
    }

    public static void showAlertBox(Activity activity, String title, String message, String yesValue){
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
    	alertDialogBuilder.setTitle(title);
	      alertDialogBuilder.setMessage(message);
	      alertDialogBuilder.setPositiveButton(yesValue, 
	      new DialogInterface.OnClickListener() {
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
	        	 arg0.cancel();
	         }
	      });	
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
    }
}
