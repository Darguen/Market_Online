package com.panitagames.marketonline

import adapters.ProductAdapter
import adapters.ProductDetails
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import entities.Product

class ProductList: AppCompatActivity() {

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

        // Initialize UI elements
        listViewProducts = findViewById(R.id.listViewProducts)

        // Create a sample list of patients (you should replace this with your data source)
        products = mutableListOf(
            Product("Bebida Coca-cola", "bebida", "botella 597cc", 1, 1200),
            Product("Queso", "comida", "paquete laminado 250g", 2, 3000),
            Product("Cereal", "comida", "caja chocapic 500g", 3, 4000),
            Product("Salame", "comida", "paquete 200 g", 4, 5500),
            Product("Carne vacuno", "comida", "trozo 1kg", 5, 10000),
            // Add more patients as needed
        )

        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, products)

        listViewProducts.adapter = adapter



        // Handle item clicks to view patient details
        listViewProducts.setOnItemClickListener { _, _, position, _ ->
            val selectedProduct = products[position]
            listOption = !listOption
            if (detailOption) {
                showPatientDetailDialog(selectedProduct)
            }
            else {
                val intent = Intent(this, ProductDetails::class.java)
                intent.putExtra("product", selectedProduct)

                startActivity(intent)
            }
        }
        registerForContextMenu(listViewProducts)
    }

   /* override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.patient_list_menu, menu)
    }*/
    /*override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                // Handle the "Edit" option
                val intent = Intent(this, PatientEditActivity::class.java)
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                intent.putExtra("product",products.get(position))
                intent.putExtra("position",position)
                startActivityForResult(intent, REQUEST_EDITER)
                true
            }
            R.id.action_delete -> {
                // Handle the "Delete" option
                // Show the confirmation dialog when "Delete" is selected
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                showDeleteConfirmationDialog(position)
                true
            }
            // Add cases for other options as needed
            else -> super.onContextItemSelected(item)
        }
    }*/

    fun changeAdapter(view: View) {
        if (listOption) {
            adapterItems = ProductAdapter(this, R.layout.activity_product_list, products)
            listViewProducts.adapter = adapterItems
        } else {
            // Perform actions when listOption is false
            //adapter = ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, patients.map { it.name })
            adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, products)
            listViewProducts.adapter = adapter
        }
        listOption = !listOption
    }

    //fun goCreatePatient(view: View) {
    //    val intent = Intent(this, PatientRegistrationActivity::class.java)
    //    startActivityForResult(intent, REQUEST_REGISTER)
    //}

    private fun showPatientDetailDialog(product: Product) {
        val dialog = ProductDetails(this, product)
        dialog.show()
    }

    /*private fun showDeleteConfirmationDialog(itemPosition: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.message_delete_patient)
        builder.setPositiveButton(R.string.btn_delete) { dialog, which ->
            // Handle the delete action here
            deleteItem(itemPosition)
        }
        builder.setNegativeButton(R.string.btn_cancel) { dialog, which ->
            dialog.dismiss()
        }
        builder.create().show()
    }*/

    private fun deleteItem(itemPosition: Int) {
        products.removeAt(itemPosition)
        adapterItems.notifyDataSetChanged()
    }


    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newPatient = data?.getParcelableExtra<Patient>("new")
            if (newPatient != null) {
                patients.add(newPatient)
                if (listOption) {

                }
                adapterItems.notifyDataSetChanged()
            }
        }
        if (requestCode == REQUEST_EDITER && resultCode == RESULT_OK) {
            val editedPatient = data?.getParcelableExtra<Patient>("patient")
            val position = data!!.getIntExtra("position",-1)

            if (position != -1) {
                if (editedPatient != null) {
                    patients.set(position,editedPatient)
                }
            }
            adapterItems.notifyDataSetChanged()
        }
    }*/
}