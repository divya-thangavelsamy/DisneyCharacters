package com.codewithdivya.disneycharacters.data.datasource

import com.codewithdivya.disneycharacters.data.dtos.DisneyListResponse
import retrofit2.Response
import retrofit2.http.GET

interface DisneyCharactersApi {

    @GET("characters")
    suspend fun getDisneyCharacterList() : Response<DisneyListResponse>
}
