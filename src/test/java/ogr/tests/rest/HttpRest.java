package ogr.tests.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class HttpRest {

    @Test
    public void testEmojisApiHttpClient() throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api.github.com/emojis");

        HttpResponse response = client.execute(request);
        String jsonResponse = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> responseMap = gson.fromJson(jsonResponse, mapType);
        assertTrue(responseMap.containsKey("articulated_lorry"), "Response should contain 'articulated_lorry' key");

    }

}
