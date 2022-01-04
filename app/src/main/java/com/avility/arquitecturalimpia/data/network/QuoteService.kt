package com.avility.arquitecturalimpia.data.network

import com.avility.arquitecturalimpia.core.RetrofitHelper
import com.avility.arquitecturalimpia.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api: QuoteApiClient
) {
    suspend fun getQuotes(): List<QuoteModel> = withContext(Dispatchers.IO) {
        val response = api.getAllQuotes()
        response.body() ?: emptyList()
    }
}