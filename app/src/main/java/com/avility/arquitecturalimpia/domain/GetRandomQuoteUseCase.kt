package com.avility.arquitecturalimpia.domain

import com.avility.arquitecturalimpia.data.QuoteRepository
import com.avility.arquitecturalimpia.data.model.QuoteModel
import com.avility.arquitecturalimpia.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {

    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDataBase()

        if (!quotes.isNullOrEmpty()) {
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }

        return null
    }
}