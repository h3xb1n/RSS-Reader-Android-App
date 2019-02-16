package edu.ycce.rssreader;


import android.app.FragmentManager;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddSourceActivity extends AppCompatActivity {

    SQLiteHelper sQLiteHelper;
    FeedSourceModel feedSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_source);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        sQLiteHelper = new SQLiteHelper(this);
    }

    public void addSource(View view){
        EditText name_input = (EditText) findViewById(R.id.name_input);
        EditText url_input  = (EditText) findViewById(R.id.feed_url);
        Spinner categories = (Spinner)findViewById(R.id.categories);
        String name = name_input.getText().toString();
        String url = url_input.getText().toString();
        String category = String.valueOf(categories.getSelectedItem());
        if ( !(name.equals("")) && !(url.equals(""))) {
            feedSourceModel = new FeedSourceModel();
            feedSourceModel.setUrl(url);
            feedSourceModel.setCategory(category);
            feedSourceModel.setName(name);
            try {
                sQLiteHelper.insertRecord(feedSourceModel);
                Toast toast = Toast.makeText(this, "Source Successfully Added", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } catch (SQLiteException e) {
                Log.v("exception", e.getMessage());
                Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        else {
            Toast toast = Toast.makeText(this, "Please enter name and url", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}

