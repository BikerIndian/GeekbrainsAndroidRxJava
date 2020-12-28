package net.svishch.android.githubclient

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.svishch.android.githubclient.mvp.presenter.BtnIndexBase.Companion.btn1BaseIndex
import net.svishch.android.githubclient.mvp.presenter.BtnIndexBase.Companion.btn2BaseIndex
import net.svishch.android.githubclient.mvp.presenter.BtnIndexBase.Companion.btn3BaseIndex
import net.svishch.android.githubclient.mvp.presenter.Presenter
import net.svishch.android.githubclient.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    val counters = mutableListOf(0, 0, 0)

    private lateinit var buttonCounter1: Button
    private lateinit var buttonCounter2: Button
    private lateinit var buttonCounter3: Button
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter (this)

        buttonCounter1 = findViewById(R.id.btn_counter1)
        buttonCounter2 = findViewById(R.id.btn_counter2)
        buttonCounter3 = findViewById(R.id.btn_counter3)


        buttonCounter1.setOnClickListener{
            presenter.counterClick(btn1BaseIndex)
        }
        buttonCounter2.setOnClickListener{
            presenter.counterClick(btn2BaseIndex)
        }
        buttonCounter3.setOnClickListener{
            presenter.counterClick(btn3BaseIndex)
        }
    }

    override fun setButton1Text(text: String?) {
        buttonCounter1.text = text
    }

    override fun setButton2Text(text: String?) {
        buttonCounter2.text = text
    }

    override fun setButton3Text(text: String?) {
        buttonCounter3.text = text
    }
}