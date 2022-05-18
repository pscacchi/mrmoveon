package ar.scacchipa.mrmoveon.dominio

import android.graphics.PointF

data class VerticalSprite(
    private val posX: Float = 5.0f,
    private val posY: Float = 5.0f,
    private val icon: String = "V",
    private val board: Board
): ISprite {
    override fun getPos() = PointF(posX, posY)
    override fun getIcon(): String {
        return icon
    }
    override fun move(): ISprite {
        var y = posY + 1
        if (y >= board.boardSize.height) {
            y = 0f
        }

        return this.copy(posY = y)
    }
}
