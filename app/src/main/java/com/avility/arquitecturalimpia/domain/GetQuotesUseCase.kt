package com.avility.arquitecturalimpia.domain

import com.avility.arquitecturalimpia.data.QuoteRepository
import com.avility.arquitecturalimpia.data.model.QuoteModel
import javax.inject.Inject


class GetQuotesUseCase @Inject constructor(private val repository : QuoteRepository) {

    suspend operator fun invoke(): List<QuoteModel>? = repository.getAllQuotes()

}