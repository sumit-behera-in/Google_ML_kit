package `in`.apps.sumit.mlapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.apps.sumit.mlapp.constants.REQUEST_PICK_IMAGE
import `in`.apps.sumit.mlapp.databinding.ActivityImageHelperBinding

class ImageHelperActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImageHelperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageHelperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pickImage.setOnClickListener {
            onPickImage()
        }
    }

    fun onPickImage(){
        var intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "images/*"
        startActivityForResult(intent, REQUEST_PICK_IMAGE)
    }

    fun onStartCamera(){

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK ){
            if( requestCode == REQUEST_PICK_IMAGE){
                val uri: Uri? = data?.data
                val bitmap :Bitmap? = loadFromUri(uri)
                if(bitmap!=null) binding.imageViewInput.setImageBitmap(bitmap)

            }

        }
    }

    private fun loadFromUri(uri: Uri?) : Bitmap? {
        var bitmap:Bitmap?=null
        val source = uri?.let { ImageDecoder.createSource(contentResolver, it) }
        bitmap = source?.let { ImageDecoder.decodeBitmap(it) }

        return bitmap
    }
}