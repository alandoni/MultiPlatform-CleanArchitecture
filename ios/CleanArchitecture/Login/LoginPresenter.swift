//
//  LoginPresenter.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 12/04/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit
import main

class LoginPresenter: BasePresenter, CallBack {
    
    var view: LoginView?
    let useCase: LoginUseCase
    
    init(useCase: LoginUseCase) {
        self.useCase = useCase
    }
    
    func onCancel() {
        
    }
    
    func attach(view: BaseView) {
        self.view = view as? LoginView
    }
    
    func onClickLoginButton() {
        view?.showProgress()
        LoginController(loginUseCase: self.useCase, callback: self)
            .attemptLogin(email: view?.getEmail(), password: view?.getPassword())
    }
    
    func onFinish(result: Any?) {
        view?.hideProgress()
    }
    
    func onError(error_ error: String) {
        view?.onFail(error: error)
    }
}
