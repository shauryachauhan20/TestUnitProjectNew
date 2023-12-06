package com.example.unittestproject

import com.example.unittestproject.util.LoginUtil
import org.junit.After
import org.junit.Before
import org.junit.Test

class LoginActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun username_is_empty() {
        val username = ""
        val assert = LoginUtil.validateLoginInput(username, "shyam@08", "shyam@08")

        assert(assert == "Please enter user name")
    }

    @Test
    fun username_length_short() {
        val username = "sskjt"
        val assert = LoginUtil.validateLoginInput(username, "shyam@08", "shyam@08")

        assert(assert == "Very short userName")
    }

    @Test
    fun email_is_empty() {
        val email = ""
        val assert = LoginUtil.validateLoginInput("shyam@08", email, "shyam@08")

        assert(assert == "Please enter Email")
    }

    @Test
    fun email_length_short() {
        val email = "ldlg"
        val assert = LoginUtil.validateLoginInput("shyam@08", email, "shyam@08")

        assert(assert == "Email is very short")
    }

    @Test
    fun email_format() {
        val email = "ldlgkl"
        val assert = LoginUtil.validateLoginInput("shyam@08", email, "shyam@08")

        assert(assert == "Please enter valid email")
    }

    @Test
    fun email_contain_no_digit() {
        val email = "ldlgk@"
        val assert = LoginUtil.validateLoginInput("shyam@08", email, "shyam@08")

        assert(assert == "Email must contains one digit")
    }

    @Test
    fun password_is_empty() {
        val pass = ""
        val assert = LoginUtil.validateLoginInput("shyam08", "shyam@08", pass)

        assert(assert == "Please enter Password")
    }

    @Test
    fun password_length_short() {
        val pass = "ldlg"
        val assert = LoginUtil.validateLoginInput("shyam08", "shyam@08", pass)

        assert(assert == "Password is very short")
    }
}