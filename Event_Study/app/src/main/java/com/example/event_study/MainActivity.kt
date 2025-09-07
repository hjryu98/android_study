package com.example.event_study

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 뒤로 가기 콜백
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("UI", "뒤로가기 눌림 - 내가 직접 처리")
                }
            })
    }

    // touch 발생 event
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_DOWN -> { // touch 하자마자
                Log.d("Notice","Touch down event in x:${event.x}, rawX:${event.rawX}") // debug용 log, (tag, message) param. 두개 넘겨줘야 함
            }


            MotionEvent.ACTION_UP -> { // touch 이후 뗐을 때
                Log.d("Notice", "Touch up event")
            }
        }

        return super.onTouchEvent(event)
    }

    // key를 누른 순간의 event -> 어떤 KEY를 눌렀는지 식별하고 처리
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode){
            KeyEvent.KEYCODE_0 -> Log.d("Notice", "key-0 pressed")
            KeyEvent.KEYCODE_1 -> Log.d("Notice", "key-1 pressed")
            KeyEvent.KEYCODE_A -> Log.d("Notice", "key-A pressed")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("Notice", "Volume up")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("Notice", "Volume down")
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("Notice", "onKeyUp")
        return super.onKeyDown(keyCode, event)
    }
}