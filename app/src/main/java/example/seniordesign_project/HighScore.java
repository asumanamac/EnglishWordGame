package example.seniordesign_project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by - on 3.2.2017.
 */

public class HighScore extends AsyncTask <Void,Void,Void>{
    String url= "http://192.168.2.11:8080/android_login_api/highscore.php";
    public static HashMap<String, String> score_map = new HashMap<>();
    ArrayList<HashMap<String, String>> score_list=new ArrayList<>();


    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);


        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("result");
                int resultSize = contacts.length();

                JSONObject c1 = contacts.getJSONObject(1);

                String name=c1.getString("name");
                String score=c1.getString("score");

                score_map.put("name",name);
                score_map.put("score",score);

                score_list.add(score_map);

                return null;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        for(int i=0;i<score_list.size();i++) {
            if (MainActivity.name.equals(score_map.get("name")))
                PlayScreen.high_score_result.setText(""+score_list.get(i).get("name"));

        }
    }

}