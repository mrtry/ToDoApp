package io.github.mrtry.todolist.app.todo.ui.menu

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import org.junit.Before
import org.junit.Test

class ToDoMenuLogoutActionHandlerTest {
    lateinit var actionHandler: ToDoMenuLogoutActionHandler

    lateinit var mockNavigator: ToDoNavigator

    @Before
    fun setUp() {
        mockNavigator = mock()

        actionHandler = ToDoMenuLogoutActionHandler(
            mock(),
            mockNavigator
        )
    }

    @Test
    fun handleAction() {
        actionHandler.handleAction()

        verify(mockNavigator, times(1)).showLogoutAlert(any())
    }
}