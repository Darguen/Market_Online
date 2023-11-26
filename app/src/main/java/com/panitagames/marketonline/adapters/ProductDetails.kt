package com.panitagames.marketonline.adapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import com.panitagames.marketonline.R
import entities.Product

class ProductDetails(
    context: Context,
    private val product: Product
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_product_details)

        val textViewName = findViewById<TextView>(R.id.textViewNameC)
        val textViewId = findViewById<TextView>(R.id.textViewId)
        val textViewPrice = findViewById<TextView>(R.id.textViewPrice)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        val textViewType = findViewById<TextView>(R.id.textViewType)
        //val buttonGoBack = findViewById<Button>(R.id.buttonGoBackDialog)

        // Set patient information in TextViews
        textViewName.text = product.name
        textViewId.text = product.id.toString()
        textViewPrice.text = product.price.toString()
        textViewDescription.text = product.description
        textViewType.text = product.type

        // Set a click listener for the "Go Back" button to dismiss the dialog
        //buttonGoBack.setOnClickListener {
        //    dismiss()
        //}
    }
}