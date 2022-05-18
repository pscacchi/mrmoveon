package ar.scacchipa.mrmoveon.viewmodel

import android.util.Size
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ar.scacchipa.mrmoveon.dominio.Board
import ar.scacchipa.mrmoveon.dominio.ControlSprite
import ar.scacchipa.mrmoveon.dominio.ISprite

class BoardViewModel: ViewModel() {
    private val boardLD = MutableLiveData<Board>()
    private val spritesLD = MutableLiveData( listOf<ISprite>() )
    private val controlSpitesLD = MutableLiveData( listOf<ControlSprite>())
    private val directionLD = MutableLiveData<Double>()
    fun getSprites():List<ISprite> {
        return spritesLD.value?:listOf()
    }
    fun addSprite(newSprite: ISprite) {
        spritesLD.value = (spritesLD.value?: listOf()) + newSprite
    }
    fun addObserver(owner: LifecycleOwner, observer: Observer<List<ISprite>>) {
        this.spritesLD.observe(owner, observer)
    }
    fun getControlSprites(): List<ControlSprite> {
        return controlSpitesLD.value?: listOf()
    }
    fun addControlSprite(newControlSprite: ControlSprite) {
        controlSpitesLD.value = (controlSpitesLD.value?: listOf()) + newControlSprite
    }
    fun setControlObserver(owner: LifecycleOwner, observer: Observer<List<ControlSprite>>) {
        this.controlSpitesLD.observe(owner, observer)
    }
    fun getBoardSize(): Size {
        return Size(20,20)
    }
    fun moveAll() {
        spritesLD.value = getSprites().map { it.move() }
        controlSpitesLD.value = getControlSprites().map { it.move() }
    }
    fun setBoard(board: Board) {
        this.boardLD.value = board
    }
    fun setDireccion(direction: Double) {
        this.directionLD.value = direction
    }
    fun removeDirection() {
        this.directionLD.value = null
    }
    fun getDirection(): Double? {
        return this.directionLD.value
    }
}