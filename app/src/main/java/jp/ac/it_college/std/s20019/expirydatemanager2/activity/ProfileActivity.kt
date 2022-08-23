package jp.ac.it_college.std.s20019.expirydatemanager2.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import jp.ac.it_college.std.s20019.expirydatemanager2.R
import jp.ac.it_college.std.s20019.expirydatemanager2.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // カスタマイズモード中にアクションバーの色を変える処理
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F1BECF")))

        // アクションバーの色の変更に伴い、ステータスバーの色をアクションバーに合わせる処理
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#F1BECF")

        binding.imageView3.setOnClickListener {
            binding.profileImg.setImageResource(R.drawable.fruits)
        }
    }
}