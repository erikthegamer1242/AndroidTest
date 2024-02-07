package com.example.androidtest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var textCount: TextView

    companion object {
        const val COUNT_KEY = "COUNT_KEY" // const key to save/read value from bundle
    }

    private var count = 0 // count value with setter. It will be easier, You can change this value and don't have to think about setting TextView.text
        set(value) {
            field = value
            textCount.text = value.toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textCount = findViewById<View>(R.id.textViewCounter) as TextView
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        count = sharedPreferences.getInt("count", 0)


    }
    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStart")

    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onResume")

    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onRestart")

    }
    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onPause")
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putInt("count", textCount.text.toString().toInt())
        }.apply()

    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStop")


    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onDestroy")

    }
    fun setOnClickListenerUp(view: View) {
        count = textCount.text.toString().toInt() + 1
        textCount.text = count.toString()
        if (count == 10) {
            count = 0
            val intent = Intent (this, SuccessActivity::class.java).apply {
                putExtra("name", findViewById<EditText>(R.id.plainTextName).text.toString())
            }
            startActivity(intent)
        }
    }
    fun setOnClickListenerDown(view: View) {
        if(count > 0)   {
            count -= 1
            textCount.text = count.toString()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) { // Here You have to save count value
        super.onSaveInstanceState(outState)
        Log.i("MyLog", "onSaveInstanceState")

        outState.putInt(COUNT_KEY, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MyLog", "onRestoreInstanceState")

        count = savedInstanceState.getInt(COUNT_KEY)
    }

}