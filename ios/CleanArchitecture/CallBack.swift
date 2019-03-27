//
//  CallBack.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 27/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit

protocol CallBack {
    func onFinish(result: Any?)
    func onCancel()
    func onError(error: String)
}

extension CallBack {
    func onCancel() { }
    func onError(error: String) { }
}
