package io.github.mrtry.todolist.auth

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationClient
@Inject constructor() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun isLoggedIn() = firebaseAuth.currentUser != null
}