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
    
    func attemptLogin() {
        let email = self.view?.getEmail()
        let password = self.view?.getPassword()

        let loginRequest = LoginRequestEntity(email: email!, password: password!)
        
        do {
            let response = try LoginUseCase(repository: UserRepositoryImpl()).execute(params: loginRequest)
            self.onFinish(result: response)
        } catch {
            self.onError(error: "")
        }
    }
    
    func onFinish(result: LoginResponseEntity?) {
        view?.hideProgress()
        view?.onFail(error: result!.error!)
        view?.onSuccess()
    }
    
    func onError(error: String) {
        view?.hideProgress()
        view?.onFail(error: error)
    }
}
