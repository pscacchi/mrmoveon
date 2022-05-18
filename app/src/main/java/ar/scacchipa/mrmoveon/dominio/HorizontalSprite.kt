package ar.scacchipa.mrmoveon.dominio

import android.graphics.PointF

data class HorizontalSprite(
    private val posX: Float = 5.0f,
    private val posY: Float = 5.0f,
    private val icon: String = "H",
    private val board: Board
): ISprite {
    override fun getPos() = PointF(posX, posY)
    override fun getIcon(): String {
        return icon
    }
    override fun move(): ISprite {
        var x = posX + 1
        if (x >= board.boardSize.width) {
            x = 0f
        }

        return this.copy(posX = x)
    }
}
