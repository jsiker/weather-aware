package com.techstars.app;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by m805958 on 7/21/15.
 *
 * Class returns api URL with API key
 *
 */
public class WeatherUndergroundResponse {

    private static InputStream in;
    private static HttpClient client = new DefaultHttpClient();

    public WeatherUndergroundResponse() {}

    private String getAPIKey() throws IOException{
        Properties properties = new Properties();
        in = getClass().getResourceAsStream("/secret.properties");
        properties.load(in);
        final String API_KEY = properties.getProperty("weatherUndergroundAPIKey");
        in.close();
        return API_KEY;
    }


    public JSONObject getConditions(String country, String city) throws IOException, URISyntaxException {
        country = country.replace(" ", "_");
        city = city.replace(" ", "_");
        final URI url = new URI("http://api.wunderground.com/api/" + getAPIKey() + "/geolookup/conditions/forecast/q/"+country+"/"+city+".json");
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        String result;
        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream inputStream = entity.getContent();
                result = convertStreamToString(inputStream);
                JSONObject object = new JSONObject(result);
                inputStream.close();
                return object;
            }
        } catch (ClientProtocolException c) {
            c.printStackTrace();
        }
        return null;
    }

//    public HttpGet getJSON() {
    // TODO: change city, country to getters of user city/country in a UserLocation class
//        return new HttpGet(getConditions(UserLocation.getUserCountry(), UserLocation.getUserCity()))
//    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
