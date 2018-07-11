package br.com.zup.model

data class Profile(
        val dsBuscaPerfil: DsBuscaPerfil
){
    data class DsBuscaPerfil(val perfil: Perfil)

    data class Perfil(
            val nome: String? = null,
            val email: String? = null,
            val ipphone: String? = null,
            val telefone: String? = null,
            val registroID: String? = null,
            val foto: String? = null
    )
}