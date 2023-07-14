package com.example.punchmachinedbz

import android.media.MediaPlayer
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import android.view.animation.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlinx.coroutines.runBlocking
import android.view.animation.Animation

class MainActivity : AppCompatActivity() {

    fun actualizarpantalla() {

        val random = Random
        imageView1.setImageResource(cardImages1[random.nextInt(cardImages1.size)])
        imageView2.setImageResource(cardImages2[random.nextInt(cardImages2.size)])
        imageView3.setImageResource(cardImages3[random.nextInt(cardImages3.size)])




        runBlocking {
            delay(3600) // Delay for 1 second

        }

        val animation: Animation =
            AlphaAnimation(1f, 0f) //to change visibility from visible to invisible

        animation.duration = 200 //1 second duration for each animation cycle
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = 6 //repeating indefinitely
        animation.repeatMode = Animation.REVERSE //animation will start from end point once ended.
        animation.fillBefore= true
        animation.fillAfter= true


        imageView1.startAnimation(animation) //to start animation
        imageView2.startAnimation(animation) //to start animation
        imageView3.startAnimation(animation) //to start animation


    }


    lateinit var imageView1: ImageView
    lateinit var imageView2: ImageView
    lateinit var imageView3: ImageView
    lateinit var button: Button
    lateinit var text: EditText


    val cardImages1: IntArray = intArrayOf(
        R.drawable.empty,
        R.drawable.one,
        R.drawable.two,
        R.drawable.three,
        R.drawable.four
        //R.drawable.five,
        //R.drawable.six,
        //R.drawable.seven,
        //R.drawable.eight,
        //R.drawable.nine,
    )
    val cardImages2: IntArray = intArrayOf(
        //R.drawable.zero,
        R.drawable.one,
        R.drawable.two,
        R.drawable.three,
        R.drawable.four,
        R.drawable.five,
        R.drawable.six,
        R.drawable.seven,
        R.drawable.eight,
        R.drawable.nine
    )

    val cardImages3: IntArray = intArrayOf(
        R.drawable.zero,
        R.drawable.one,
        R.drawable.two,
        R.drawable.three,
        R.drawable.four,
        R.drawable.five,
        R.drawable.six,
        R.drawable.seven,
        R.drawable.eight,
        R.drawable.nine
    )

    val fd by lazy {
        assets.openFd("todo.mp3")
    }

    val mp by lazy {
        val m = MediaPlayer()
        m.setDataSource(
            fd.fileDescriptor,
            fd.startOffset,
            fd.length
        )
        fd.close()
        m.prepare()

        m

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            mp.start()
            actualizarpantalla()
        }

        button.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                mp.start()
                actualizarpantalla()
                true
            } else {
                false // Return false to allow the event to propagate to other views
            }
        }


    }

}

