package com.panitagames.marketonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

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
            R.id.go_movements -> {
                val movementList = Intent(this, MovementList::class.java)
                startActivity(movementList)
                return true
            }
            R.id.go_product_list -> {
                val productsList = Intent(this, ProductList::class.java)
                startActivity(productsList)
                return true
            }
            R.id.go_api_test ->{
                val apiRestActivity = Intent(this, APIRestActivity::class.java)
                startActivity(apiRestActivity)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}