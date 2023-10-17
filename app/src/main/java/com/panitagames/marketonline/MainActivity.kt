package com.panitagames.marketonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonInventory = findViewById<Button>(R.id.buttonInventory)
        val buttonInterestPlaces = findViewById<Button>(R.id.buttonInterestPlaces)
        val buttonShoppingCart = findViewById<Button>(R.id.buttonShoppingCart)
        val buttonProduct = findViewById<Button>(R.id.buttonProduct)

        //button listeners
        buttonInventory.setOnClickListener {
            val intentAbout = Intent(this, Inventory::class.java)
            startActivity(intentAbout)
        }
        buttonInterestPlaces.setOnClickListener {
            val intentAbout = Intent(this, InterestPlaces::class.java)
            startActivity(intentAbout)
        }
        buttonShoppingCart.setOnClickListener {
            val intentAbout = Intent(this, ShoppingCart::class.java)
            startActivity(intentAbout)
        }
        buttonProduct.setOnClickListener {
            val intentAbout = Intent(this, ProductList::class.java)
            startActivity(intentAbout)
        }
    }
}