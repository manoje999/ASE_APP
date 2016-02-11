package com.manojprabhakar.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void MainActivity(View view) {

        EditText firstname = (EditText) findViewById(R.id.FirstName);
        EditText Lastname = (EditText) findViewById(R.id.LastName);
        EditText Address = (EditText) findViewById(R.id.Address);
        EditText email = (EditText) findViewById(R.id.Email);
        EditText password = (EditText) findViewById(R.id.Password);
        String fname = firstname.getText().toString();
        String lname = Lastname.getText().toString();
        String Addr = Address.getText().toString();
        String emailid = email.getText().toString();
        String pwd = password.getText().toString();



       // Boolean validationFlag = false;
       // if (!Addr.isEmpty() && !emailid.isEmpty() && !pwd.isEmpty()) {
         //       validationFlag = true;
       // }
        //if (!validationFlag) {

      //      Errormsg.setVisibility(View.VISIBLE);
        //}
        // errorText.setVisibility(View.VISIBLE);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }*/

       // else {
            Intent redirect = new Intent(MainActivity.this, Options.class);
            startActivity(redirect);


        //}
    }
}