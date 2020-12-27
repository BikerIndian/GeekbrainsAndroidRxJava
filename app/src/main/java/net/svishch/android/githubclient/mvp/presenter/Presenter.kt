package net.svishch.android.githubclient.mvp.presenter

import net.svishch.android.githubclient.mvp.BtnIndexBase.Companion.btn1BaseIndex
import net.svishch.android.githubclient.mvp.BtnIndexBase.Companion.btn2BaseIndex
import net.svishch.android.githubclient.mvp.BtnIndexBase.Companion.btn3BaseIndex
import net.svishch.android.githubclient.mvp.model.CountersModel
import net.svishch.android.githubclient.mvp.view.MainView

class Presenter(private val view: MainView){
    private val model = CountersModel()

    fun counterClick(id: Int) {
        when (id) {
            btn1BaseIndex -> view.setButton1Text(model.next(id).toString())
            btn2BaseIndex -> view.setButton2Text(model.next(id).toString())
            btn3BaseIndex -> view.setButton3Text(model.next(id).toString())
        }
    }
}