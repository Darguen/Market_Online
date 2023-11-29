package com.panitagames.marketonline

import com.panitagames.marketonline.adapters.CreateProductDialog
import com.panitagames.marketonline.adapters.EditProductDialog
import com.panitagames.marketonline.adapters.SortProductDialog
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.panitagames.marketonline.Database.AppDatabase
import com.panitagames.marketonline.adapters.DetailAdapter
import com.panitagames.marketonline.entities.Product
import com.panitagames.marketonline.entities.ProductWithMovement

class ProductList: AppCompatActivity(), SortProductDialog.ItemDialogListener {

    private lateinit var db: AppDatabase
    private lateinit var listViewProducts: ListView
    private lateinit var products: MutableList<Product>
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var adapterListProducts: ArrayAdapter<Product>
    private lateinit var findProduct: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Initialize UI elements
        listViewProducts = findViewById(R.id.listViewProducts)
        findProduct = findViewById(R.id.find_product)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().fallbackToDestructiveMigration()
            .build()


        // Create a sample list of patients (you should replace this with your data source)
        products = mutableListOf(
            Product(1, "bebida", "botella 597cc", "Coca-cola", 1200),
            Product(2, "comida", "paquete laminado 250g", "Queso", 3000),
            Product(4, "comida", "paquete 200 g", "Salame", 5500),
            Product(3, "comida", "caja chocapic 500g", "Cereal", 4000),
            Product(5, "comida", "trozo 1kg", "Carne vacuno", 10000),
            // Add more patients as needed
        )
        //val productsList = products.toList()

        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map{ it.name })
        val list : List<Product> = db.productDao().getAll()
        products = list.toMutableList()
        listViewProducts.adapter = adapter


        list.size



        val fab : FloatingActionButton = findViewById(R.id.idFabProduct)
        fab.setOnClickListener{
            val dialog = CreateProductDialog(this,this)
            dialog.show()
        }
        val dialog = SortProductDialog()
        dialog.setItemDialogListener(this)  // Initialize the listener here
        //dialog.show(supportFragmentManager, "ProductDialog")
        registerForContextMenu(listViewProducts)



        refreshFromDatabase()







    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.product_menu, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.product_menu, menu)
        return true
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.delete_product -> {
                // Handle the "Delete" option
                // Show the confirmation dialog when "Delete" is selected
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                Toast.makeText(this, "Product:$position", Toast.LENGTH_LONG).show()
                //if (position != null) {
                showDeleteConfirmationDialog(position)
                //}
                true
            }
            R.id.edit_product -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                val dialog = EditProductDialog(this,position,this)
                dialog.show()
                true
            }


            // Add cases for other options as needed
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_product_list -> {
                adapterListProducts = DetailAdapter(this, android.R.layout.simple_list_item_1, products)
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Detail Products")
                    .setAdapter(adapterListProducts, null)
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                val alertDialog = builder.create()

                // Show the AlertDialog
                alertDialog.show()

                return true
            }


            R.id.sort_product -> {
                orderList()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteConfirmationDialog(itemPosition: Int) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(R.string.delete_message)
        builder.setPositiveButton(R.string.button_delete) { dialog, _ ->
            // Handle the delete action here
            deleteItem(itemPosition)
        }
        builder.setNegativeButton(R.string.button_cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteItem(itemPosition: Int) {

        deleteFromDatabase(products[itemPosition])
        refreshFromDatabase()
    }

    fun deleteFromDatabase(product : Product) {
        db.productDao().delete(product)
    }

    fun refreshFromDatabase() {
        val list: List<Product> = db.productDao().getAll()
        val listProductWith: List<ProductWithMovement> = db.productDao().getProductsWithPlayLists()
        listProductWith.forEach {
            Log.i("ProductList", "Data product: " + it.product.name)
            Log.i("ProductList", "Data movements of product: " + it.movements.toString())
        }
        Toast.makeText(this, "Hi: " + list.size.toString(), Toast.LENGTH_LONG).show()
        products = list.toMutableList()
        Toast.makeText(this, "Hi: " + products.size.toString(), Toast.LENGTH_LONG).show()
        listViewProducts.invalidate()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map { it.name })
        listViewProducts.adapter = adapter

    }


    private fun orderList(){

        showCustomDialog()
        val dialogFragment = SortProductDialog()
        dialogFragment.setItemDialogListener(this)
        dialogFragment.show(supportFragmentManager, "ItemDialogFragment")


    }

    private fun showCustomDialog() {
        val dialogFragment = SortProductDialog()
        dialogFragment.show(supportFragmentManager, "MyDialogFragment")
    }

    override fun onItemSelected(item: String) {
        // Handle the selected item
        // For example, display a Toast or perform an action
        val orderedList = mutableListOf("")
        if(item == "Id") {
            products = db.productDao().getAll().sortedBy { it.id }.toMutableList()
            val productId = db.productDao().getAll().sortedBy { it.id }
            orderedList.add(productId.toString())
            Log.i("IDS", orderedList.toString())
            //db.productDao().loadAllByIds(products.map { it.id })
            //products.sortBy { it.id }

            refreshFromDatabase()
        }
        if(item == "Name") {
            products = db.productDao().getAll().sortedBy { it.name }.toMutableList()
            val productName = db.productDao().getAll().sortedBy { it.name }
            orderedList.add(productName.toString())
            Log.i("NAMES", orderedList.toString())
            //orderedList.sortedBy { it.name }
            //products.sortBy { it.name }
            refreshFromDatabase()
        }
        if(item == "Description") {
            val productDescription = db.productDao().getAll().sortedBy { it.description }
            orderedList.add(productDescription.toString())
            Log.i("DESCRIPTIONS", orderedList.toString())
            refreshFromDatabase()
        }
        if(item == "Type") {
            val productType = db.productDao().getAll().sortedBy { it.type }
            orderedList.add(productType.toString())
            Log.i("TYPES", orderedList.toString())
            refreshFromDatabase()
        }
        if(item == "Price") {
            val productPrice = db.productDao().getAll().sortedBy { it.price }
            orderedList.add(productPrice.toString())
            Log.i("PRICE", orderedList.toString())
            refreshFromDatabase()
        }


    }
}