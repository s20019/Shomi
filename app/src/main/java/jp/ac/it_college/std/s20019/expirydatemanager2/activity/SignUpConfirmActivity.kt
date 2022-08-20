package jp.ac.it_college.std.s20019.expirydatemanager2.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast import jp.ac.it_college.std.s20019.expirydatemanager2.databinding.ActivitySignUpConfirmBinding

class SignUpConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // サインアップ画面で入力されたユーザーネームを表示する処理
        val username = intent.getStringExtra("USER_NAME")
        binding.userNameSignUpConfirm.text = username

        binding.okBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_NAME", binding.userNameSignUpConfirm.text.toString())
            startActivity(intent)
        }

        binding.copyImg.setOnClickListener {
            // クリップボードにコピーする処理
            val clipboard: ClipboardManager = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", binding.userId.text)
            clipboard.setPrimaryClip(clip)

            // コピーしたことを知らせる表示
            Toast.makeText(applicationContext, "コピーしました", Toast.LENGTH_SHORT).show()
        }
    }
}