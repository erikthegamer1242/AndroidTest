package com.example.andoridtest

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class SuccessActivity : AppCompatActivity() {
    @SuppressLint("ResourceType", "WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val textViewSuccess = findViewById<TextView>(R.id.successText)
        val button = findViewById<Button>(R.id.buttonIntent)
        val ime = intent.getStringExtra("name")
        val successMessage = getString(R.string.successMessage)

        textViewSuccess.text = "$ime, $successMessage"

        button.setOnClickListener {
            val selectedRadioButtonId = findViewById<RadioGroup>(R.id.radioGroupPhoneNumbers).checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
            var phoneNumber = selectedRadioButton?.text.toString()
            val message = textViewSuccess.text.toString()

            if (selectedRadioButton == null) {
                phoneNumber = "0993232465"
            }

            val uri = Uri.parse("smsto:$phoneNumber")
            val intent = Intent(Intent.ACTION_SENDTO, uri)

            intent.putExtra("sms_body", message)

            val chooser: Intent = Intent.createChooser(intent, "")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }

            else {
                Toast.makeText(this, "No SMS app found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
