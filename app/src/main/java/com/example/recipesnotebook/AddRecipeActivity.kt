package com.example.recipesnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.recipesnotebook.databinding.ActivityAddRecipeBinding
import java.lang.Exception

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).allowMainThreadQueries().build()

        binding.btnSaveRecipe.setOnClickListener {

            val recipeTitle = binding.etRecipeTitle.text.toString()
            val recipeIngredients = binding.etRecipeIngredients.text.toString()
            val recipeCookMethod = binding.etRecipeCookMethod.text.toString()

            if (recipeTitle == "" || recipeIngredients == "" || recipeCookMethod == "" ){
                showMessage("Preencha os campos corretamente!")
            }

            try{
                db.recipeDao().insertAll(Recipe(
                    recipeId = 0,
                    recipeTitle = recipeTitle,
                    recipeCookMethod = recipeCookMethod,
                    recipeIngredients = recipeIngredients
                    )
                )
                showMessage("Receita salva com sucesso")
            } catch (e: Exception){
                showMessage(e.toString())
            }

            binding.etRecipeTitle.setText("")
            binding.etRecipeIngredients.setText("")
            binding.etRecipeCookMethod.setText("")
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun showMessage (str: String){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Aviso")
        dialog.setMessage(str)
        dialog.setNeutralButton("Ok", null)
        dialog.show()
    }

}