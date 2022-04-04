package com.avility.arquitecturalimpia.data

import com.avility.arquitecturalimpia.data.database.dao.QuoteDao
import com.avility.arquitecturalimpia.data.database.entities.QuoteEntity
import com.avility.arquitecturalimpia.data.network.QuoteService
import com.avility.arquitecturalimpia.domain.model.Quote
import com.avility.arquitecturalimpia.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        return api.getQuotes().map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDataBase(): List<Quote> {
        return quoteDao.getAllQuotes().map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAll()
    }
}