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

class CreateProductDialog (

    context: Context,
    idProduct : Int,
    private var act: ProductList
    ) : Dialog(context) {

        private lateinit var type : EditText
        private lateinit var description : EditText
        private lateinit var name : EditText
        private lateinit var price : EditText
        private lateinit var db : AppDatabase
        private var id : Int = idProduct
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.activity_product)

            type = findViewById(R.id.editTextTypeC)
            description = findViewById(R.id.editTextDescriptionC)
            name = findViewById(R.id.editTextNameC)
            price = findViewById(R.id.editTextPriceC)

            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().build()


            val buttonAddAndGoBack : Button = findViewById(R.id.buttonAddAndGoBack)
            // Set a click listener for the "Go Back" button to dismiss the dialog
            buttonAddAndGoBack.setOnClickListener {
                //Add user to database
                db.productDao().insertAll(
                    Product(id,type.text.toString(),description.text.toString(), name.text.toString(),price.toString().toIntOrNull()?: 0)
                )

                act.refreshFromDatabase()
                dismiss()
            }
        }

    }