package com.panitagames.marketonline.adapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.panitagames.marketonline.Database.AppDatabase
import com.panitagames.marketonline.ProductList
import com.panitagames.marketonline.R
import entities.Product

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
            ).allowMainThreadQueries().build()


            val buttonAddAndGoBack : Button = findViewById(R.id.buttonAddAndGoBack)
            // Set a click listener for the "Go Back" button to dismiss the dialog
            buttonAddAndGoBack.setOnClickListener {

                //Edit product in database
                db.productDao().updateProduct(
                    Product(idEdit,type.text.toString(),description.text.toString(), name.text.toString(),price.toString().toIntOrNull()?: 0)
                )
                act.refreshFromDatabase()
                dismiss()
            }
        }

    }