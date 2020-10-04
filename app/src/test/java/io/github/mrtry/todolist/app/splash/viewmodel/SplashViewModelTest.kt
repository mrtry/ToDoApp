package io.github.mrtry.todolist.app.splash.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.mrtry.todolist.app.splash.ui.navigator.SplashNavigator
import io.github.mrtry.todolist.auth.AuthenticationClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    lateinit var viewModel: SplashViewModel

    lateinit var mockClient: AuthenticationClient
    lateinit var mockNavigator: SplashNavigator

    @Before
    fun setUp() {
        mockClient = mock()
        mockNavigator = mock()

        viewModel = SplashViewModel(mockClient, mockNavigator)
    }

    @Test
    fun ensuredLoggedIn_isLoggedIn_true() {
        whenever(mockClient.isLoggedIn()).thenReturn(true)

        viewModel.ensuredLoggedIn()

        verify(mockNavigator, times(0)).navigateToAuthentication()
        verify(mockNavigator, times(1)).navigateToToDo()
    }

    @Test
    fun ensuredLoggedIn_isLoggedIn_false() {
        whenever(mockClient.isLoggedIn()).thenReturn(false)

        viewModel.ensuredLoggedIn()

        verify(mockNavigator, times(1)).navigateToAuthentication()
        verify(mockNavigator, times(0)).navigateToToDo()
    }
}