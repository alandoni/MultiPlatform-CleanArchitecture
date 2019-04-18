//
//  DependencyInjection.swift
//  CleanArchitecture
//
//  Created by Alan Quintiliano on 11/04/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import SwinjectStoryboard
import main

extension SwinjectStoryboard {
    @objc class func setup() {
        defaultContainer.register(Database.self) { _ in Database().initDatabase() }
        defaultContainer.register(HttpRequest.self) { _ in HttpRequest() }
        
        loginModule()
    }
    
    class func loginModule() {
        defaultContainer.register(LoginUseCase.self) { r in
            LoginUseCase(localRepository: r.resolve(UserLocalRepository.self)!,
                         remoteRepository: r.resolve(UserRemoteRepository.self)!)
        }
        defaultContainer.register(UserLocalRepository.self) { r in
            UserLocalRepository(database: r.resolve(Database.self)!)
        }
        defaultContainer.register(UserRemoteRepository.self) { r in
            UserRemoteRepository(request: HttpRequest())
        }
        defaultContainer.register(LoginPresenter.self) { r in
            LoginPresenter(loginUseCase: r.resolve(LoginUseCase.self)!)
        }
        defaultContainer.storyboardInitCompleted(LoginViewController.self) { r, c in
            c.presenter = r.resolve(LoginPresenter.self)
        }
    }
}

