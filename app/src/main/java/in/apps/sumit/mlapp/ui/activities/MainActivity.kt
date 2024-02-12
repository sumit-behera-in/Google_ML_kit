package `in`.apps.sumit.mlapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.apps.sumit.mlapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageHelper.setOnClickListener {
            val intent = Intent(this, ImageHelperActivity::class.java)
            startActivity(intent)
        }
    }

}