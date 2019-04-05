package com.adqmobile.domain.presentation

import com.adqmobile.domain.presentation.IBaseView

interface IPresenter {
    fun attach(view: IBaseView)
}