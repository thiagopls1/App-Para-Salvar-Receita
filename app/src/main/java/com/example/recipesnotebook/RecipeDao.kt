package com.example.recipesnotebook

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipe")
    fun getAllLiveData(): LiveData<List<Recipe>>

    @Query("SELECT recipe_title FROM recipe WHERE recipeId IN (:recipeId)")
    fun getRecipeTitleFromId(recipeId: Long): String

    @Query("SELECT recipe_ingredients FROM recipe WHERE recipeId IN (:recipeId)")
    fun getRecipeIngredientsFromId(recipeId: Long): String

    @Query("SELECT recipe_cook_method FROM recipe WHERE recipeId IN (:recipeId)")
    fun getRecipeCookMethodFromId(recipeId: Long): String

    @Query("UPDATE Recipe SET recipe_title = (:recipeTitle) WHERE recipeId IN (:recipeId)")
    fun editRecipeTitle(recipeId: Long, recipeTitle: String): Int

    @Query("UPDATE Recipe SET recipe_ingredients = (:recipeIngredients) WHERE recipeId IN (:recipeId)")
    fun editRecipeIngredients(recipeId: Long, recipeIngredients: String): Int

    @Query("UPDATE Recipe SET recipe_cook_method = (:recipeCookMethod) WHERE recipeId IN (:recipeId)")
    fun editRecipeCookMethod(recipeId: Long, recipeCookMethod: String): Int

    @Insert
    fun insertAll(vararg recipe: Recipe)

    @Query("DELETE FROM recipe WHERE recipeId IN (:recipeId) ")
    fun deleteRecipe(recipeId: Long)

    @Query("DELETE FROM recipe ")
    fun deleteAllRecipes()
}

