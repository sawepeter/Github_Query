package com.example.sawepeter.sunshine;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sawepeter.sunshine.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;

    private TextView error_msg;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText)findViewById(R.id.et_search_box);
        mUrlDisplayTextView = (TextView)findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView)findViewById(R.id.tv_github_search_results_json);

        error_msg = (TextView)findViewById(R.id.tv_error_message_display);
        progressBar = (ProgressBar)findViewById(R.id.pb_loading_indicator);
    }

    private void makeGithubSearchQuery(){
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchurl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchurl.toString());

        String githubSearchResults = null;
        try{
            githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubSearchurl);
            mSearchResultsTextView.setText(githubSearchResults);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void showJsonDataView(){

    }

    public void showErrorMessage(){

    }

      public class GithubQueryTask extends AsyncTask<URL, Void, String>{

          @Override
          protected void onPreExecute() {
              super.onPreExecute();
          }

          @Override
          protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
              String githubSearchResults = null;
              try {
                  githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
              } catch(IOException e) {
                  e.printStackTrace();
              }
              return githubSearchResults;
          }

          protected void onPostExecute(String githubSearchResults) {
              if (githubSearchResults != null && !githubSearchResults.equals("")) {
                  showJsonDataView();
                  mSearchResultsTextView.setText(githubSearchResults);
              }
              showErrorMessage();
          }
      }

    public boolean onCreatOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search){
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
