//
//  PresenterProtocol.swift
//  CleanArchitecture
//
//  Created by Alan Quintiliano on 18/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import Foundation

class BasePresenter {
    
    required init() { }
    
    func attach(view: BaseViewControllerProtocol) { }
}
