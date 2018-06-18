package com.example.felix.ausgaben2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private List<Ausgaben> ausgabenList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AusgabenAdapter mAdapter;
    private String sum;
    static final int REQUEST_ID = 1;
    public static final String TEXTFILE = "enteredData.txt";
    private AusgabenDataSource dataSource;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        dataSource = new AusgabenDataSource(this);
        mAdapter = new AusgabenAdapter(dataSource);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //preparedAusgabenData();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet");
        dataSource.open();


        Ausgaben ausgaben = dataSource.createAusgaben("Testeinkauf", 20, new Date());




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

*/
                Intent i = new Intent(MainActivity.this, EnterDataActivity.class);
                startActivityForResult(i, REQUEST_ID);
            }
        });


        TextView textView = (TextView) findViewById(R.id.total_amount);
        textView.setText(String.valueOf(sum(ausgabenList)));
        Log.d(LOG_TAG,"Die Datenquelle wird geschlossen");
        dataSource.close();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ID){
            if(resultCode == RESULT_OK){
                String retEnterDataString = data.getStringExtra("string");
                String retEnterDataAmount = data.getStringExtra("amount");
                String retEnterDataDate = data.getStringExtra("buyDate");
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
                Date date = null;
                try {
                    date = format.parse(retEnterDataDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
                double val = Double.parseDouble(retEnterDataAmount);

                //dataSource.createAusgaben(retEnterDataString,val,date);
                Ausgaben ausgabenRet = new Ausgaben(retEnterDataString,val,new Date(date.getDate(),date.getMonth()+1,date.getYear()+2000));

                ausgabenList.add(ausgabenRet);
                TextView textView = (TextView) findViewById(R.id.total_amount);
                textView.setText(String.valueOf(sum(ausgabenList)));
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void preparedAusgabenData() {
        Ausgaben ausgaben = new Ausgaben("Nettoeinkauf", 10, new Date());
        ausgabenList.add(ausgaben);

        Ausgaben ausgaben1 = new Ausgaben("Edeka", 10.90, new Date());
        ausgabenList.add(ausgaben1);

        Ausgaben ausgaben2 = new Ausgaben("Edeka", 10.90, new Date());
        ausgabenList.add(ausgaben2);

        Ausgaben ausgaben3 = new Ausgaben("Edeka", 10.90, new Date());
        ausgabenList.add(ausgaben3);

        Ausgaben ausgaben4 = new Ausgaben("Edeka", 10.90, new Date());
        ausgabenList.add(ausgaben4);

        Ausgaben ausgaben5 = new Ausgaben("Edeka", 10.90, new Date());
        ausgabenList.add(ausgaben5);

        Ausgaben ausgaben6 = new Ausgaben("Edeka", 10.90, new Date());
        ausgabenList.add(ausgaben6);

        mAdapter.notifyDataSetChanged();
    }

    private double sum(List<Ausgaben> ausgabenList) {
        double summe = 0;
        for (int i = 0; i < ausgabenList.size(); i++) {
            summe = summe + ausgabenList.get(i).getPrice();
        }
        return summe;
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




    


}
