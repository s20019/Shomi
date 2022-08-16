package jp.ac.it_college.std.s20019.expirydatemanager2.activity

import android.os.Bundle
import android.view.Menu
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
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