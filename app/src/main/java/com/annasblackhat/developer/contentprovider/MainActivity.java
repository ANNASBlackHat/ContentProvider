package com.annasblackhat.developer.contentprovider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.annasblackhat.developer.contentprovider.database.WalletContract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
    }

    public void save(View view){
        String ket = ((EditText) findViewById(R.id.ket)).getText().toString();
        String total = ((EditText) findViewById(R.id.total)).getText().toString();


        ContentValues cv = new ContentValues();
        cv.put(WalletContract.TITLE, ket);
        cv.put(WalletContract.TOTAL, total);
        Uri uri = getContentResolver().insert(WalletContract.CONTENT_URI, cv);

        Toast.makeText(this, "Inserted ID : "+ ContentUris.parseId(uri),Toast.LENGTH_SHORT).show();

        loadData();
    }

    private void loadData(){
        //LOAD DATA
        Cursor cursor = getContentResolver().query(WalletContract.CONTENT_URI, null, null, null, null);
        String value = "";
        while(cursor.moveToNext()){
            value += cursor.getString(cursor.getColumnIndex(WalletContract.TITLE))+" - "+cursor.getString(cursor.getColumnIndex(WalletContract.TOTAL))+"\n";
        }
        ((TextView)findViewById(R.id.txt_value)).setText(value);
    }

}
