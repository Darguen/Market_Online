package adapters

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment

class SortProductDialog: DialogFragment() {

    interface ItemDialogListener {
        fun onItemSelected(item: String)
    }

    private lateinit var listener: ItemDialogListener


    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val items = arrayOf("Id", "Name", "Description", "Type", "Price")
        val builder = AlertDialog.Builder(requireActivity())

        // Set the dialog title
        builder.setTitle("Choose an Item to sort by").setAdapter(
            ArrayAdapter(requireContext(), R.layout.simple_list_item_1, items)
        ) { _, which ->
            // Notify the listener when an item is selected
            listener.onItemSelected(items[which])


        }

        // Positive button
        builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
            // Do something when the positive button is clicked
            dialog.dismiss()
        }

        // Negative button (optional)
        builder.setNegativeButton("Cancel") { dialog: DialogInterface, _: Int ->
            // Do something when the negative button is clicked
            dialog.dismiss()
        }

        return builder.create()
    }

    fun setItemDialogListener(listener: ItemDialogListener) {
        this.listener = listener
    }
}