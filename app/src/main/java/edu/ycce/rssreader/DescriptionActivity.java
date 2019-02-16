package edu.ycce.rssreader;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


public class DescriptionActivity extends AppCompatActivity {

    RegEx regEx;
    public static String desc_txt;
    public static String image1;
    public static String[] description;
    public static String[] link;
    public static String[] pubDate;
    public static String[] img;
    public static final String ID = "id";
    public int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        id = (int)getIntent().getExtras().get(ID);

        regEx = new RegEx();

        TextView pDate = (TextView)findViewById(R.id.pubDate);
        pDate.setText(pubDate[id]);
        desc_txt = regEx.replaceMatches(description[id]);
        if (img[id] == null) {
            image1 = regEx.findImage(description[id]);
        }
        ImageView image = (ImageView) findViewById(R.id.image);
        if (image1.equals("") || image1.equals("not found")) {
            image.getLayoutParams().height = 0;
        } else {
            Glide.with(this).load(image1).into(image);
        }
        TextView desc = (TextView)findViewById(R.id.description);
        if (desc_txt.equals(""))
            desc.setText("No Description Provided...View Full News By Clicking following button.");
        else
            desc.setText(desc_txt);
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

    public void webView(View view) {
        Intent intent = new Intent(this, WebviewActivity.class);
        intent.putExtra(WebviewActivity.EXTRA_URL, link[id]);
        startActivity(intent);
    }
}
