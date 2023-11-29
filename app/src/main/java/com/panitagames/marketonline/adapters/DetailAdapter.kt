package com.panitagames.marketonline.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.panitagames.marketonline.R
import com.panitagames.marketonline.entities.Product

class DetailAdapter(
    context: Context,
    resource: Int,
    products: List<Product>
) : ArrayAdapter<Product>(context, resource, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.details_list, null)

        // Get the patient data at the current position
        val product = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val idTextView = listItemView.findViewById<TextView>(R.id.textViewId)
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewName)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewDescription)
        val typeTextView = listItemView.findViewById<TextView>(R.id.textViewType)
        val priceTextView = listItemView.findViewById<TextView>(R.id.textViewPrice)


        // Set the patient data in the TextViews
        idTextView.text = "Id: " + product?.id.toString()
        nameTextView.text = "Name: " + product?.name
        descriptionTextView.text = "Description: " + product?.description
        typeTextView.text = "Type: " + product?.type
        priceTextView.text = "Price: " + product?.price.toString()

        return listItemView
    }
}