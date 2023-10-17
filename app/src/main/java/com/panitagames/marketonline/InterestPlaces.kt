package com.panitagames.marketonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class InterestPlaces : AppCompatActivity() {
    private lateinit var nameEdit : EditText
    private lateinit var button : Button
    private lateinit var list : ListView
    private lateinit var listTextos : ArrayList<String>
    private lateinit var adapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest_places)

        nameEdit = findViewById(R.id.editTextNamePlace)
        button = findViewById(R.id.buttonSave)
        list = findViewById(R.id.listaDeTextos)
        listTextos = ArrayList()
        listTextos.add("Jumbo")
        listTextos.add("Lider")
        listTextos.add("Santa Isabel")

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listTextos)
        list.adapter = adapter


        button.setOnClickListener{
            Toast.makeText(this,nameEdit.text, Toast.LENGTH_LONG).show()
            listTextos.add(nameEdit.text.toString())
            adapter.notifyDataSetChanged()
            nameEdit.setText("")
        }
    }

}