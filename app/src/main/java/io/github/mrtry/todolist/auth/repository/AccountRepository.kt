package io.github.mrtry.todolist.auth.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository
@Inject constructor() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun getUserId(): String? = firebaseAuth.currentUser?.uid
        ?: throw IllegalStateException("no login")

    fun isLoggedIn(): Boolean = firebaseAuth.currentUser != null

    fun logout() = firebaseAuth.signOut()
}