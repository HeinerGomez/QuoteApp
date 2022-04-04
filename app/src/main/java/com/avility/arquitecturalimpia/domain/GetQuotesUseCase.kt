package com.avility.arquitecturalimpia.domain

import com.avility.arquitecturalimpia.data.QuoteRepository
import com.avility.arquitecturalimpia.data.database.entities.toDatabase
import com.avility.arquitecturalimpia.data.model.QuoteModel
import com.avility.arquitecturalimpia.domain.model.Quote
import javax.inject.Inject


class GetQuotesUseCase @Inject constructor(private val repository : QuoteRepository) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        } else {
            repository.getAllQuotesFromDataBase()
        }
    }

}