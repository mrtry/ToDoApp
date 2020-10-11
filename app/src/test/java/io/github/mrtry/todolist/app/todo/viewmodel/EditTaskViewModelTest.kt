package io.github.mrtry.todolist.app.todo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.android.material.snackbar.Snackbar
import com.nhaarman.mockitokotlin2.*
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.EditTaskDialogFragmentValueHolder
import io.github.mrtry.todolist.app.todo.ui.navigator.EditTaskNavigator
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class EditTaskViewModelTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EditTaskViewModel

    private lateinit var mockDomainService: TaskDomainService
    private lateinit var mockNavigator: EditTaskNavigator

    @Before
    fun setUp() {
        val mockValueHolder = mock<EditTaskDialogFragmentValueHolder>()
        whenever(mockValueHolder.task).thenReturn(Task())

        mockDomainService = mock()
        mockNavigator = mock()

        viewModel = EditTaskViewModel(
            mockDomainService,
            mockNavigator,
            mockValueHolder,
            TestCoroutineScope()
        )
    }

    @Test
    fun onSaveClick_taskName_success() = runBlockingTest {
        viewModel.onSaveClick()

        verify(mockNavigator, times(1)).dismissDialog()
    }

    @Test
    fun onSaveClick_taskName_failed() = runBlockingTest {
        whenever(mockDomainService.saveToRepository(any())).thenThrow(IllegalStateException())

        viewModel.onSaveClick()

        verify(mockNavigator, times(1)).showSnackBar(
            R.string.edit_task_fragment_error_update_task_failed,
            Snackbar.LENGTH_SHORT
        )
    }
}