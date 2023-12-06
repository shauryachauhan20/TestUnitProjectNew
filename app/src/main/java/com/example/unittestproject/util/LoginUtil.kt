package com.example.unittestproject.util

object LoginUtil {

    fun validateLoginInput(userName: String, email: String, password: String): String? {
        if (userName.isEmpty()) return "Please enter user name"
        if (userName.length < 6) return "Very short userName"
        if (email.isEmpty()) return "Please enter Email"
        if (email.length < 6) return "Email is very short"
        if (!email.contains("@")) return "Please enter valid email"
        if (email.filter { it.isDigit() }.isEmpty()) return "Email must contains one digit"
        if (password.isEmpty()) return "Please enter Password"
        if (password.length < 6) return "Password is very short"

        return null
    }
}