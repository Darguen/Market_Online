package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.panitagames.marketonline.R
import entities.Product

class ProductAdapter (
    context: Context,
    resource: Int,
    products: List<Product>
) : ArrayAdapter<Product>(context, resource, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.activity_product_list, null)

        // Get the patient data at the current position
        val product = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewName)
        val idTextView = listItemView.findViewById<TextView>(R.id.textViewId)
        val priceTextView = listItemView.findViewById<TextView>(R.id.textViewPrice)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewDescription)
        val typeTextView = listItemView.findViewById<TextView>(R.id.textViewType)

        // Set the patient data in the TextViews
        nameTextView.text = product?.name
        idTextView.text = product?.id.toString()
        priceTextView.text = product?.price.toString()
        descriptionTextView.text = product?.description
        typeTextView.text = product?.type

        return listItemView
    }

}