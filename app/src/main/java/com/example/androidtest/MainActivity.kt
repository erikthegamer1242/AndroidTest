package com.example.androidtest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var textCount: TextView
    lateinit var sharedPref : SharedPreferences

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
        sharedPref = getSharedPreferences("com.example.androidtest", Context.MODE_PRIVATE)
        sharedPref.getInt("count", count)
        Log.i("MyLog", "valOnStart ${count}")


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

    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStop")
        with (sharedPref.edit()) {
            putInt("count", count)
            apply()
        }
        Log.i("MyLog", "valOnStop ${count}")
        var nigg = 69
        sharedPref.getInt("count", nigg)
        Log.i("MyLog", "valOnStopRet ${nigg}")

    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onDestroy")

    }
    fun setOnClickListenerUp(view: View) {
        count = textCount.text.toString().toInt() + 1
        textCount.text = count.toString()

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