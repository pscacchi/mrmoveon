package ar.scacchipa.mrmoveon.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class MrMoveonApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            fragmentFactory()
            androidContext(this@MrMoveonApp)
            modules(appModule)
        }
    }
}