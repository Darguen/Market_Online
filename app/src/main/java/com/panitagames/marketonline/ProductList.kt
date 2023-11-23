package com.panitagames.marketonline

import adapters.ProductAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.room.Room
import com.panitagames.marketonline.Database.AppDatabase
import entities.Product

class ProductList: AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var listViewProducts: ListView
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    private lateinit var products: MutableList<Product>
    private lateinit var adapterItems: ProductAdapter
    private lateinit var adapter : ArrayAdapter<Product>
    companion object {
        const val REQUEST_REGISTER = 1 // You can use any unique code
        const val REQUEST_EDITER = 2 // You can use any unique code
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        setSupportActionBar(findViewById(R.id.menu))

        // Initialize UI elements
        listViewProducts = findViewById(R.id.listViewProducts)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()


        // Create a sample list of patients (you should replace this with your data source)
        products = mutableListOf(
            Product(1, "bebida", "botella 597cc", "Bebida Coca-cola", 1200),
            Product(2, "comida", "paquete laminado 250g", "Queso", 3000),
            Product(3, "comida", "caja chocapic 500g", "Cereal", 4000),
            Product(4, "comida", "paquete 200 g", "Salame", 5500),
            Product(5, "comida", "trozo 1kg", "Carne vacuno", 10000),
            // Add more patients as needed
        )

        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, products)
        db.productDao().insertAll(products)
        //listViewProducts.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.go_inventory -> {
                db.productDao().getAll()
                return true
            }
            /*R.id.go_product_list -> {
                val productsList = Intent(this, ProductList::class.java)
                startActivity(productsList)
                return true
            }*/
            else -> return super.onOptionsItemSelected(item)
        }
    }
}