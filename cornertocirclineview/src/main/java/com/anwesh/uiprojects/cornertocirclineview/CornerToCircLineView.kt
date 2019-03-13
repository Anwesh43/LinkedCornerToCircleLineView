package com.anwesh.uiprojects.cornertocirclineview

/**
 * Created by anweshmishra on 14/03/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color
import android.app.Activity
import android.content.Context

val nodes : Int = 5
val lines : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#388E3C")
val backColor : Int = Color.parseColor("#BDBDBD")
val angleDeg : Float = 45f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap
fun Float.crx(deg : Float) : Float = this * Math.cos(deg * Math.PI/180).toFloat()
fun Float.cry(deg : Float) : Float = this * Math.sin(deg * Math.PI/180).toFloat()
fun Float.updateToD(d : Float, sc : Float) : Float = this + (d - this) * sc

fun Canvas.drawCCLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val size : Float = gap / sizeFactor
    val r1 : Float = size
    val r2 : Float = size * Math.sqrt(2.0).toFloat()
    val sx : Float = r1.crx(angleDeg)
    val sy : Float = r1.cry(angleDeg)
    val dx : Float = r2.crx(angleDeg)
    val dy : Float = r2.cry(angleDeg)
    save()
    translate(w / 2, gap * (i + 1))
    rotate(90f * sc2)
    drawRect(RectF(-size, -size, size, size), paint)
    drawCircle(0f, 0f, size, paint)
    for (j in 0..(lines - 1)) {
        val sc : Float = sc1.divideScale(j, lines)
        save()
        rotate(90f * j)
        drawLine(sx, sy, sx.updateToD(dx, sc), sy.updateToD(dy, sc), paint)
        restore()
    }
    restore()
}