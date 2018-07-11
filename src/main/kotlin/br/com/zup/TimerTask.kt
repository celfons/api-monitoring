package br.com.zup

import br.com.zup.integration.Integration
import br.com.zup.model.LoginRequest

class TimerTask(
        private val integration: Integration
){

    fun run() {
        while(true) {
            integration.getProfile(LoginRequest("marcelr", "dSRiejY0TSt4"))
            Thread.sleep(600 * 1000)
        }
    }

}