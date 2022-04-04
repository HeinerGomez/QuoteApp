package com.avility.arquitecturalimpia.domain

import com.avility.arquitecturalimpia.data.QuoteRepository
import com.avility.arquitecturalimpia.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when the getRandomQuoteUseCase is called, must return a null if the list of quotes is empty`() = runBlocking {
        // Given
        val quotesFromDB = emptyList<Quote>()

        coEvery { quoteRepository.getAllQuotesFromDataBase() } returns quotesFromDB

        // When
        val response = getRandomQuoteUseCase()

        // Then
        assert(response == null)
    }

    @Test
    fun `when the getRandomQuoteUseCase is called, must return one random quote if the list quotes is not empty`() = runBlocking {
        // Given
        val quotesFromDB = listOf<Quote>(
            Quote("Quote 1", "Heiner")
        )
        coEvery { quoteRepository.getAllQuotesFromDataBase() } returns quotesFromDB

        // When
        val response = getRandomQuoteUseCase()

        // Then
        assert(response is Quote)
        assert(response == quotesFromDB.first())
    }
}