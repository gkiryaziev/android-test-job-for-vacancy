package android.ua.testapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gkiryaziev on 09.11.2015.
 */
public class AsyncTestDataLoader extends AsyncTask<String, Void, String> {

    URL url;
    HttpURLConnection httpURLConnection = null;

    @Override
    protected String doInBackground(String... params) {
        try {
            url = new URL(params[0]);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer stringBuffer = new StringBuffer();

                while ((inputLine = bufferedReader.readLine()) != null) {
                    stringBuffer.append(inputLine);
                }
                bufferedReader.close();

                return stringBuffer.toString();
            }else{
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if(httpURLConnection != null)
                httpURLConnection.disconnect();
        }
    }
}
