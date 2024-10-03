package com.vishesh.network.utils

import com.vishesh.common.CoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultDispatcherProvider
    @Inject
    constructor() : CoroutineDispatcherProvider {
        override val main = Dispatchers.Main
        override val io = Dispatchers.IO
        override val default = Dispatchers.Default
    }
