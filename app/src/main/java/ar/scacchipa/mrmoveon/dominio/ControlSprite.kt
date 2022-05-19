package ar.scacchipa.mrmoveon.dominio

import android.graphics.PointF
import ar.scacchipa.mrmoveon.viewmodel.BoardViewModel
import kotlin.math.cos
import kotlin.math.sin

data class ControlSprite(
    private val posX: Float = 5.0f,
    private val posY: Float = 5.0f,
    private val icon: String = "V",
    private val board: Board,
    private val viewModel: BoardViewModel? = null
): ISprite, IControlable {

    override fun getPos() = PointF(posX, posY)
    override fun getIcon(): String {
        return icon
    }
    override fun getDirection(): Double? {
        return viewModel?.getDirection()
    }
    override fun move(): ControlSprite {
       getDirection()?.let { dir ->
            var x = posX + 1 * cos(dir).toFloat()
            if (x >= board.boardSize.width) {
                x = 0f
            }
            if (x < 0f) {
                x = board.boardSize.width.toFloat()
            }
            var y: Float = posY + 1 * sin(dir).toFloat()
            if (y >= board.boardSize.height) {
                y = 0f
            }
            if (y < 0f) {
                y = board.boardSize.height.toFloat()
            }

            return this.copy(
                posX = x,
                posY = y
            )
        }
        return this
    }
}