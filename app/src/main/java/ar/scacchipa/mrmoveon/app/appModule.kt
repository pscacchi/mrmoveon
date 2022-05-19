package ar.scacchipa.mrmoveon.app

import ar.scacchipa.mrmoveon.dominio.*
import ar.scacchipa.mrmoveon.viewmodel.BoardViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module{
    fragment<FirstFragment>{ FirstFragment() }
    fragment<SecondFragment>{ SecondFragment() }
    viewModel{ BoardViewModel(
        get(), get(named("ISprites")), get(named("ControlSprites")))
    }
    single<Board> { Board() }
    factory(named("ISprites")) { listOf<ISprite>(
            QuiteSprite(10f, 20f, "X"),
            QuiteSprite(5.0f, 10.0f),
            QuiteSprite(10.0f, 10.0f),
            HorizontalSprite(3f, 3f, "H", get()),
            VerticalSprite(8f, 3f, "V", get()),
            CircularSprite(8f, 8f, "O", get(), 0.0)
        )
    }
    factory(named("ControlSprites")) { listOf(
        ControlSprite(12f, 12f, "P", get()))
     }
}