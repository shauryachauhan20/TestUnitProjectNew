package com.example.unittestproject.viewModel

import com.example.unittestproject.helpers.MainDispatcherRule
import com.example.unittestproject.util.ViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TestViewModelTest {
    private val catRepository: CatApiRepo = mockk()
    private lateinit var viewmodelTest: TestViewModel
    private val limit = 10
    private lateinit var viewStates: MutableList<ViewState>

    @get:Rule
    val rule = MainDispatcherRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        viewmodelTest = TestViewModel(catRepository)
        viewStates = mutableListOf()
        viewmodelTest.viewState.observeForever {
            viewStates.add(it)
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `should emit error object when api response error`() = runTest {
        val message = "Error from api"
        coEvery {
            catRepository.catData(any())
        } throws IllegalAccessException(message)

        viewmodelTest.getApiCallItem(limit)

        coVerify {
            catRepository.catData(limit)
        }
        dispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(ViewState.Loading, viewStates[0])
        Assert.assertEquals(ViewState.Error(message), viewStates[1])
        verify(exactly = 0) {
            //
        }
    }


    @Test
    fun `when fetching api data is shown`() = runTest {
        val response = catRepository.catData(limit)

        coEvery {
            catRepository.catData(any())
        }
        viewmodelTest.getApiCallItem(limit)

        coVerify {
            viewmodelTest.getApiCallItem(limit)
        }
        dispatcher.scheduler.advanceUntilIdle()

//        assert(viewStates[0] is ViewState.Loading)
//        assert(viewStates[1] is ViewState.Content)
        Assert.assertEquals(ViewState.Loading, viewStates[0])
        Assert.assertEquals(ViewState.Content(response), viewStates[1])
        verify {
            assert(true)
        }

    }
}
