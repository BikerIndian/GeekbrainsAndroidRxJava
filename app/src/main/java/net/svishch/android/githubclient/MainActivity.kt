package net.svishch.android.githubclient

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val counters = mutableListOf(0, 0, 0)

    private lateinit var buttonCounter1: Button
    private lateinit var buttonCounter2: Button
    private lateinit var buttonCounter3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCounter1 = findViewById(R.id.btn_counter1)
        buttonCounter2 = findViewById(R.id.btn_counter2)
        buttonCounter3 = findViewById(R.id.btn_counter3)



        buttonCounter1.setOnClickListener {
            buttonCounter1.text = (++counters[0]).toString()
        }
        buttonCounter2.setOnClickListener {
            buttonCounter2.text = (++counters[1]).toString()
        }
        buttonCounter3.setOnClickListener {
            buttonCounter3.text = (++counters[2]).toString()
        }

    }

    fun initViews(){
        buttonCounter1.text = counters[ 0 ].toString()
        buttonCounter2.text = counters[ 1 ].toString()
        buttonCounter3.text = counters[ 2 ].toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super .onSaveInstanceState(outState)
        outState.putIntArray( "counters" , counters.toIntArray())
    }
    override fun onSaveInstanceState(outState: Bundle , outPersistentState:
    PersistableBundle
    ) {
        super .onSaveInstanceState(outState , outPersistentState)
        outState.putIntArray( "counters" , counters.toIntArray())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super .onRestoreInstanceState(savedInstanceState)
        val countersArray = savedInstanceState.getIntArray( "counters" )
        countersArray?.toList()?.let {
            counters.clear()
            counters.addAll(it)
        }
        initViews()
    }
}