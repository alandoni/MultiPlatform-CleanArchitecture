package com.adqmobile.cleanarchitecture.task

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.usecases.LoginUseCase

class LoginTask(callBack: CallBack<LoginResponseEntity>) :
    Task<LoginUseCase, LoginRequestEntity, LoginResponseEntity>(callBack)