package com.example.lina.hhk_girls;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    static MyDBHandler dbHandler;

    private MaterialSearchView searchView;
    ListView lv=Ebtda2y.lv;
    ArrayAdapter adapter1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Ebtda2y"));
        tabLayout.addTab(tabLayout.newTab().setText("E3dady"));
        tabLayout.addTab(tabLayout.newTab().setText("Thanawy"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        dbHandler = new MyDBHandler(this, null, null,3);



        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Tabs adapter = new Tabs
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
       // searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*Snackbar.make(findViewById(R.id.main_layout), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();*/
                String  s="";
                if(tabLayout.getSelectedTabPosition()==0){
//lv.clearChoices();
                s=dbHandler.getItemName(query, "name", "girlsEbtda2y");
                    String[]ss=new String[s.length()];
                    ss=s.split("\n");
                    final String []dbNames=dbHandler.getItemName(query, "name", "girlsEbtda2y").split("\n");
                    final String []dbYear=dbHandler.getItemName(query, "year", "girlsEbtda2y").split("\n");
                    final String []dbM1=dbHandler.getItemName(query, "mobile1", "girlsEbtda2y").split("\n");
                    final String []dbM2=dbHandler.getItemName(query, "mobile2", "girlsEbtda2y").split("\n");
                    final String []dbH1=dbHandler.getItemName(query, "home1", "girlsEbtda2y").split("\n");
                    final String []dbH2=dbHandler.getItemName(query, "home2", "girlsEbtda2y").split("\n");
                    final String []ids=dbHandler.getItemName(query, "_id", "girlsEbtda2y").split("\n");
                    adapter1 = new ArrayAdapter(getApplicationContext(),
                            R.layout.listview_item, R.id.textv, dbNames);

                    lv = (ListView) findViewById(R.id.list1);
                    // Assign adapter to ListView
                    lv.setAdapter(adapter1);
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
                            Intent intent = new Intent(MainActivity.this, Details.class);
                            Bundle b = new Bundle();
                            b.putInt("position", Integer.parseInt(ids[j])-1); //Your id
                            b.putString("names", dbNames[j]);
                            b.putString("mobile1", dbM1[j]);
                            b.putString("mobile2", dbM2[j]);
                            b.putString("home1", dbH1[j]);
                            b.putString("home2", dbH2[j]);
                            b.putString("year", dbYear[j]);
                            b.putString("table", "girlsEbtda2y");
                            intent.putExtras(b);

                            startActivity(intent);
                        }
                    });
                }
                else if(tabLayout.getSelectedTabPosition()==1){

                    s=dbHandler.databaseToString("name", "girlsE3dady");
                    String[]ss=new String[s.length()];
                    ss=s.split("\n");
                    adapter1 = new ArrayAdapter(getApplicationContext(),
                            R.layout.listview_item, R.id.textv, ss);
                    lv = (ListView) findViewById(R.id.list2);

                    // Assign adapter to ListView
                    lv.setAdapter(adapter1);
                }
                else if(tabLayout.getSelectedTabPosition()==2){
                    s=dbHandler.databaseToString("name", "girlsThanawy");
                String[]ss=new String[s.length()];
                ss=s.split("\n");
                adapter1 = new ArrayAdapter(getApplicationContext(),
                        R.layout.listview_item, R.id.textv, ss);
                lv = (ListView) findViewById(R.id.list3);

                // Assign adapter to ListView
                lv.setAdapter(adapter1);}



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_select_menu, menu);
        return true;
    }
*/
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}