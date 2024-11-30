package com.example.lovecounter.data.repository

import com.example.lovecounter.data.source.local.MainRoomDB
import com.example.lovecounter.data.source.remote.MainService
import com.example.lovecounter.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val mainRoomDB: MainRoomDB,
) : MainRepository