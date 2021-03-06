//
//  BaseViewController.swift
//  CleanArchitecture
//
//  Created by Alan Quintiliano on 18/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit
import main

class BaseViewController<T: BasePresenter>: UIViewController, BaseView {
    func getString(resource: Int32) -> String {
        return ""
    }
    
    var presenter: T?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
