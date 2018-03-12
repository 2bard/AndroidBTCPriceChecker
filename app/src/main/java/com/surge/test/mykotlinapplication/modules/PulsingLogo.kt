package com.surge.test.mykotlinapplication.modules

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.surge.test.mykotlinapplication.R
import timber.log.Timber


/**
 * Created by Lewis on 20/02/2018.
 */
class PulsingLogo : View, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

    val logoPaint = Paint()
    val pulsePaint = Paint()

    lateinit var valueAnimator: ValueAnimator

    var logo: Bitmap
    var scaledLogo: Bitmap? = null

    var diameterSize: Float? = null
    var isAnimating = false
    var padding = 20f
    var canvasWidth: Int? = null
    var canvasHeight: Int? = null

    var bitmapXpos: Float? = null
    var bitmapYpos: Float? = null
    var x: Float? = null
    var y: Float? = null

    init {
        logoPaint.style = Paint.Style.STROKE
        logoPaint.color = ContextCompat.getColor(context, R.color.colorBtc)
        pulsePaint.color = ContextCompat.getColor(context, R.color.colorBtc)
        pulsePaint.style = Paint.Style.STROKE
        logo = BitmapFactory.decodeResource(resources, R.drawable.btc_logo)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        attributeSet?.let {
            val typedArray = context.obtainStyledAttributes(it,
                    R.styleable.pulsing_logo, 0, 0)
            padding = typedArray.getDimension(R.styleable.pulsing_logo_pulse_padding, 20f)
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            drawLogo(canvas)
            drawPulse(canvas)
        }
    }

    fun drawPulse(canvas: Canvas) {
        diameterSize?.let{
            canvas.drawCircle(x!!, y!!, (((diameterSize!!))/2), pulsePaint)
        }
    }

    fun drawLogo(canvas: Canvas){

        if(scaledLogo == null){
            canvasWidth = canvas.width
            canvasHeight = canvas.height
            x = (canvas.width/2).toFloat()
            y = (canvas.height/2).toFloat()

            val logoDiameter = if (canvas.height > canvas.width) { canvas.width.toFloat() } else { canvas.height.toFloat() }
            val scaledRadiusSize =  (logoDiameter - padding).toInt()

            scaledLogo = Bitmap.createScaledBitmap(
                    logo,
                    scaledRadiusSize,
                    scaledRadiusSize,
                    false)

            bitmapXpos = (x!! - ((logoDiameter - padding)/2))
            bitmapYpos = (y!! - ((logoDiameter - padding)/2))
        }

        canvas.drawBitmap(scaledLogo,
                bitmapXpos!!,
                bitmapYpos!!,
                logoPaint)
    }

    override fun onAnimationUpdate(p0: ValueAnimator?) {
        val multiplier = p0?.animatedFraction
        pulsePaint.alpha = 255 - (multiplier!!.times(255).toInt())
        diameterSize = (p0.animatedValue as Float)
        invalidate()
    }

    fun startAnimating(){

        if(isAnimating) { return }

        canvasHeight?.let {
            valueAnimator = ValueAnimator.ofFloat((canvasWidth!! - padding), canvasWidth!!.toFloat())
            valueAnimator.addListener(this)
            valueAnimator.addUpdateListener( this)
            valueAnimator.duration = 1000L
            valueAnimator.interpolator = AccelerateInterpolator()
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