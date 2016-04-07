package com.example.lina.hhk_girls;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;


public class E3dady extends Fragment {
    // String[] numbers={"1","2","3","4","5","6","7","8","9"};
    // String [] names={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};
    String [] names;
    String [] mobile1;
    String [] mobile2;
    String [] home1;
    String [] home2;
    String [] year;
    String [] girl;
    String[] N;
    String[]NN;
    int jj=0;
    ListView lv;
    Integer [] k;
    MyDBHandler dbHandler;



    int i=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_e3dady, container, false);
        // dbHandler = new MyDBHandler(this.getContext(), null, null, 3);

        lv = (ListView) view.findViewById(R.id.list2);
        dbHandler = new MyDBHandler(getContext(), null, null,3);

        InputStream stream = null;
        try {
            stream =getContext().getAssets().open("E3dady-Girls.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int size = 0;
        try {
            size = stream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[size];
        try {
            stream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!dbHandler.checkForTables("girlsE3dady")){
            String text = new String(buffer);
            String [] records=text.split("\r\n");
            names=new String[records.length];
            girl=new String[6];
            mobile1=new String[records.length];
            mobile2=new String[records.length];
            home1=new String[records.length];
            home2=new String[records.length];
            year=new String[records.length];
            String table="girlsE3dady";
            while(i<records.length){
                girl= (records[i].split(","));
                names[i]=girl[0];
                mobile1[i]=girl[1];
                mobile2[i]=girl[2];
                home1[i]=girl[3];
                home2[i]=girl[4];
                year[i]=girl[5];
                // String original= names[i]+","+mobile1[i]+","+mobile2[i]+","+home1[i]+","+home2[i]+","+year[i]+",";
                dbHandler.addRecord(names[i],year[i],mobile1[i],mobile2[i],home1[i],home2[i],"name","year","mobile1","mobile2","home1","home2",table);



                i++;


            }

        }


        final String []dbNames=dbHandler.databaseToString("name","girlsE3dady").split("\n");
        final String []dbYear=dbHandler.databaseToString("year","girlsE3dady").split("\n");
        final String []dbM1=dbHandler.databaseToString("mobile1","girlsE3dady").split("\n");
        final String []dbM2=dbHandler.databaseToString("mobile2","girlsE3dady").split("\n");
        final String []dbH1=dbHandler.databaseToString("home1","girlsE3dady").split("\n");
        final String []dbH2=dbHandler.databaseToString("home2", "girlsE3dady").split("\n");
       /*String [] dbNames=new String[dbHandler.getCountt("girlsE3dady")];
        for(int i=0;i<dbNames.length;i++){
            dbNames[i]=s[i];
        }*/


        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                R.layout.listview_item, R.id.textv, dbNames);

        // Assign adapter to ListView
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv.setMultiChoiceModeListener(new ModeCallback());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int j, long arg3) {
                // TODO Auto-generated method stub
                int itemi = j;

                // ListView Clicked item value
                String itemValue = (String) lv.getItemAtPosition(j);

                // Show Alert
                /*Toast.makeText(getContext(),
                        "i :" + itemi + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();*/
                // Details details=new Details(itemi);
                Intent intent = new Intent(getContext(), Details.class);
                Bundle b = new Bundle();
                b.putInt("position", j); //Your id
                b.putString("names", dbNames[j]);
                b.putString("mobile1", dbM1[j]);
                b.putString("mobile2", dbM2[j]);
                b.putString("home1", dbH1[j]);
                b.putString("home2", dbH2[j]);
                b.putString("year", dbYear[j]);
                b.putString("table", "girlsE3dady");
                intent.putExtras(b);

                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabAdd1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Add.class);
                Bundle b = new Bundle();
                int o = dbHandler.getCountt("girlsE3dady") - 1;

                b.putInt("position", o);
                b.putString("names", dbNames[o]);
                b.putString("mobile1", dbM1[o]);
                b.putString("mobile2", dbM2[o]);
                b.putString("home1", dbH1[o]);
                b.putString("home2", dbH2[o]);
                b.putString("year", dbYear[o]);
                b.putString("table", "girlsE3dady");
                intent.putExtras(b);
                //i++;
                startActivity(intent);
            }
        });

        N=new String[dbNames.length];
        NN=new String[N.length];
        k=new Integer[NN.length];
        N=dbNames;

        return view;
    }


    public void onPostCreate(Bundle savedInstanceState) {
        onPostCreate(savedInstanceState);
        getActivity().getActionBar().setSubtitle("Long press to start selection");
    }

    private class ModeCallback implements ListView.MultiChoiceModeListener {

        int u=0;
        @Override
        public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
            final int checkedCount = lv.getCheckedItemCount();


            switch (checkedCount) {
                case 0:
                    mode.setSubtitle(null);
                    break;
                case 1:
                    mode.setSubtitle("One item selected");
                    break;
                default:
                    mode.setSubtitle("" + checkedCount + " items selected");
                    break;
            }
            u =position;
            NN[jj]=N[u];
            k[jj]=u+1;
            jj++;

        }

        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            MenuInflater inflater =getActivity().getMenuInflater();
            inflater.inflate(R.menu.list_select_menu, menu);
            mode.setTitle("Select Items");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                    // Setting Dialog Title
                    alertDialog.setTitle("Confirm Delete...");

                    // Setting Dialog Message
                    alertDialog.setMessage("Are you sure you want delete this?");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.delete);

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                            // Write your code here to invoke YES event
                          //  dbHandler.deleteGirl("girlsE3dady", u,k);
                            String s= String.valueOf(lv.getCheckedItemCount());
                            Toast.makeText(getContext(), "Deleted ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            // Toast.makeText(getContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();


                    mode.finish();

                    break;
                default:
                    Toast.makeText(getContext(), "Clicked " + item.getTitle(),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {

        }
    }



}