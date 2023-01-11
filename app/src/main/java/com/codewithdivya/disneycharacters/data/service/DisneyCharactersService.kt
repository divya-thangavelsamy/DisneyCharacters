package com.codewithdivya.disneycharacters.data.service

import com.codewithdivya.disneycharacters.data.datasource.DisneyCharactersApi
import com.codewithdivya.disneycharacters.data.dtos.DisneyListResponse
import com.codewithdivya.disneycharacters.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DisneyCharactersService @Inject constructor(
    private val disneyApi: DisneyCharactersApi
){

    suspend fun getDisneyCharacterList() : Flow<Result<DisneyListResponse>> {
        return flow {
            try {
                val response = disneyApi.getDisneyCharacterList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Result.Success(it))
                    } ?:
                    emit(Result.Failure(IOException(response.message())))
                }
                else {
                    emit(Result.Failure(IOException()))
                }
            } catch (exception: Exception) {
                emit(Result.Failure(exception))
            }
            }
        }
    }
