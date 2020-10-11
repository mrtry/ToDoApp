package io.github.mrtry.todolist.app.todo.viewmodel

import android.app.Activity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.app.todo.viewmodel.converter.ToDoListItemViewModelConverter
import io.github.mrtry.todolist.misc.extension.requireValue
import io.github.mrtry.todolist.misc.ui.viewmodel.ToolbarViewModel
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ToDoViewModelTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ToDoViewModel

    private lateinit var mockNavigator: ToDoNavigator
    private lateinit var mockDomainService: TaskDomainService
    private lateinit var mockConverter: ToDoListItemViewModelConverter

    @Before
    fun setUp() {
        mockNavigator = mock()
        mockDomainService = mock()
        mockConverter = mock()
        val mockActivity = mock<Activity>()
        whenever(mockActivity.title).thenReturn("")

        viewModel = ToDoViewModel(
            ToolbarViewModel(mockActivity),
            mock(),
            mockNavigator,
            mockDomainService,
            mockConverter,
            TestCoroutineScope()
        )
    }

    @Test
    fun load_success() = runBlockingTest {
        val mockFlow: Flow<List<Task>> = flow { emit(listOf(mock())) }
        whenever(mockDomainService.connectToRepository()).thenReturn(mockFlow)
        whenever(mockConverter.convert(any())).thenReturn(mock())

        viewModel.load()

        assertThat(viewModel.items).isNotEmpty()
    }

    @Test
    fun load_fail() = runBlockingTest {
        whenever(mockDomainService.connectToRepository()).thenThrow(IllegalStateException())

        viewModel.load()

        verify(mockNavigator, times(1)).showSnackBar(any(), any())
    }

    @Test
    fun load_empty() = runBlockingTest {
        val mockFlow: Flow<List<Task>> = flow { emit(listOf()) }
        whenever(mockDomainService.connectToRepository()).thenReturn(mockFlow)

        viewModel.load()

        assertThat(viewModel.items).isEmpty()
        assertThat(viewModel.showEmptyStatus.requireValue()).isTrue()
    }
}