//
//  Request.swift
//  CleanArchitecture
//
//  Created by Alan Donizete Quintiliano on 10/04/19.
//  Copyright © 2019 Alan Quintiliano. All rights reserved.
//

import UIKit
import main

class HttpRequest: BaseRequest {
    override func execute(api: BaseApi) -> String {
        NSLog("Requesting")
        let semaphore = DispatchSemaphore(value: 0)
        var responseStr: String = ""
        var responseError: Error? = nil
        let task = URLSession.shared.dataTask(with: createRequest(api: api)) { data, response, error in
            if (error == nil && data != nil) {
                responseStr = String(data: data!, encoding: .utf8)!
                NSLog("Requested JSON")
            } else {
                responseError = error
            }
            semaphore.signal()
        }
        task.resume()
        semaphore.wait()
        if (responseError != nil) {
            self.onError(error: responseError?.localizedDescription)
        }
        NSLog("Returning JSON")
        return responseStr
    }
    
    func createRequest(api: BaseApi) -> URLRequest {
        let url = URL(string: "http://192.168.0.16:3000/api/\(api.getUrl())/")!
        var request = URLRequest(url: url)
        request.httpMethod = api.getMethod().name
        
        if (api.getHeaders() != nil) {
            let headers = api.getHeaders()!
            for (key, value) in headers {
                request.addValue(value, forHTTPHeaderField: key)
            }
        }
        if (api.getBody() != nil) {
            request.httpBody = "\(api.getBody()!)".data(using: .utf8)
        }
        return request
    }
}
