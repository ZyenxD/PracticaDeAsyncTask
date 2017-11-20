package com.personal.neycasilla.practicadeasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView inProcess;
    TextView veredict;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.edit_text);
        inProcess = (TextView) findViewById(R.id.procesando);
        veredict = (TextView)findViewById(R.id.primo_o_no);
        button = (Button)findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask<String,Integer,Integer> asyncTask = new AsyncTask<String, Integer,Integer>() {
                    @Override
                    protected Integer doInBackground(String... strings) {
                        int num = 0;
                        Integer integer = Integer.parseInt(strings[0]);
                        for(int i=0;i<=integer;i++){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            publishProgress(i);
                            num = i;
                        }
                        return num;
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        inProcess.setText("Procesando...");
                    }

                    @Override
                    protected void onPostExecute(Integer aLong) {
                        if(isPrime(aLong)){
                            veredict.setText(aLong+" Es primo");
                        }else{
                            veredict.setText(aLong+" No es primo");
                        }
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                        String s;
                        int aLong = values[0];
                        if(isPrime(aLong)){
                            s = "Es primo";
                        }else {
                            s = "No es primo";
                        }
                        inProcess.setText(" ");
                        inProcess.setText(inProcess.getText()+" "+values[0]+ " "+ s);


                    }
                };
                asyncTask.execute(editText.getText().toString());
            }
        });

    }

    boolean isPrime(int n){
        if(n%2 == 0) return false;
        for (int i= 3;i*i<=n;i+=2){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }
}
