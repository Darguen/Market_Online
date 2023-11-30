package com.panitagames.marketonline

import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.panitagames.marketonline.background.ApiCallback
import com.panitagames.marketonline.background.ApiTask
import org.json.JSONException
import org.json.JSONObject

class APIRestActivity : AppCompatActivity(), ApiCallback {
    private lateinit var listDataFromJson : ListView
    private lateinit var getRequestButton : Button
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var listData : MutableList<String>
    private  var URL : String = "https://reqres.in/api/users"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apirest)

        listDataFromJson = findViewById(R.id.listViewDataFromJson)
        getRequestButton = findViewById(R.id.getRequest)

        listData = mutableListOf(
            getString(R.string.api_instruction)
        )

        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, // Built-in layout for simple items
            listData
        )

        listDataFromJson.adapter = adapter

        getRequestButton.setOnClickListener{
            val apiRequestTask = ApiTask(this)
            apiRequestTask.execute(URL)
        }
    }

    override fun onRequestComplete(result: String) {
        //Update GUI
        listData = processingData(result)
        Log.i("APIRestActivity",listData.toString())
        adapter.clear()
        adapter.addAll(listData)
        adapter.notifyDataSetChanged()

    }

    fun processingData(result: String) : MutableList<String> {
        var list : MutableList<String> = mutableListOf()
        try {
            // Parse the JSON string into a JSONObject
            val jsonObject = JSONObject(result)

            // Access values from the JSON object
            val page = jsonObject.getInt("page")
            val perPage = jsonObject.getInt("per_page")
            val total = jsonObject.getInt("total")
            val totalPages = jsonObject.getInt("total_pages")



            // Access the "data" array
            val dataArray = jsonObject.getJSONArray("data")

            // Iterate through the array and access individual objects
            for (i in 0 until dataArray.length()) {
                val dataObject = dataArray.getJSONObject(i)
                /*
                val id = dataObject.getInt("id")
                val email = dataObject.getString("email")

                val lastName = dataObject.getString("last_name")
                val avatar = dataObject.getString("avatar")
                */
                val firstName = dataObject.getString("first_name")
                Log.i("APIRestActivity",firstName.toString())
                list.add(firstName)
            }

            // Access the "support" object
            /*
            val supportObject = jsonObject.getJSONObject("support")
            val supportUrl = supportObject.getString("url")
            val supportText = supportObject.getString("text")*/

        } catch (e: JSONException) {
            e.printStackTrace()
            list.add(getString(R.string.api_error))
        }
        return list.toMutableList()
    }
}