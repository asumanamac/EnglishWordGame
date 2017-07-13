package example.seniordesign_project;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreActivity extends AppCompatActivity {

    private String TAG = FourthLevelActivity.class.getSimpleName();

    private static String score_url="http://192.168.2.11:8080/android_login_api/getTotalScore.php";
    ArrayList<HashMap<String, String>> score_list;
    TextView num1,num2,num3,level;
    private ListView lv;
    private ProgressDialog pDialog;
    HashMap<String, String> score_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score_list=new ArrayList();
        lv = (ListView) findViewById(R.id.list);
        new GetHighScore().execute();
    }



    public class GetHighScore extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ScoreActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {


            HttpHandler sh = new HttpHandler();


            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(score_url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    score_list=new ArrayList<>();
                    // Getting JSON Array node
                    JSONArray scores = jsonObj.getJSONArray("result");
                    for (int i = 0; i <scores.length(); i++) {
                        JSONObject c = scores.getJSONObject(i);

                        String name = c.getString("name");
                        String score = c.getString("score");

                         score_map = new HashMap<>();
                        score_map.put("name", name);
                        score_map.put("score", score);


                        score_list.add(score_map);
                    }
                    for(int i=0;i<scores.length();i++) {
                        Log.d("List elements are ", score_list.get(i).toString());
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    ScoreActivity.this, score_list,
                    R.layout.list_item, new String[]{"name", "score"}, new int[]{R.id.name,
                    R.id.score});

            lv.setAdapter(adapter);




            }}
}
