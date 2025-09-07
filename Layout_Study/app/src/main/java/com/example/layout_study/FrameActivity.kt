package com.example.layout_study

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.layout_study.databinding.FramelayoutMainBinding

// view binding 구현 필요.
class FrameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = FramelayoutMainBinding.inflate(layoutInflater) // view binding
        setContentView(binding.root)

        // setOnClickListener: 특정 객체가 click 되었을 때 수행할 내용을 구현
        binding.button1.setOnClickListener{
            binding.button1.visibility = View.INVISIBLE // 버튼은 숨긴다.
            binding.imageAndroid.visibility = View.VISIBLE // 이미지는 보이게 변경.
        }

        binding.imageAndroid.setOnClickListener{
            binding.button1.visibility = View.VISIBLE // 버튼은 보이게 변경.
            binding.imageAndroid.visibility = View.INVISIBLE // 이미지는 숨기게 변경.
        }

    }
}