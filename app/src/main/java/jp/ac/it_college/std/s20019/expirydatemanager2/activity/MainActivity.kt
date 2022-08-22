package jp.ac.it_college.std.s20019.expirydatemanager2.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import jp.ac.it_college.std.s20019.expirydatemanager2.R
import jp.ac.it_college.std.s20019.expirydatemanager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 常にダークテーマをOFFにする処理
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        val username = intent.getStringExtra("USER_NAME")
        Toast.makeText(applicationContext, "ようこそ ${username}さん", Toast.LENGTH_SHORT).show()

        binding.addBtn.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }
    }

    // アプリバーにメニューアイコンを表示する処理
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    // それぞれのメニューがタップされた時の処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // 「カスタマイズ」がタップされた時
            R.id.action_customize -> {
                // カスタマイズモード中にアクションバーの色を変える処理
                supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#2196F3")))

                // アクションバーの色の変更に伴い、ステータスバーの色をアクションバーに合わせる処理
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.parseColor("#2196F3")

                binding.imgDeleteBtn1.visibility = View.VISIBLE
                binding.imgDeleteBtn2.visibility = View.VISIBLE
                binding.imgDeleteBtn3.visibility = View.VISIBLE
                binding.imgDeleteBtn4.visibility = View.VISIBLE
                binding.imgDeleteBtn5.visibility = View.VISIBLE
                true
            }
            // 「終了」がタップされた時
            R.id.action_end -> {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF018786")))
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.parseColor("#FF018786")

                binding.imgDeleteBtn1.visibility = View.INVISIBLE
                binding.imgDeleteBtn2.visibility = View.INVISIBLE
                binding.imgDeleteBtn3.visibility = View.INVISIBLE
                binding.imgDeleteBtn4.visibility = View.INVISIBLE
                binding.imgDeleteBtn5.visibility = View.INVISIBLE
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // メニュー項目を表示する前の調整
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        // 削除ボタンが表示されている場合は「終了」項目のみ表示
        if (binding.imgDeleteBtn1.visibility == View.VISIBLE) {
            menu?.findItem(R.id.action_customize)?.isVisible = false
            menu?.findItem(R.id.action_profile)?.isVisible = false
            menu?.findItem(R.id.action_friend)?.isVisible = false
            menu?.findItem(R.id.action_settings)?.isVisible = false

            menu?.findItem(R.id.action_end)?.isVisible = true
        }
        // 削除ボタンが表示されていなければデフォルトの項目を表示
        else {
            menu?.findItem(R.id.action_customize)?.isVisible = true
            menu?.findItem(R.id.action_profile)?.isVisible = true
            menu?.findItem(R.id.action_friend)?.isVisible = true
            menu?.findItem(R.id.action_settings)?.isVisible = true

            menu?.findItem(R.id.action_end)?.isVisible = false
        }
        return super.onPrepareOptionsMenu(menu)
    }
}