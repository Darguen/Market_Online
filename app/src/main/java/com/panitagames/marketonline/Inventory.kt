package com.panitagames.marketonline

import adapters.ProductAdapter
import adapters.ProductDetails
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import entities.Product

class Inventory : AppCompatActivity() {
    private lateinit var listViewProducts: ListView
    private lateinit var productsOnStock: MutableList<Product>
    private lateinit var adapterItems: ProductAdapter
    private lateinit var adapter : ArrayAdapter<Product>
    private val listOption: Boolean = true
    private val detailOption: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        // Initialize UI elements
        listViewProducts = findViewById(R.id.listViewProducts)

        // Create a sample list of patients (you should replace this with your data source)
        productsOnStock = mutableListOf(
            Product(1, "bebida", "tarro 100g", "Cafe", 0),
            Product(2, "comida", "2 hallullas", "Pan", 0),
            Product(3, "comida", "bolsa 500g", "Arroz", 0),
            // Add more patients as needed
        )

        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productsOnStock)

        listViewProducts.adapter = adapter



    }
}