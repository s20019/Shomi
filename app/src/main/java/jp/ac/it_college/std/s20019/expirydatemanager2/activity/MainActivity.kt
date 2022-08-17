package jp.ac.it_college.std.s20019.expirydatemanager2.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import jp.ac.it_college.std.s20019.expirydatemanager2.MyWorker
import jp.ac.it_college.std.s20019.expirydatemanager2.R
import jp.ac.it_college.std.s20019.expirydatemanager2.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val manager = WorkManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 常にダークテーマをOFFにする処理
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        notification()

    }

    // アプリバーにメニューアイコンを表示する処理
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    // それぞれのメニューがタップされた時の処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_customize -> {
                binding.imgDeleteBtn1.visibility = View.VISIBLE
                binding.imgDeleteBtn2.visibility = View.VISIBLE
                binding.imgDeleteBtn3.visibility = View.VISIBLE
                binding.imgDeleteBtn4.visibility = View.VISIBLE
                binding.imgDeleteBtn5.visibility = View.VISIBLE
                true
            }
            R.id.action_end -> {
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


    private fun notification() {
        val data = Data.Builder().build()

        if (manager.getWorkInfosByTag("FLAG").get().size == 0) {
            val saveRequest = PeriodicWorkRequestBuilder<MyWorker>(
                15, TimeUnit.MINUTES,
                14, TimeUnit.MINUTES
            )
                .addTag("FLAG")
                .setInputData(data)
                .build()
            manager.enqueue(saveRequest)
        }
    }
}