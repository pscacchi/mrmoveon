package ar.scacchipa.mrmoveon.dominio

import android.graphics.PointF

interface ISprite {
    fun getPos(): PointF
    fun getIcon(): String
    fun move(): ISprite
}

