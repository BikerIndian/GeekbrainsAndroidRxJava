package net.svishch.android.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface InfoView : MvpView {
    fun init()
    fun setId(text: String)
    fun setName(text: String)
    fun setForks(text: String)
}