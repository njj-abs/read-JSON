package com.example.jsontomodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import java.io.InputStream



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
            val jsonArray = JSONArray(json)
            jsonText.text = jsonArray.toString()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val name = jsonObject.getJSONObject("name")
                val firstName = name.getString("firstName")
                str += firstName
            }
            jsonText.text = str

        } catch (e:Exception){
            Toast.makeText(this@MainActivity, "$e" , Toast.LENGTH_LONG).show()
        }
    }
}