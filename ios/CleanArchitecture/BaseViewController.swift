//
//  BaseViewController.swift
//  CleanArchitecture
//
//  Created by Alan Quintiliano on 18/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit

class BaseViewController<T: BasePresenter>: UIViewController {
    var presenter: T;
    
    required init?(coder aDecoder: NSCoder) {
        presenter = T();
        super.init(coder: aDecoder);
    }
}
