package ar.scacchipa.mrmoveon

import android.util.Size
import ar.scacchipa.mrmoveon.dominio.Board
import ar.scacchipa.mrmoveon.dominio.HorizontalSprite
import ar.scacchipa.mrmoveon.dominio.ISprite
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

class HorizontalSpriteTest: KoinTest {

    private val myTestModule = module {
        single<Board> { Board( Size(20, 20) ) }
        factory<ISprite> {
            HorizontalSprite(
                posX = 15f,
                posY = 15f,
                icon = "Z",
                board = get() )
        }
    }

    private val horizontalSprite by inject<ISprite>()

    @Test
    fun spriteOnEdgeOfBoard() {

        startKoin { modules(myTestModule) }

        println(horizontalSprite.getIcon())
        assertEquals("Z", horizontalSprite.getIcon())
        println(horizontalSprite.getPos().x)
        println(horizontalSprite.getPos().y)

        assertEquals(15f, horizontalSprite.getPos().x)

        var resultSprite = horizontalSprite
        repeat (5) {
            resultSprite = resultSprite.move() as HorizontalSprite
        }

        assertEquals(resultSprite.getPos().x, 0f)
        assertEquals(resultSprite.getPos().y, 10f)
    }
}