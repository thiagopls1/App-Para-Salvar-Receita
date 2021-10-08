package com.example.recipesnotebook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val recipeId: Long = 0,
    @ColumnInfo(name = "recipe_title")val recipeTitle: String?,
    @ColumnInfo(name = "recipe_ingredients")val recipeIngredients: String?,
    @ColumnInfo(name = "recipe_cook_method")val recipeCookMethod: String?
)

