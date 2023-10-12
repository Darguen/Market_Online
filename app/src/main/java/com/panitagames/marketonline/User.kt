package com.panitagames.marketonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class User : AppCompatActivity() {
    private lateinit var nameEdit : EditText
    private lateinit var button : Button
    private lateinit var list : ListView
    private lateinit var listTextos : ArrayList<String>
    private lateinit var adapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        nameEdit = findViewById(R.id.editTextNamePractice)
        button = findViewById(R.id.buttonPractice)
        list = findViewById(R.id.listaDeTextos)
        listTextos = ArrayList()
        listTextos.add("Hola")

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listTextos)
        list.adapter = adapter


        button.setOnClickListener{
            Toast.makeText(this,nameEdit.text,Toast.LENGTH_LONG).show()
            listTextos.add(nameEdit.text.toString())
            adapter.notifyDataSetChanged()
            Log.i("CreatePatient","Size of listaTexto = "+listTextos.size.toString())
            nameEdit.setText("")
        }

    }
}