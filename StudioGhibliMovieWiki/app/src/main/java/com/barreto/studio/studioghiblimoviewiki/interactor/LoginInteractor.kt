package com.barreto.studio.studioghiblimoviewiki.interactor

import android.content.Context
import com.barreto.studio.studioghiblimoviewiki.repository.UserRepository

class LoginInteractor (private val context: Context) {
    val userRepository =
        UserRepository()


    fun login(email: String, senha: String, callback: (resultado: Boolean, mensagem: String?)-> Unit){

        //AQUI FAZ AS REGRAS DE NEGÓCIO DO LOGIN, VALIDAÇOES
        if (email.isEmpty() && senha.isNotEmpty()) {
            callback(false, "E-mail obrigatório!")
            return
        }

        if (senha.isEmpty() && email.isNotEmpty()) {
            callback(false,"Senha obrigatória!")
            return
        }

        if (email.isEmpty() && senha.isEmpty()) {
            callback(false,"Preencha os campos corretamente")
            return
        }

        if(senha.length < 6){
            callback(false,null)
            return
        }


        userRepository.authLogin(email,senha){
            resultado, mensagem -> callback(resultado, mensagem)
        }

    }


}