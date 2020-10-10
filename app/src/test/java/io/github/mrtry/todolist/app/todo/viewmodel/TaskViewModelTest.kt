package io.github.mrtry.todolist.app.todo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.misc.extension.requireValue
import io.github.mrtry.todolist.todo.domainservice.ToDoDomainService
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TaskViewModelTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TaskViewModel

    private lateinit var mockNavigator: ToDoNavigator
    private lateinit var mockDomainService: ToDoDomainService

    @Before
    fun setUp() {
        mockNavigator = mock()
        mockDomainService = mock()

        viewModel = TaskViewModel(
            mockNavigator,
            mockDomainService,
            TestCoroutineScope()
        )
    }

    @Test
    fun onAddTaskButtonClick_taskName_empty() = runBlockingTest {
        viewModel.taskName.value = ""

        viewModel.onAddTaskButtonClick(null)

        verify(mockDomainService, times(0)).saveToRepository(any())
    }

    @Test
    fun onAddTaskButtonClick_taskName_success() = runBlockingTest {
        viewModel.taskName.value = "hoge"

        viewModel.onAddTaskButtonClick(null)

        verify(mockDomainService, times(1)).saveToRepository(any())
        assertThat(viewModel.taskName.requireValue()).isEmpty()
    }

    @Test
    fun onAddTaskButtonClick_taskName_failed() = runBlockingTest {
        viewModel.taskName.value = "hoge"

        whenever(mockDomainService.saveToRepository(any())).thenThrow(IllegalStateException())

        viewModel.onAddTaskButtonClick(null)

        verify(mockNavigator, times(1)).showSnackBar(any(), any())
        assertThat(viewModel.taskName.requireValue()).isNotEmpty()
    }
}