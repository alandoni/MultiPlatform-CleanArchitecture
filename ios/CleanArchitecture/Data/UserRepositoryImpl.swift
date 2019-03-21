//
//  UserRepositoryImpl.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 21/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import Foundation
import main

class UserRepositoryImpl : NSObject, UserRepository {
    func getByEmail(email: String) -> UserEntity? {
        return UserEntity(name: "Alan", email: "alan.etm@gmail.com", password: "123123")
    }
}
