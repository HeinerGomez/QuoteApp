package com.avility.arquitecturalimpia.domain

import com.avility.arquitecturalimpia.data.QuoteRepository
import com.avility.arquitecturalimpia.data.database.entities.toDatabase
import com.avility.arquitecturalimpia.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `when the api dosent return anything then get values from database`() = runBlocking {
        // Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        // When
        getQuotesUseCase()

        // Then
        coVerify(exactly = 1) {
            quoteRepository.getAllQuotesFromDataBase()
        }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        val quotes = listOf<Quote>(
            Quote("Hola 1", "Heiner"),
            Quote("Hola 2", "Heiner"),
            Quote("Hola 3", "Heiner"),
            Quote("Hola 4", "Heiner"),
        )

        // Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns quotes

        // When
        val response = getQuotesUseCase()

        // Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDataBase() }

        assert(quotes == response)
    }

}