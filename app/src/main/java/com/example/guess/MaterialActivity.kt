package com.example.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*


class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))
        Log.d(TAG,"secret:"+secretNumber.secret)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.replay_games))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.ok), {dialog, which ->
                    secretNumber.reset()
                    counter.setText(secretNumber.count.toString())
                    ed_number.setText("")
                    Log.d(TAG,"secret:"+secretNumber.secret)

                })

                .setNegativeButton(getString(R.string.cancel),null)
                .show()

//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
        counter.setText(secretNumber.count.toString())

    }
    fun check(view : View){

        val n = ed_number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG,"number:"+n)
        val diff = secretNumber.validate(n)
        var message = getString(R.string.well_done)
        if (diff < 0){
            message = getString(R.string.bigger)
        }
        if (diff > 0){
            message = getString(R.string.smaller)
        }
        if (diff == 0){
            if (secretNumber.count < 3){
                message = getString(R.string.excellent) + secretNumber.secret
            }

        }
        counter.setText(secretNumber.count.toString())
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok),null)
            .show()

    }
}