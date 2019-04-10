//
//  Request.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 10/04/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit
import main
import SwiftyJSON

class HttpRequest: NSObject, Request {
    func execute(api: IApi) -> [String : Any]? {
        do {
            NSLog("Requesting")
            let url = URL(string: "http://192.168.3.102:3000/api/\(api.getUrl())/")!
            var request = URLRequest(url: url)
            request.httpMethod = api.getMethod().name
            
            if (api.getBody() != nil) {
                request.httpBody = try JSON(rawValue: api.getBody()!)?.rawData()
            }
            
            let semaphore = DispatchSemaphore(value: 0)
            var json: JSON? = nil
            let task = URLSession.shared.dataTask(with: request) { data, response, error in
                if (error == nil) {
                    do {
                        json = try JSON(data: data!)
                        NSLog("Requested JSON")
                    } catch { }
                }
                semaphore.signal()
            }
            task.resume()
            semaphore.wait()
            NSLog("Returning JSON")
            return json?.dictionaryObject
        } catch {
            return nil
        }
    }
}
