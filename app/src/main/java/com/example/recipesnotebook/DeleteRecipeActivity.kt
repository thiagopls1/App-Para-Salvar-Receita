package com.example.recipesnotebook

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipesnotebook.databinding.ActivityDeleteRecipeBinding

class DeleteRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteRecipeBinding
    private var adapter: RecipeAdapter? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).allowMainThreadQueries().build()

        adapter = RecipeAdapter(dataSet = listOf(), onItemClicked = {
            db.recipeDao().deleteRecipe(recipeId = it)
            showMessage("Receita excluída com sucesso")
        } )

        binding.recipesListview.apply {
            adapter = this@DeleteRecipeActivity.adapter
            layoutManager = LinearLayoutManager(
                this@DeleteRecipeActivity,
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

    private fun showMessage (str: String){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Aviso")
        dialog.setMessage(str)
        dialog.setNeutralButton("Ok", null)
        dialog.show()
    }

}