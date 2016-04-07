package com.example.lina.hhk_girls;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class Add extends AppCompatActivity {
    static String item="lina";
    EditText name;
    EditText mobile1;
    EditText mobile2;
    EditText home1;
    EditText home2;
    EditText year;
    static File file1=new File("");
    static String f="";
    int j=1;
    MyDBHandler dbHandler;


    /* public Edit(String item){
         this.item=item;

     }
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new MyDBHandler(getApplicationContext(), null, null,3);
        name=(EditText) findViewById(R.id.nameA);
        mobile1=(EditText) findViewById(R.id.mobile1A);
        mobile2=(EditText) findViewById(R.id.mobile2A);
        home1=(EditText) findViewById(R.id.home1A);
        home2=(EditText) findViewById(R.id.home2A);
        year=(EditText) findViewById(R.id.yearA);
        Bundle b = getIntent().getExtras();
        final int position = b.getInt("position");
        final String  names= b.getString("names");
        final String  mobile11=b.getString("mobile1");
        final String  mobile22=b.getString("mobile2");
        final String  home11=b.getString("home1");
        final String  home22=b.getString("home2");
        final String  yearr=b.getString("year");
        final String table=b.getString("table");


       /* final String []dbYear=dbHandler.databaseToString("year",table).split("\n");
        final String []dbM1=dbHandler.databaseToString("mobile1",table).split("\n");
        final String []dbM2=dbHandler.databaseToString("mobile2",table).split("\n");
        final String []dbH1=dbHandler.databaseToString("home1",table).split("\n");
        final String []dbH2=dbHandler.databaseToString("home2", table).split("\n");*/

       /* home1.setText(dbH1[position]);
        home2.setText(dbH2[position]);
        mobile1.setText(dbM1[position]);
        mobile2.setText(dbM2[position]);
        name.setText(dbNames[position]);
        year.setText(dbYear[position])*/;
        // edit=(Button) findViewById(R.id.edit1);
        //  name.setEnabled(false);
        // name.setText(item);

      /*  edit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.getInputType();

                final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                edit.setText("Save");

                edit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        name.setText(name.getText());
                        item = name.getText().toString();
                        name.setEnabled(false);
                        edit.setText("Edit");

                    }
                });


            }
        });

*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSave1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                final String []dbNames1=dbHandler.databaseToString("name",table).split("\n");
                for(int i=0;i<dbNames1.length;i++)
                    if(name.getText().toString().equals(dbNames1[i])){
                    name.setText(dbNames1[i]+" "+j);
                        j++;
                }
                if(year.getText().toString().equals(""))
                    year.setText("none");
                if(mobile1.getText().toString().equals(""))
                    mobile1.setText("none");
                if(mobile2.getText().toString().equals(""))
                    mobile2.setText("none");
                if(home1.getText().toString().equals(""))
                    home1.setText("none");
                if(home2.getText().toString().equals(""))
                    home2.setText("none");
                dbHandler.addRecord(name.getText().toString(),year.getText().toString(),mobile1.getText().toString(),
                        mobile2.getText().toString(),home1.getText().toString(),home2.getText().toString(),
                        "name","year","mobile1","mobile2","home1","home2",table);

                final String []dbNames=dbHandler.databaseToString("name",table).split("\n");
                final String []dbYear=dbHandler.databaseToString("year",table).split("\n");
                final String []dbM1=dbHandler.databaseToString("mobile1",table).split("\n");
                final String []dbM2=dbHandler.databaseToString("mobile2",table).split("\n");
                final String []dbH1=dbHandler.databaseToString("home1",table).split("\n");
                final String []dbH2=dbHandler.databaseToString("home2", table).split("\n");
                Intent intent=new Intent(Add.this,Details.class);

                Bundle b = new Bundle();
                b.putInt("position", position+1); //Your id
                b.putString("names", dbNames[position + 1]);
                b.putString("mobile1",dbM1[position+1]);
                b.putString("mobile2", dbM2[position+1]);
                b.putString("home1", dbH1[position+1]);
                b.putString("home2", dbH2[position+1]);
                b.putString("year", dbYear[position+1]);
                b.putString("table", table);
                Toast.makeText(getApplicationContext(), "Saved ", Toast.LENGTH_SHORT).show();

           /*     try
                {
                   *//* File file1 =new File (file);
                    BufferedReader reader = new BufferedReader(new FileReader(file1));
                    String line = "", oldtext = "";
                    while((line = reader.readLine()) != null)
                    {
                        oldtext += line + "\r\n";
                    }
                    reader.close();*//*
                    // replace a word in a file
                    //String newtext = oldtext.replaceAll("drink", "Love");

                    //To replace a line in a file
                  //  String newtext = oldtext.replaceAll(original, replacement);
                    String [] newtext=new String [names.length];
                for(int i=0;i<names.length;i++){
                     newtext[i]=names[i]+","+mobile11[i]+","+mobile22[i]+","+home11[i]+","+home22[i]
                             +","+yearr[i]+","+"\r\n";
                }
                    StreamResult result = new StreamResult(new File(android.os.Environment.getExternalStorageDirectory(), file));

                    FileWriter writer = new FileWriter(file);
                    writer.write(String.valueOf(newtext));
                    writer.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }*/
                /*String [] newtext=new String [names.length];
                for(int i=0;i<names.length;i++){
                    newtext[i]=names[i]+","+mobile11[i]+","+mobile22[i]+","+home11[i]+","+home22[i]
                            +","+yearr[i]+","+"\r\n";
                }*/
            /*    try {
                    FileOutputStream fileout=openFileOutput(file, MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write(newtext.toString());
                    outputWriter.close();

                    //display file saved message
                    Toast.makeText(getBaseContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }*/
              /*  File file1;
                FileOutputStream outputStream;
                try {
                    file1 = new File(Environment.getExternalStorageDirectory(), file);

                    outputStream = new FileOutputStream(file1);
                    outputStream.write(newtext.toString().getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                /*FileOutputStream outputStream = null;
                try {
                    outputStream = openFileOutput(file, Context.MODE_PRIVATE);
                    outputStream.write(newtext.toString().getBytes());
                    Context context=getApplicationContext();
                    f=String.valueOf(context.getDir(file, Context.MODE_PRIVATE));
                    outputStream.close();*/
                   /* String y= String.valueOf(outputStream);


                   file1 = new File(f);*/
                   /* BufferedReader reader = new BufferedReader(new FileReader(f));
                    String line = "", oldtext = "";
                    while((line = reader.readLine()) != null)
                    {
                        oldtext += line + "\r\n";
                    }
                    reader.close();*/
                //To replace a line in a file
                   /* String content = oldtext.replaceAll(original, replacement);
                    FileWriter writer = new FileWriter(file);
                    writer.write(content);
                    writer.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                intent.putExtras(b);

                startActivity(intent);

            }
        });

    }





}
