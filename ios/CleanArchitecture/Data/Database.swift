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
    
    func initDatabase() -> Database {
        let dbPath = Bundle.main.bundleURL.deletingLastPathComponent().appendingPathComponent("db.sqlite").absoluteString
        do {
            self.db = try Connection(dbPath)
        } catch {
            NSLog(error.localizedDescription)
        }
        return self
    }
    
    override func executeSelectQuery(sql: String, params: KotlinArray) -> [[String : Any]]? {
        do {
            NSLog("Executing SQL %@", sql)
            var bindings: [Binding] = [];
            let it = params.iterator()
            while (it.hasNext()) {
                bindings.append(it.next() as! String)
            }
            
            let statement = try self.db!.prepare(sql, bindings)
            var list: Array<[String : String?]> = Array()
            while (try statement.step()) {
                var map : [String : String?] = [:]
                for columnName in statement.columnNames {
                    let columnIndex: Int = statement.columnNames.firstIndex(of: "name")!
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
            NSLog("Running SQL %@ %@", sql, bindings)
            let stmt = try self.db!.prepare(sql, bindings)
            try stmt.run()
            return Int32(truncatingIfNeeded: self.db!.lastInsertRowid)
        } catch {
            NSLog(error.localizedDescription)
            return -1
        }
    }
}
