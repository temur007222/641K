package com.example.a642k

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var read: Button? = null
    var write: Button? = null
    var userInput: EditText? = null

    private val filename = "demoFile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        read = findViewById(R.id.read_button)
        write = findViewById(R.id.write_button)
        userInput = findViewById(R.id.userInput)
        read!!.setOnClickListener(this)
    }

    fun printMessage(m: String?) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show()
    }

    override fun onClick(view: View) {
        val b = view as Button

        val b_text = b.text.toString()
        when (b_text.lowercase(Locale.getDefault())) {
            "write" -> {
                writeData()
            }
            "delete" -> {
                DeleteData()
            }
        }
    }

        private fun writeData() {
            try {
                val fos: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
                val data = userInput!!.text.toString()
                fos.write(data.toByteArray())
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            userInput!!.setText("")
            printMessage("writing to file " + filename + "completed...")
        }

        private fun DeleteData() {
            deleteFile(filename)
            Log.d("@@@", filename)
            printMessage("deleting the file $filename is completed...")
        }
    }