package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView alsoknownTv , ingredientsTv , originPlaceTv ,descriptionTv;
    String[] sandwiches ;
    Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        alsoknownTv = (TextView)findViewById(R.id.also_known_as_Show);
        ingredientsTv = (TextView)findViewById(R.id.ingredients_Show);
        originPlaceTv = (TextView)findViewById(R.id.place_of_origin_Show);
        descriptionTv = (TextView)findViewById(R.id.description_Show);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
         sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);
        setTitle(sandwich.getMainName());


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        if(alsoknownTv != null ){
            for (int i=0 ; i<sandwich.getAlsoKnownAs().size(); i++) {
                String nameOfItem = sandwich.getAlsoKnownAs().get(i);
                if( i != sandwich.getAlsoKnownAs().size() - 1){
                    alsoknownTv.append(nameOfItem + "  ");
                }

                else{
                    alsoknownTv.append(nameOfItem + "  ");
                }
            }
        }

        originPlaceTv.setText(sandwich.getPlaceOfOrigin());

        if(alsoknownTv != null ){
            for (int i=0 ; i<sandwich.getIngredients().size(); i++) {
                String ingredients = sandwich.getIngredients().get(i);
                if( i != sandwich.getIngredients().size() - 1){
                    ingredientsTv.append(ingredients + "  ");
                }
                else{
                    ingredientsTv.append(ingredients + "  ");
                }
            }
        }

        descriptionTv.setText(sandwich.getDescription());


    }
}
