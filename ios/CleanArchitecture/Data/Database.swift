//
//  Database.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 10/04/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit
import main
import SQLite

class Database: AbstractDatabase {
    
    var db: Connection?
    let DATABASE_VERSION: Int32 = 1
    
    func initDatabase() -> Database {
        let dbPath = Bundle.main.bundleURL.deletingLastPathComponent().appendingPathComponent("db.sqlite").absoluteString
        do {
            self.db = try Connection(dbPath, readonly: false)
            let databaseInitializer = DatabaseInitializer(database: self)
            
            let currentVersion = self.db!.userVersion
            NSLog("\(self.db!.userVersion)")
            databaseInitializer.onCreate()
            if (databaseInitializer.onUpgrade(oldVersion: currentVersion, newVersion: self.DATABASE_VERSION)) {
                 self.db!.userVersion = self.DATABASE_VERSION
                NSLog("\(self.db!.userVersion)")
            }
            
        } catch {
            NSLog(error.localizedDescription)
        }
        return self
    }
    
    override func executeSelectQuery(sql: String, params: KotlinArray?) -> [[String : Any]]? {
        do {
            var bindings: [Binding] = [];
            if (params != nil) {
                let it = params!.iterator()
                while (it.hasNext()) {
                    bindings.append(it.next() as! String)
                }
            }
            NSLog("Executing SQL: %@ with %@", sql, bindings)
            let statement = try self.db!.prepare(sql, bindings)
            var list: Array<[String : Any]> = Array()
            while (try statement.step()) {
                var map : [String : Any] = [:]
                for columnName in statement.columnNames {
                    let columnIndex: Int = statement.columnNames.firstIndex(of: columnName)!
                    let value = statement.row[columnIndex] as String
                    map[columnName] = value
                }
                list.append(map)
            }
            return list
        } catch {
            NSLog(error.localizedDescription)
            return nil
        }
    }
    
    override func runStatement(sql: String, params: KotlinArray) -> Int32 {
        do {
            var bindings: [Binding] = [];
            let it = params.iterator()
            while (it.hasNext()) {
                bindings.append(it.next() as! String)
            }
            NSLog("Running SQL: %@ with %@", sql, bindings)
            let stmt = try self.db!.prepare(sql, bindings)
            try stmt.run()
            return Int32(truncatingIfNeeded: self.db!.lastInsertRowid)
        } catch {
            NSLog(error.localizedDescription)
            return -1
        }
    }
}

extension Connection {
    public var userVersion: Int32 {
        get { return try! Int32(scalar("PRAGMA user_version") as! Int64) }
        set { try! run("PRAGMA user_version = \(newValue)") }
    }
}