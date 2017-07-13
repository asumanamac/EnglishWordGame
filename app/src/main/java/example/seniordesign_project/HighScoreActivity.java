package example.seniordesign_project;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HighScoreActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    String url= "http://192.168.2.11:8080/android_login_api/highscore.php";;
    ArrayList<HashMap<String, String>> score_list;
    public static String score_text;
    public static boolean play_screen =false ,second_level =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public class GetScore extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

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
                        String level = c.getString("level");
                        HashMap<String, String> score_map = new HashMap<>();
                        score_map.put("name", name);
                        score_map.put("score", score);
                        score_map.put("level", level);

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


            for (int i = 0; i < score_list.size(); i++) {
                Log.d("ELEMENTS ARE : ",score_list.get(i).toString());
                if (LevelScreen.isLevel1 == true) {
                    Log.d("IS IT TRUE?", "TRUE1");
                    if (MainActivity.name.equals(score_list.get(i).get("name")) &&score_list.get(i).get("level").equals("1")) {
                        Log.d("IS IT TRUE?", "TRUE2");
                        if (Integer.valueOf(PlayScreen.scoreText.getText().toString()) > Integer.valueOf(score_list.get(i).get("score").toString())
                                ) {
                            Log.d("IS IT TRUE?", "TRUE");
                            PlayScreen.high_score_result.setText(String.valueOf(PlayScreen.scoreText.getText()));
                        } else {
                            PlayScreen.high_score_result.setText(String.valueOf(score_list.get(i).get("score")));
                           // Log.d("4IS IT TRUE?", "TRUE3");

                        }

                    }




                }


                if (LevelScreen.isLevel2 == true) {
                    if (MainActivity.name.equals(score_list.get(i).get("name"))&& score_list.get(i).get("level").equals("2")) {
                        Log.d("1IS IT TRUE?", "TRUE");
                        if (Integer.valueOf(SecondLevelActivity.scoreText.getText().toString()) > Integer.valueOf(score_list.get(i).get("score").toString())
                                 ) {

                            Log.d("2IS IT TRUE?", "SECOND LEVEL FIRST CONDITION");
                            SecondLevelActivity.high_score_result.setText(SecondLevelActivity.scoreText.getText().toString());
                        } else {
                           // Log.d("Arraylist elemntsssss" ,score_list.get(i).get("score").toString());
                            SecondLevelActivity.high_score_result.setText(String.valueOf(score_list.get(i).get("score")));
                        }

                    }




                }

                if (LevelScreen.isLevel3== true) {
                    if (MainActivity.name.equals(score_list.get(i).get("name"))&& LevelScreen.level.equals(score_list.get(i).get("level"))) {
                        if (Integer.valueOf(ThirdLevelActivity.scoreText.getText().toString()) > Integer.valueOf(score_list.get(i).get("score").toString())
                               ) {
                            ThirdLevelActivity.high_score_result.setText("" + ThirdLevelActivity.scoreText.getText().toString());
                        } else {
                            ThirdLevelActivity.high_score_result.setText("" + score_list.get(i).get("score").toString());
                        }
                    }




                }

                if (LevelScreen.isLevel4== true) {
                    if (MainActivity.name.equals(score_list.get(i).get("name"))&& LevelScreen.level.equals(score_list.get(i).get("level"))) {
                        if (Integer.valueOf(FourthLevelActivity.scoreText.getText().toString()) > Integer.valueOf(score_list.get(i).get("score").toString())
                               ) {
                            FourthLevelActivity.high_score_result.setText("" + FourthLevelActivity.scoreText.getText().toString());
                        } else {
                            FourthLevelActivity.high_score_result.setText("" + score_list.get(i).get("score").toString());
                        }
                    }



                }

            }


        }
    }}