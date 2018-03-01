package com.surge.test.mykotlinapplication.modules

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.surge.test.mykotlinapplication.R


/**
 * Created by Lewis on 20/02/2018.
 */
class PulsingLogo : View, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

    val paint = Paint()
    lateinit var valueAnimator: ValueAnimator
    var canvasHeight: Int? = null
    var canvasWidth: Int? = null
    var radiusSize: Float? = null
    var isAnimating = false

    init {
        paint.style = Paint.Style.FILL
        paint.color = ContextCompat.getColor(context, R.color.colorBtc)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    override fun onDraw(canvas: Canvas?) {
        canvas?.let { drawPulse(canvas) }
    }

    fun drawPulse(canvas: Canvas) {
        canvasHeight = canvas.height
        canvasWidth = canvas.width

        radiusSize?.let{
            val x = (canvas.width/2).toFloat()
            val y = (canvas.height/2).toFloat()
            canvas.drawCircle(x, y, radiusSize!!, paint)
        }
    }

    override fun onAnimationUpdate(p0: ValueAnimator?) {
        val multiplier = p0?.animatedFraction
        paint.alpha = 255 - (multiplier!!.times(255).toInt())
        radiusSize = (multiplier.times(canvasWidth!!))/2
        invalidate()
    }

    fun startAnimating(){
        if(isAnimating) { return }

        canvasHeight?.let {
            valueAnimator = ValueAnimator.ofInt(canvasHeight!!)
            valueAnimator.addListener(this)
            valueAnimator.addUpdateListener( this)
            valueAnimator.duration = 1000L
            valueAnimator.start()
            isAnimating = true
        }

    }

    override fun onAnimationEnd(p0: Animator?) {
        isAnimating = false
    }

    override fun onAnimationCancel(p0: Animator?) {
        isAnimating = false
    }

    override fun onAnimationStart(p0: Animator?) {}
    override fun onAnimationRepeat(p0: Animator?) {}
}