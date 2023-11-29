package com.panitagames.marketonline.adapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.panitagames.marketonline.Database.AppDatabase
import com.panitagames.marketonline.ProductList
import com.panitagames.marketonline.R
import com.panitagames.marketonline.entities.Product

class EditProductDialog (

    context: Context,
    idProductEdit : Int,
    private var act: ProductList
    ) : Dialog(context) {

        private lateinit var type : EditText
        private lateinit var description : EditText
        private lateinit var name : EditText
        private lateinit var price : EditText
        private lateinit var db : AppDatabase
        private var idEdit : Int = idProductEdit
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.activity_product_edit)

            type = findViewById(R.id.editTextType)
            description = findViewById(R.id.editTextDescription)
            name = findViewById(R.id.editTextName)
            price = findViewById(R.id.editTextPrice)


            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().fallbackToDestructiveMigration()
                .build()
            val list : List<Product> = db.productDao().getAll().toMutableList()
            val productType = list.map { it.type }
            val productDescription = list.map { it.description }
            val productName = list.map { it.name }
            val productPrice = list.map { it.price }

            type.setText(productType[idEdit].toString())
            description.setText(productDescription[idEdit].toString())
            name.setText(productName[idEdit].toString())
            price.setText(productPrice[idEdit].toString())





            val buttonAddAndGoBack : Button = findViewById(R.id.buttonAddAndGoBack)
            // Set a click listener for the "Go Back" button to dismiss the dialog
            buttonAddAndGoBack.setOnClickListener {

                //Edit product in database
                db.productDao().updateProduct(
                    Product(idEdit,type.text.toString(),description.text.toString(), name.text.toString(),price.text.toString().toIntOrNull()?: 0)
                )
                Log.i("Product: ", db.productDao().getAll().toString())
                //db.movementDao().insertAll(MovementHistory(0, idEdit, name.text.toString(), "Edit Product"))
                act.refreshFromDatabase()
                dismiss()
            }
        }

    }