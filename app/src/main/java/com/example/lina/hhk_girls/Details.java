package com.example.lina.hhk_girls;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Details extends AppCompatActivity {
//int position;
   /* public Details(int position){
        this.position=position;

    }*/
    public Details(){


    }
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new MyDBHandler(getApplicationContext(), null, null,3);
        final TextView home1=(TextView) findViewById(R.id.home1);
        final TextView home2=(TextView) findViewById(R.id.home2);
        final TextView mobile1=(TextView) findViewById(R.id.mobile1);
        final TextView mobile2=(TextView) findViewById(R.id.mobile2);
        final TextView year=(TextView) findViewById(R.id.year);
        TextView name=(TextView) findViewById(R.id.name);
        Bundle b = getIntent().getExtras();
        final int position = b.getInt("position");
        final String  names= b.getString("names");
        final String  mobile11=b.getString("mobile1");
        final String  mobile22=b.getString("mobile2");
        final String  home11=b.getString("home1");
        final String  home22=b.getString("home2");
        final String  yearr=b.getString("year");
        final String table=b.getString("table");
       final String []dbNames=dbHandler.databaseToString("name",table).split("\n");
        final String []dbYear=dbHandler.databaseToString("year",table).split("\n");
        final String []dbM1=dbHandler.databaseToString("mobile1",table).split("\n");
        final String []dbM2=dbHandler.databaseToString("mobile2",table).split("\n");
        final String []dbH1=dbHandler.databaseToString("home1",table).split("\n");
        final String []dbH2=dbHandler.databaseToString("home2",table).split("\n");
        final String []dbNames1=dbHandler.getItemName(names, "name", table).split("\n");
        final String []dbYear1=dbHandler.getItemName(names, "year", table).split("\n");
        final String []dbM11=dbHandler.getItemName(names, "mobile1", table).split("\n");
        final String []dbM21=dbHandler.getItemName(names, "mobile2", table).split("\n");
        final String []dbH11=dbHandler.getItemName(names, "home1", table).split("\n");
        final String []dbH21=dbHandler.getItemName(names, "home2", table).split("\n");
        final String []ids=dbHandler.getItemName(names, "_id", table).split("\n");

        home1.setText(dbH11[0]);
        home2.setText(dbH21[0]);
        mobile1.setText(dbM11[0]);
        mobile2.setText(dbM21[0]);
        name.setText(dbNames1[0]);
        year.setText(dbYear1[0]);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabEdit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent=new Intent(Details.this,Edit.class);
                Bundle b = new Bundle();
                b.putInt("position", position); //Your id
                b.putString("names", dbNames[position]);
                b.putString("mobile1",dbM1[position]);
                b.putString("mobile2", dbM2[position]);
                b.putString("home1", dbH1[position]);
                b.putString("home2", dbH2[position]);
                b.putString("year",dbYear[position]);
                b.putString("table", table);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Details.this, MainActivity.class));
        finish();

    }

}
