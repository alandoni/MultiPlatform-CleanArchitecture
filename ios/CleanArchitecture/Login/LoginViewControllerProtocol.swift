//
//  LoginViewControllerProtocol.swift
//  CleanArchitecture
//
//  Created by Alan Quintiliano on 18/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import Foundation

protocol LoginViewControllerProtocol {
    func getEmail() -> String
    func getPassword() -> String
    func onFail(error: String)
    func onSuccess()
    func showProgress()
    func hideProgress()
}
