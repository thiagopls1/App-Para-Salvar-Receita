package com.example.recipesnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.recipesnotebook.databinding.ActivityOpenedRecipeBinding

class OpenedRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpenedRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenedRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).allowMainThreadQueries().build()

        val selectedRecipeId = intent.getLongExtra("recipe_id", 1)

        binding.tvRecipeTitle.text =
                db.recipeDao().getRecipeTitleFromId(selectedRecipeId)
        binding.tvRecipeIngredients.text =
                db.recipeDao().getRecipeIngredientsFromId(selectedRecipeId)
        binding.tvRecipeCookMethod.text =
                db.recipeDao().getRecipeCookMethodFromId(selectedRecipeId)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, ListRecipesActivity::class.java))
        }
    }
}