package jp.ac.it_college.std.s20019.expirydatemanager2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import jp.ac.it_college.std.s20019.expirydatemanager2.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 常にダークテーマをOFFにする処理
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // 「今すぐ登録」を押したときの処理
        binding.registerNowText.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.logInBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_NAME", binding.userName.text.toString())
            startActivity(intent)
        }
    }

    // 文字列が修正されたあと二呼び出されるメソッド
    override fun afterTextChanged(p0: Editable?) { }

    // 文字列が修正される直前に呼び出されるメソッド
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

    // 文字１つを入力したときに呼び出されるメソッド
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        binding.personImg.visibility = View.GONE
        binding.keyImg.visibility = View.GONE
    }
}