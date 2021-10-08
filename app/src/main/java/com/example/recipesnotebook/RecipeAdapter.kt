package com.example.recipesnotebook

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private var dataSet: List<Recipe>, private val onItemClicked: (Long) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val textViewTitle: TextView
            val textViewIngredients: TextView

            init {
                textViewTitle = view.findViewById(R.id.recipe)
                textViewIngredients = view.findViewById(R.id.description)
            }

            fun bind(recipe: Recipe, onItemClicked: (Long) -> Unit){
                textViewTitle.text = recipe.recipeTitle
                textViewIngredients.text = recipe.recipeIngredients
                view.setOnClickListener {
                    onItemClicked(recipe.recipeId)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_recipes_item, parent, false)

            return RecipeViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is RecipeViewHolder) {
                dataSet.getOrNull(position)?.let {
                    holder.bind(it, onItemClicked)
                }
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        fun setRecipeList(recipeList: List<Recipe>) {
            this.dataSet = recipeList
            notifyDataSetChanged()
        }

        override fun getItemCount() = dataSet.size

    }