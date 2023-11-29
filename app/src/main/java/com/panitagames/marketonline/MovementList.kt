package com.panitagames.marketonline

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.panitagames.marketonline.Database.AppDatabase
import com.panitagames.marketonline.adapters.MovementsAdapter
import com.panitagames.marketonline.entities.MovementHistory

class MovementList : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var listViewMovements: ListView
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var adapterListMovements: ArrayAdapter<MovementHistory>
    private lateinit var movements: MutableList<MovementHistory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movement_list)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().fallbackToDestructiveMigration()
            .build()

        movements = db.movementDao().getAll().toMutableList()

        // Initialize UI elements
        listViewMovements = findViewById(R.id.movementList)
        adapterListMovements = MovementsAdapter(this, android.R.layout.simple_list_item_1, movements)




        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movements.map { movements.toString() })




        listViewMovements.adapter = adapterListMovements



    }
}