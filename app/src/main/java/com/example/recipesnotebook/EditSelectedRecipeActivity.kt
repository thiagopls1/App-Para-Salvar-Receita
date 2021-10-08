package com.example.recipesnotebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.recipesnotebook.databinding.ActivityEditSelectedRecipeBinding

class EditSelectedRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditSelectedRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSelectedRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).allowMainThreadQueries().build()

        val selectedRecipeId = intent.getLongExtra("recipe_id", 1)

        binding.etRecipeTitle.setText(db.recipeDao().getRecipeTitleFromId(selectedRecipeId))
        binding.etRecipeIngredients.setText(db.recipeDao().getRecipeIngredientsFromId(selectedRecipeId))
        binding.etRecipeCookMethod.setText(db.recipeDao().getRecipeCookMethodFromId(selectedRecipeId))

        binding.btnSaveRecipe.setOnClickListener{
            if( binding.etRecipeTitle.text.equals("") || binding.etRecipeIngredients.equals("") ||
                binding.etRecipeCookMethod.equals("")){
                showMessage("Todos os campos devem ser preenchidos!")
            }
            val etRecipeTitle = binding.etRecipeTitle.text.toString()
            db.recipeDao().
            editRecipeTitle(recipeId = selectedRecipeId, recipeTitle = etRecipeTitle)

            val etRecipeIngredients = binding.etRecipeIngredients.text.toString()
            db.recipeDao().
            editRecipeIngredients(recipeId = selectedRecipeId, recipeIngredients = etRecipeIngredients)

            val etRecipeCookMethod = binding.etRecipeCookMethod.text.toString()
            db.recipeDao().
            editRecipeCookMethod(recipeId = selectedRecipeId, recipeCookMethod = etRecipeCookMethod)
            showMessage("Dados alterados com sucesso")
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, EditRecipeActivity::class.java))
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