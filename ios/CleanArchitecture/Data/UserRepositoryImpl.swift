//
//  UserRepositoryImpl.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 21/03/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import Foundation
import main
import SwiftyJSON
import SQLite

class UserRepositoryImpl : NSObject, UserRepository {
    func getByEmail(email: String) -> UserEntity? {
        do {
            let loginRequest = LoginRequestEntity(email: email, password: "")
            let json = try self.request(api: GetUserApi(loginRequest: loginRequest))!
            let user = UserEntity(name: json["name"].string!, email: json["email"].string!, password: json["password"].string!)
            try self.executeSQL(userEntity: user)
            return user
        } catch {
            return nil
        }
    }
    
    func request(api: IApi) throws -> JSON? {
        let url = URL(string: "http://192.168.3.102:3000/api/\(api.getUrl())/")!
        var request = URLRequest(url: url)
        request.httpMethod = api.getMethod().name
        
        let semaphore = DispatchSemaphore(value: 0)
        var json: JSON? = nil
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            if (error == nil) {
                do {
                    json = try JSON(data: data!)
                } catch {
                }
            }
            semaphore.signal()
        }
        task.resume()
        semaphore.wait()
        return json
    }
    
    func executeSQL(userEntity: UserEntity) throws -> Int64 {
        let dbPath = Bundle.main.bundleURL.deletingLastPathComponent().appendingPathComponent("db.sqlite").absoluteString
        let db = try Connection(dbPath)
        let userDb = UserInfoBD()
        let stmt = try db.prepare(userDb.createTable())
        try stmt.run()
        let stmtInsert = try db.prepare(userDb.insert())
        try stmtInsert.run(userEntity.name, userEntity.email, userEntity.password)
        let id = db.lastInsertRowid
        return id
    }
}
