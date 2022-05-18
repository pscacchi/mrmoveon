package ar.scacchipa.mrmoveon.dominio

import android.graphics.PointF

data class QuiteSprite(
    private val posX: Float = 5.0f,
    private val posY: Float = 5.0f,
    private val icon: String = "X"
): ISprite {
    override fun getPos() = PointF(posX, posY)
    override fun getIcon(): String {
        return icon
    }
    override fun move(): ISprite {
        return this.copy()
    }
}

