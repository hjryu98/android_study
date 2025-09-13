package com.example.ch8_event

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 뒤로 가기 버튼 누른 시각을 저장하는 attribute
    var initTime = 0L

    // 멈춘 시각을 저장하는 attibute
    var pauseTime = 0L

    // 마지막으로 눌렸던 시각 저장 (뒤로가기 -> 현대식으로 재구현)
    private var lastBackPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로가기 콜백 등록 (권장 방식)
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val now = System.currentTimeMillis()
                    if (now - lastBackPressed > 3000) {
                        Toast.makeText(
                            this@MainActivity,
                            "종료하려면 한 번 더 누르세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                        lastBackPressed = now
                    } else {
                        // 두 번째 눌림: 실제 뒤로가기 수행
                        // 1) 콜백 비활성화 후 시스템에 위임
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()

                        // 또는 2) 앱을 직접 종료/백그라운드로
                        // finish()
                        // moveTaskToBack(true)
                    }
                }
            }
        )

        // 버튼 event 구현 (Event Lister와 핸들러 연결)
        binding.startButton.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()

            // 버튼 표시 여부 수정
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
            binding.startButton.isEnabled = false
        }

        binding.stopButton.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            // 버튼 표시 여부 수정
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true
            binding.startButton.isEnabled = true
        }

        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            // 버튼 표시 여부 수정
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false
            binding.startButton.isEnabled = true
        }
    }

                // 뒤로 나가기 구현
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            if(System.currentTimeMillis() - initTime > 3000){
//                Toast.makeText(this, "종료하려면 한 번 더 누르세요.", Toast.LENGTH_SHORT).show()
//                initTime = System.currentTimeMillis()
//                return true
//            }
//        }
//        return super.onKeyDown(keyCode, event)
//    }
}

