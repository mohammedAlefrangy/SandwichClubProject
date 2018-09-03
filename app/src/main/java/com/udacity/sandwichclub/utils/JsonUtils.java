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
            final String KEY_MAIN_NAME = name.optString("mainName");
            sandwich.setMainName(KEY_MAIN_NAME);

            //the alsoKnownAs item in json
            JSONArray KEY_ALSO_KNOW_AS  = name.getJSONArray("alsoKnownAs");
            List<String> array_alsoKnownAsName = jsonArrayList(KEY_ALSO_KNOW_AS);
            sandwich.setAlsoKnownAs(array_alsoKnownAsName);

            //the second item in json
            String KEY_PLACE_OF_ORIGIN = contact.optString("placeOfOrigin");
            sandwich.setPlaceOfOrigin(KEY_PLACE_OF_ORIGIN);

            //the three item in json
            String KEY_DESCRIPTION  = contact.optString("description");
            sandwich.setDescription(KEY_DESCRIPTION);

            //the four item in json
            String KEY_IMAGE = contact.optString("image");
            sandwich.setImage(KEY_IMAGE);

            //Sandwich is also ingredients as
            JSONArray KEY_INGREDIENTS = contact.getJSONArray("ingredients");
            List<String> array_ingredientslis = jsonArrayList(KEY_INGREDIENTS);
            sandwich.setIngredients(array_ingredientslis);


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
