package com.panitagames.marketonline.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.panitagames.marketonline.R
import com.panitagames.marketonline.entities.MovementHistory

class MovementsAdapter(
    context: Context,
    resource: Int,
    movements: List<MovementHistory>
) : ArrayAdapter<MovementHistory>(context, resource, movements) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.activity_movement_list, null)

        // Get the patient data at the current position
        val movement = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val idTextView = listItemView.findViewById<TextView>(R.id.id)
        val productIdTextView = listItemView.findViewById<TextView>(R.id.productId)
        val productNameTextView = listItemView.findViewById<TextView>(R.id.productName)
        val movementTypeTextView = listItemView.findViewById<TextView>(R.id.movementType)


        // Set the patient data in the TextViews
        idTextView.text = "Id: " + movement?.movementId.toString()
        productIdTextView.text = "Product Id: " + movement?.pId.toString()
        productNameTextView.text = "Product Name: " + movement?.productName
        movementTypeTextView.text = "Movement Type: " + movement?.movementType

        return listItemView
    }
}