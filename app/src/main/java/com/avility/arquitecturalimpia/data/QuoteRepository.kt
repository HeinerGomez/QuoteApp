package com.avility.arquitecturalimpia.data

import com.avility.arquitecturalimpia.data.model.QuoteModel
import com.avility.arquitecturalimpia.data.model.QuoteProvider
import com.avility.arquitecturalimpia.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuoteService,
    private val quoteProvider: QuoteProvider
) {

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}