package com.manojprabhakar.pushnotification;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by ManojPrabhakar on 4/29/2016.
 */
public class function implements android.widget.AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long id){

        String str = parent.getItemAtPosition(pos).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0)
    {

    }
}
