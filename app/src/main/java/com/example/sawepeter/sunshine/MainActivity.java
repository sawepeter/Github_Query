package com.example.sawepeter.sunshine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText)findViewById(R.id.et_search_box);
        mUrlDisplayTextView = (TextView)findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView)findViewById(R.id.tv_github_search_results_json);
    }

    private void makeGithubSearchQuery(){

    }

    public boolean onCreatOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search){
            Context context = MainActivity.this;
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
