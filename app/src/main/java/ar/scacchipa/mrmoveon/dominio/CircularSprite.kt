package ar.scacchipa.mrmoveon.dominio

import android.graphics.PointF
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

data class CircularSprite(
    private val posX: Float = 5.0f,
    private val posY: Float = 5.0f,
    private val icon: String = "V",
    private val board: Board,
    private val direction: Double = 0.0
): ISprite {
    override fun getPos() = PointF(posX, posY)
    override fun getIcon(): String {
        return icon
    }
    override fun move(): ISprite {
        var x = posX + 1 * cos(direction).toFloat()
        if (x >= board.boardSize.width) {
            x = 0f
        }
        if (x < 0f) {
            x = board.boardSize.width.toFloat()
        }
        var y: Float = posY + 1 * sin(direction).toFloat()
        if (y >= board.boardSize.height) {
            y = 0f
        }
        if ( y < 0f) {
            y = board.boardSize.height.toFloat()
        }

        return this.copy(
            posX = x,
            posY = y,
            direction = direction + PI / 50
        )
    }
}
