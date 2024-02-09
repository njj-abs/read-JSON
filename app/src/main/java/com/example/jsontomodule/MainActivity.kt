package com.example.jsontomodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import org.json.JSONArray
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStream


data class Person(
    val name: String,
    val age: Int,
    val email: String
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        main()
        readJson()
    }

    private fun readJson() {
        var json: String? = null

        try {
            val inputStream: InputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use {
                it.readText()
            }
            val jsonText = findViewById<TextView>(R.id.json_text)
            jsonText.text = json
            var str = ""
            val gson = Gson()
            val personList = gson.fromJson(json, Array<Any>::class.java).toList()
            println(personList)

            for (person in personList) {
                str += person
            }
            jsonText.text = str
        } catch (e:Exception){
            Toast.makeText(this@MainActivity, "$e" , Toast.LENGTH_LONG).show()
        }
    }
}