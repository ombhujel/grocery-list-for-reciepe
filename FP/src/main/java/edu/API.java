package edu;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class API {

    public String[][] APII(String x) throws IOException, InterruptedException {
        String id = "ac.............8";
        String key = "5a0................................d7f3237";
        String food = x;
        String food1 = new String(food.replace(" ", "%20"));
        var url = "https://api.edamam.com/api/recipes/v2?type=public&q=" + food1 + "&app_id=" + id + "&app_key=" + key
                + "&ingr=5-5";
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        try {
            JSONObject obj = new JSONObject(response.body());
            JSONArray hits = obj.getJSONArray("hits");

            String[][] recipes = new String[hits.length()][7];

            for (int i = 0; i < hits.length(); i++) {
                JSONObject hit = hits.getJSONObject(i);
                JSONObject recipe = hit.getJSONObject("recipe");

                String label = recipe.getString("label");
                String image = recipe.getString("image");
                JSONArray ingredientLines = recipe.getJSONArray("ingredientLines");

                recipes[i][0] = label;
                recipes[i][6] = image;

                for (int j = 0; j < 5 && j < ingredientLines.length(); j++) {
                    recipes[i][j + 1] = ingredientLines.getString(j);
                }
            }

            return recipes;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // Test your API method here
    }
}
