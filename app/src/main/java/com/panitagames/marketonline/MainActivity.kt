package com.panitagames.marketonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.go_inventory -> {
                val inventory = Intent(this, Inventory::class.java)
                startActivity(inventory)
                return true
            }
            R.id.go_product_list -> {
                val productsList = Intent(this, ProductList::class.java)
                startActivity(productsList)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}