//
//  LoginPresenter.swift
//  CleanArchitecture
//
//  Created by Alan Quintiliano on 18/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import Foundation
import main

class LoginPresenter: BasePresenter, CallBack {
    var view: LoginViewControllerProtocol?
    
    required init() {
        
    }
    
    override func attach(view: BaseViewControllerProtocol) {
        self.view = view as? LoginViewControllerProtocol
    }
    
    func attemptLogin() {
        let email = self.view?.getEmail()
        let password = self.view?.getPassword()
        view?.showProgress()

        let loginRequest = LoginRequestEntity(email: email!, password: password!)
        let useCase = LoginUseCase(repository: UserRepositoryImpl())
        Task<LoginRequestEntity, LoginResponseEntity>(useCase: useCase, callBack: self).execute(param: loginRequest)
    }
    
    func onFinish(result: Any?) {
        view?.hideProgress()
        view?.onFail(error: (result as! LoginResponseEntity).error!)
        view?.onSuccess()
    }
    
    func onError(error: String) {
        view?.hideProgress()
        view?.onFail(error: error)
    }
}
