package com.example.androidtest

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class SuccessActivity : AppCompatActivity() {
    lateinit var name : String
    lateinit var textView : TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        textView = findViewById<View>(R.id.nameText) as TextView
        name = intent.getStringExtra("name").toString()
        textView.text = "$name uspjesno ste napravili 10 koraka"


    }

    fun sendButton(view: View) {
        val uri: Uri = Uri.parse("smsto:0916218745")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", textView.text)
        startActivity(intent)
    }
}