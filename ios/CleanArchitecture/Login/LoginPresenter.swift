//
//  LoginPresenter.swift
//  CleanArchitecture
//
//  Created by Alan Quintiliano on 18/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import Foundation
import main

class LoginPresenter: BasePresenter {
    var view: LoginViewControllerProtocol?
    
    required init() {
        
    }
    
    override func attach(view: BaseViewControllerProtocol) {
        self.view = view as? LoginViewControllerProtocol
    }
    
    func viewDidLoad() {
        
    }
    
    func attemptLogin() {
        let email = self.view?.getEmail()
        let password = self.view?.getPassword()

        let loginRequest = LoginRequestEntity(email: email, password: password)
        LoginUseCase.init(repository: <#T##UserRepository#>).execute(params: loginRequest)
    }
    
    func onFinish(result: Any?) { //LoginResponseEntity?) {
        view?.hideProgress()
        view?.onSuccess()
    }
    
    func onError(error: String) {
        view?.hideProgress()
        view?.onFail(error: error)
    }
}
