package net.svishch.android.githubclient.mvp.presenter

import net.svishch.android.githubclient.R
import net.svishch.android.githubclient.mvp.model.CountersModel
import net.svishch.android.githubclient.mvp.view.MainView
import java.lang.String

class Presenter (private val view : MainView ){
    private val model = CountersModel()
}