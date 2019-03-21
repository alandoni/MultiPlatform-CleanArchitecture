//
//  ViewController.swift
//  CleanArchitecture
//
//  Created by Alan Quintiliano on 18/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit

let LOGIN_BUTTON_DEFAULT_TOP_CONSTRAINT_VALUE: CGFloat = 20
let LOGIN_BUTTON_WITH_ERROR_TOP_CONSTRAINT_VALUE: CGFloat = 61

class LoginViewController: BaseViewController<LoginPresenter>, LoginViewControllerProtocol {
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var errorLabel: UILabel!
    @IBOutlet weak var progressView: UIView!
    @IBOutlet weak var loginButtonTopConstraint: NSLayoutConstraint!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loginButtonTopConstraint.constant = LOGIN_BUTTON_DEFAULT_TOP_CONSTRAINT_VALUE
        errorLabel.isHidden = true
        progressView.isHidden = true
    }

    @IBAction func didTouchLogin(_ sender: Any) {
        presenter.attemptLogin()
    }
    
    func getEmail() -> String {
        return emailTextField.text!
    }
    
    func getPassword() -> String {
        return passwordTextField.text!
    }
    
    func onFail(error: String) {
        errorLabel.text = error
        errorLabel.isHidden = false
        loginButtonTopConstraint.constant = LOGIN_BUTTON_WITH_ERROR_TOP_CONSTRAINT_VALUE
    }
    
    func onSuccess() {
        errorLabel.isHidden = true
        loginButtonTopConstraint.constant = LOGIN_BUTTON_DEFAULT_TOP_CONSTRAINT_VALUE
    }
    
    func showProgress() {
        progressView.isHidden = false
    }
    
    func hideProgress() {
        progressView.isHidden = true
    }
}

