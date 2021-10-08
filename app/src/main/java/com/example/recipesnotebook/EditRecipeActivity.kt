package com.example.recipesnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipesnotebook.databinding.ActivityEditRecipeBinding

class EditRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecipeBinding
    private var adapter: RecipeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).allowMainThreadQueries().build()

        adapter = RecipeAdapter(dataSet = listOf(), onItemClicked = {
            val i = Intent(this, EditSelectedRecipeActivity::class.java)
            i.putExtra("recipe_id", it)
            startActivity(i)
        } )

        binding.recipesListview.apply {
            adapter = this@EditRecipeActivity.adapter
            layoutManager = LinearLayoutManager(
                this@EditRecipeActivity,
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