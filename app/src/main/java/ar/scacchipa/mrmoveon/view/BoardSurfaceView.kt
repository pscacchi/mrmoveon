package ar.scacchipa.mrmoveon.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.SizeF
import android.view.SurfaceHolder
import android.view.SurfaceView
import ar.scacchipa.mrmoveon.dominio.ISprite
import ar.scacchipa.mrmoveon.viewmodel.BoardViewModel

class BoardSurfaceView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : SurfaceView(context, attrs, defStyleAttr, defStyleRes) {

    var boardViewModel: BoardViewModel? = null
    set(value) {
        field= value
        this.invalidate()
    }
    val paint = Paint()
    val backgroundPaint = Paint()
    var blockSize: SizeF? = null
    init {
        setWillNotDraw(false)
        paint.color = Color.BLACK
        paint.textSize = 50.0f
        backgroundPaint.color = Color.LTGRAY
        this.holder.addCallback ( object: SurfaceHolder.Callback {
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
                val canvas = surfaceHolder.lockCanvas()
                surfaceHolder.unlockCanvasAndPost(canvas)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                boardViewModel?.getBoardSize()?.let { boardSize ->
                    val canvas = holder.lockCanvas()

                    blockSize = SizeF(
                        (width / boardSize.width).toFloat(),
                        (height / boardSize.height).toFloat()
                    )
                    holder.unlockCanvasAndPost(canvas)
                }
            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
            }
        } )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let { _canvas ->
            _canvas.drawPaint(backgroundPaint)
            blockSize?.let { bSize ->
                boardViewModel?.getSprites()?.forEach { sprite ->
                    drawSprite(canvas, bSize, sprite)
                }
                boardViewModel?.getControlSprites()?.forEach { sprite ->
                    drawSprite(canvas, bSize, sprite)
                }
            }
        }
    }
    private fun drawSprite(canvas: Canvas, bSize: SizeF, sprite: ISprite) {
        val pos = sprite.getPos()
        val icon = sprite.getIcon()

        canvas.drawText(
            icon,
            pos.x * bSize.width,
            pos.y * bSize.height,
            paint)
    }
}


