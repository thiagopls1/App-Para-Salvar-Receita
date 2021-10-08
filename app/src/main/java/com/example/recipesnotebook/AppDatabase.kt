package com.example.recipesnotebook

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
        abstract fun recipeDao(): RecipeDao
}

