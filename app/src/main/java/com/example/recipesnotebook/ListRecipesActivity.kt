package com.example.recipesnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipesnotebook.databinding.ActivityListRecipesBinding

class ListRecipesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityListRecipesBinding
    private var adapter: RecipeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).allowMainThreadQueries().build()

        adapter = RecipeAdapter(dataSet = listOf(), onItemClicked = {
            val i = Intent(this, OpenedRecipeActivity::class.java)
            i.putExtra("recipe_id", it)
            startActivity(i)
        } )

        binding.recipesListview.apply {
            adapter = this@ListRecipesActivity.adapter
            layoutManager = LinearLayoutManager(
                this@ListRecipesActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        db.recipeDao().getAllLiveData().observe(this, {
            adapter?.setRecipeList(it)
        })
    }
}