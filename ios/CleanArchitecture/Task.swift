//
//  Task.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 27/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit
import main

class Task<U: IEntity, V: IEntity>: Operation {
    let useCase: UseCase
    let callBack: CallBack?
    var param: U?
    var executingProcess: Bool = false
    var finishedProcess: Bool = false
    
    init(useCase: UseCase, callBack: CallBack) {
        self.useCase = useCase
        self.callBack = callBack
    }
    
    func execute(param: U) {
        self.param = param
        let queue = OperationQueue()
        queue.qualityOfService = .background
        queue.addOperation(self)
    }
    
    override var isExecuting: Bool {
        return self.executingProcess
    }
    
    override var isFinished: Bool {
        return self.finishedProcess
    }
    
    override var isAsynchronous: Bool {
        return true
    }
    
    override func start() {
        self.executingProcess = true;
        sleep(4)
        do {
            let result: AutoreleasingUnsafeMutablePointer<AnyObject?>? = nil
            try self.useCase.execute(param: self.param!, result: result)
            self.callBack?.onFinish(result: result)
            self.finishProcess()
        } catch {
            self.callBack?.onError(error: error.localizedDescription)
            self.finishProcess()
        }
    }

    func finishProcess() {
        self.executingProcess = false
        self.finishedProcess = true
    }
}
