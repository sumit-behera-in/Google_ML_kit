package `in`.apps.sumit.mlapp.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.apps.sumit.mlapp.constants.Constants
import `in`.apps.sumit.mlapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA),Constants.MY_CAMERA_REQUEST_CODE)
        }

        binding.imageHelper.setOnClickListener {
            val intent = Intent(this, ImageHelperActivity::class.java)
            startActivity(intent)
        }
    }

}