package io.github.mrtry.todolist.app.todo.viewmodel

// TODO:
//  テストしたかったが、RobolectricTestRunnerのテストを走らせるとなぜかテストが終わらない
//  一旦コメントアウトする

//@RunWith(RobolectricTestRunner::class)
//class ToDoListItemViewModelTest {
//    @Rule
//    @JvmField
//    val rule: TestRule = InstantTaskExecutorRule()
//
//    private lateinit var viewModel: ToDoListItemViewModel
//
//    private lateinit var mockNavigator: ToDoNavigator
//    private lateinit var mockDomainService: ToDoDomainService
//
//    @Before
//    fun setUp() {
//        mockNavigator = mock()
//        mockDomainService = mock()
//
//        viewModel = ToDoListItemViewModel(
//            ToDo(),
//            mock(),
//            mockNavigator,
//            mockDomainService,
//            TestCoroutineScope()
//        )
//    }
//
//    @Test
//    fun checkIsComplete_fail() = runBlockingTest {
//        val beforeValue = viewModel.isComplete.requireValue()
//        whenever(mockDomainService.saveToRepository(any())).thenThrow(IllegalStateException())
//
//        viewModel.isComplete.value = !beforeValue
//
//        verify(mockNavigator, times(1)).showSnackBar(any(), any())
//        assertThat(viewModel.isComplete.requireValue()).isEqualTo(beforeValue)
//    }
//}