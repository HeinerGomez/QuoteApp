package com.avility.arquitecturalimpia.domain.model

import com.avility.arquitecturalimpia.data.database.entities.QuoteEntity
import com.avility.arquitecturalimpia.data.model.QuoteModel

data class Quote (val quote: String, val author: String)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)