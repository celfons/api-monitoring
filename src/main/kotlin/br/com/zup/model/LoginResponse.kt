package br.com.zup.model

data class LoginResponse(
        val login: Login
){
    data class Login(
            var usuario: String?,
            var nome: String?,
            var email: String?
    )
}