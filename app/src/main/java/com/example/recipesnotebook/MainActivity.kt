package com.example.recipesnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipesnotebook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnListRecipes.setOnClickListener {
            startActivity(Intent(this, ListRecipesActivity::class.java))
        }

        binding.btnAddRecipes.setOnClickListener {
            startActivity(Intent(this, AddRecipeActivity::class.java))
        }

        binding.btnEditRecipes.setOnClickListener {
            startActivity(Intent(this, EditRecipeActivity::class.java))
        }

        binding.btnDeleteRecipes.setOnClickListener {
            startActivity(Intent(this, DeleteRecipeActivity::class.java))
        }
    }
}