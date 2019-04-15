//
//  LoginView.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 15/04/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit

protocol LoginView : BaseView {
    func getEmail() -> String
    func getPassword() -> String
    func onFail(error: String?)
    func onSuccess()
    func showProgress()
    func hideProgress()
}
