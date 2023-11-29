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
import com.panitagames.marketonline.entities.MovementHistory
import com.panitagames.marketonline.entities.Product

class CreateProductDialog (

    context: Context,
    private var act: ProductList
    ) : Dialog(context) {

        private lateinit var type : EditText
        private lateinit var description : EditText
        private lateinit var name : EditText
        private lateinit var price : EditText
        private lateinit var db : AppDatabase
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
            ).allowMainThreadQueries().fallbackToDestructiveMigration()
                .build()

            val buttonAddAndGoBack : Button = findViewById(R.id.buttonAddAndGoBack)
            // Set a click listener for the "Go Back" button to dismiss the dialog
            buttonAddAndGoBack.setOnClickListener {
                //Add user to database
                val newProductId = db.productDao().insertAll(
                    Product(0, type.text.toString(),description.text.toString(), name.text.toString(),price.text.toString().toIntOrNull()?: 0)
                )

                db.movementDao().insertAll(MovementHistory(0, newProductId.toInt(), name.text.toString(), "Add Product"))

                act.refreshFromDatabase()
                dismiss()
            }
        }

    }