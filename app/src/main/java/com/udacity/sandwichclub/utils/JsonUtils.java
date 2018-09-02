package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private final static String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {
        JSONObject contact  ;
        Sandwich sandwich = new Sandwich();

        try {
            contact = new JSONObject(json);

            JSONObject name = contact.getJSONObject("name");

            //the mainName item in json
            String mainName = name.getString("mainName");
            sandwich.setMainName(mainName);

            //the alsoKnownAs item in json
            JSONArray alsoKnowNames = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsName = jsonArrayList(alsoKnowNames);
            sandwich.setAlsoKnownAs(alsoKnownAsName);

            //the second item in json
            String placeOfOrigin = contact.getString("placeOfOrigin");
            sandwich.setPlaceOfOrigin(placeOfOrigin);

            //the three item in json
            String description = contact.getString("description");
            sandwich.setDescription(description);

            //the four item in json
            String image = contact.getString("image");
            sandwich.setImage(image);

            //Sandwich is also ingredients as
            JSONArray ingredients = contact.getJSONArray("ingredients");
            List<String> ingredientslis = jsonArrayList(ingredients);
            sandwich.setIngredients(ingredientslis);


            return sandwich;


        }
        catch ( JSONException e){
            Log.e(TAG , "JsonSandwich Error is :" , e);
        }

        return null;
    }


    public static List<String> jsonArrayList(JSONArray alsoKnownNames){

        //This ArrayList is added to put the data inside
        ArrayList<String> jsonList = new ArrayList<>();

        //now check if alsoKnowNames in json is null or not if null show the exception
        if(alsoKnownNames != null) {
            //here in the for loop Get all the elements in alsoKnownNames Based on the length of the Array
            for(int i=0; i < alsoKnownNames.length(); i++) {
                try {
                    jsonList.add(alsoKnownNames.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }            }
        }

        return jsonList;
    }


}
