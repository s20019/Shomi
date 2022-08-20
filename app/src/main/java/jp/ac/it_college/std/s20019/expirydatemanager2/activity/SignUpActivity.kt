package jp.ac.it_college.std.s20019.expirydatemanager2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20019.expirydatemanager2.R
import jp.ac.it_college.std.s20019.expirydatemanager2.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moveOnBtn.setOnClickListener {
            val intent = Intent(this, SignUpConfirmActivity::class.java)
            intent.putExtra("USER_NAME", binding.userNameSignUp.text.toString())
            startActivity(intent)
        }
    }
}