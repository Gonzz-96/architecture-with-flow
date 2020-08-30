package com.mx.gonz.flow.architecture.app

import android.app.Application
import com.mx.gonz.flow.architecture.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FlowArchitectureApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FlowArchitectureApp)
            modules(
                domainModule,
                localModule,
                remoteModule,
                roomModule,
                viewModelsModule
            )
        }
    }
}
