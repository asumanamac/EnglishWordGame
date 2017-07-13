package example.seniordesign_project;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResetPassword extends AppCompatActivity {




    Button send_button;
    TextView back_login;
    Button process_button;
    EditText code_input,password_input,email_input;
    public TextView count_timer;
    private CountDownTimer countDownTimer;
    String sifre,code,sonuc,email;
    String hata_mesaji="";
    String URL_POST= "http://192.168.2.10:8080/android_login_api/resetpass.php";
    String URL_POST2= "http://192.168.2.10:8080/android_login_api/resetpass2.php";
    Boolean hata = false;
    PostClass post = new PostClass();
    JSONObject cevap=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        send_button =(Button) findViewById(R.id.send_button);
        email_input=(EditText) findViewById(R.id.email_input);
        process_button=(Button) findViewById(R.id.reset_button);
        password_input=(EditText) findViewById(R.id.password_input);
        count_timer=(TextView) findViewById(R.id.timer);


        password_input.setVisibility(View.GONE);
        count_timer.setVisibility(View.GONE);
        process_button.setVisibility(View.GONE);


        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email=email_input.getText().toString();

                if(email.matches("")){
                    hata_mesaji += "Email cannot empty\n";
                    hata = true;
                }
                if(!Functions.isEmailValid(email)){//Mail format kontrol
                    hata_mesaji += "Incorrect email format\n";
                    hata=true;
                }



                if(hata){
                    AlertDialog alertDialog = new AlertDialog.Builder(ResetPassword.this).create();
                    alertDialog.setTitle("Hata");
                    alertDialog.setMessage(hata_mesaji);
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(RESULT_OK,"Tamam", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            email_input.setText("");
                            hata_mesaji = "";
                            hata = false;
                        }
                    });
                    alertDialog.show();
                }else{
                    new GirisKontrol().execute();

                }


            }
        });



        process_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sifre=password_input.getText().toString();
                email=email_input.getText().toString();
                ;


                if(sifre.equals("")){
                    hata_mesaji += "Password cannot empty\n";
                    hata = true;
                }


                if(hata){
                    AlertDialog alertDialog = new AlertDialog.Builder(ResetPassword.this).create();
                    alertDialog.setTitle("Hata");
                    alertDialog.setMessage(hata_mesaji);
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(RESULT_OK,"Tamam", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            password_input.setText("");
                            hata_mesaji = "";
                            hata = false;
                        }
                    });
                    alertDialog.show();
                }else{
                    new GirisKontrol2().execute();

                }



            }
        });






    }

    private void startCountdownTimer(){
        countDownTimer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                count_timer.setText("Time remaining : " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Time Out ! Request again to reset password.",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(ResetPassword.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }.start();
    }

    class GirisKontrol extends AsyncTask<Void, Void, Void> {
        private String sonucmesaji;
        ProgressDialog pDialog;

        protected void onPreExecute() {
            //  progress dialog
            pDialog = new ProgressDialog(ResetPassword.this);
            pDialog.setMessage("Sending...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Void doInBackground(Void... unused) {
            // Building Parameters

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));

            String json = post.httpPost(URL_POST,"POST",params,20000);

            Log.d("Gelen Json",json);//Gelen veriyi logluyoruz.Log Catten kontrol edebiliriz
            try {

                cevap = new JSONObject(json);
                sonucmesaji = cevap.getString("sonucmesaji");

                if (cevap.getString("sonuc") != null) {
                    sonuc = cevap.getString("sonuc");
                    if(Integer.parseInt(sonuc) == 1){ //Eï¿½er giriï¿½ baï¿½arï¿½lï¿½ ise





                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        // Sonuï¿½ baï¿½arï¿½lï¿½ ise bu kod ï¿½alï¿½ï¿½mï¿½cak ï¿½ï¿½nkï¿½ Main activitye yï¿½nlenmiï¿½ durumda
        protected void onPostExecute(Void unused) {
            // closing progress dialog
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    if(Integer.parseInt(sonuc) == 0){// Sonuï¿½ baï¿½arï¿½lï¿½ deï¿½il ise
                        AlertDialog alertDialog = new AlertDialog.Builder(ResetPassword.this).create();
                        alertDialog.setTitle("Hata");
                        alertDialog.setMessage(sonucmesaji);//Sonuc mesajï¿½yla bilgilendiriyoruz.
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(RESULT_OK,"Tamam", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                email_input.setText("");
                            }
                        });
                        alertDialog.show();
                    }

                    else
                    {



                        email_input.setVisibility(View.VISIBLE);
                        send_button.setVisibility(View.GONE);

                        password_input.setVisibility(View.VISIBLE);
                        process_button.setVisibility(View.VISIBLE);
                        count_timer.setVisibility(View.VISIBLE);
                        startCountdownTimer();
                    }
                }




            });


        }
    }

    class GirisKontrol2 extends AsyncTask<Void, Void, Void> {
        private String sonucmesaji;
        ProgressDialog pDialog;

        protected void onPreExecute() {
            //  progress dialog
            pDialog = new ProgressDialog(ResetPassword.this);
            pDialog.setMessage("Sending...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Void doInBackground(Void... unused) {
            // Building Parameters

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));


            String json = post.httpPost(URL_POST2,"POST",params,20000);

            Log.d("Gelen Json",""+json);//Gelen veriyi logluyoruz.Log Catten kontrol edebiliriz
            try {

                cevap = new JSONObject(json);
                sonucmesaji = cevap.getString("sonucmesaji");

                if (cevap.getString("sonuc") != null) {
                    sonuc = cevap.getString("sonuc");
                    if(Integer.parseInt(sonuc) == 1){ //Eï¿½er giriï¿½ baï¿½arï¿½lï¿½ ise

                        Intent intent=new Intent(ResetPassword.this,LoginActivity.class);
                        startActivity(intent);
                        finish();





                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        // Sonuï¿½ baï¿½arï¿½lï¿½ ise bu kod ï¿½alï¿½ï¿½mï¿½cak ï¿½ï¿½nkï¿½ Main activitye yï¿½nlenmiï¿½ durumda
        protected void onPostExecute(Void unused) {
            // closing progress dialog
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    if(Integer.parseInt(sonuc) == 0){// Sonuï¿½ baï¿½arï¿½lï¿½ deï¿½il ise
                        AlertDialog alertDialog = new AlertDialog.Builder(ResetPassword.this).create();
                        alertDialog.setTitle("Hata");
                        alertDialog.setMessage(sonucmesaji);//Sonuc mesajï¿½yla bilgilendiriyoruz.
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(RESULT_OK,"Tamam", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                password_input.setText("");
                            }
                        });
                        alertDialog.show();
                    }


                }




            });


        }
    }
}
