package `in`.apps.sumit.mlapp.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import `in`.apps.sumit.mlapp.R
import `in`.apps.sumit.mlapp.constants.Constants
import `in`.apps.sumit.mlapp.databinding.ActivityImageHelperBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class ImageHelperActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImageHelperBinding
    private lateinit var imageLabeler :ImageLabeler
    private lateinit var photoFile: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageHelperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pickImage.setOnClickListener {
            binding.imageViewInput.setImageResource(R.drawable.demophoto)
            binding.textViewOutput.text = "Output"
            onPickImage()
        }

        binding.startCamera.setOnClickListener {
            binding.imageViewInput.setImageResource(R.drawable.demophoto)
            binding.textViewOutput.text = "Output"
            onStartCamera()
        }

        imageLabeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)


    }

    fun onPickImage(){
        val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "images/*"
        startActivityForResult(intent, Constants.REQUEST_PICK_IMAGE_CODE)
    }

    //first checks the photo-file then gets the uri
    fun onStartCamera(){
        photoFile = createPhotoFile()

        //same authority given in manifest.xml
        val uri = FileProvider.getUriForFile(this,"in.apps.sumit.mlApp.fileProvider",photoFile)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
        startActivityForResult(intent,Constants.REQUEST_CAMERA_CAPTURE_IMAGE_CODE)
    }

    private fun createPhotoFile(): File {
        val photoFileDir = File(getExternalFilesDir(), "Ml_IMAGE_HELPER")

        if (!photoFileDir.exists()) photoFileDir.mkdirs()

        val name: String = SimpleDateFormat("YYYYMMDD_HHMMSS").format(Date())
        return File(photoFileDir.path + File.separator + name)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK ){
            if( requestCode == Constants.REQUEST_PICK_IMAGE_CODE){
                val uri: Uri? = data?.data
                val bitmap :Bitmap? = loadFromUri(uri)
                if(bitmap!=null) {
                    binding.imageViewInput.setImageBitmap(bitmap)
                    runClassification(bitmap)
                }

            }
            else if (requestCode == Constants.REQUEST_CAMERA_CAPTURE_IMAGE_CODE){
                val bitmap :Bitmap? = BitmapFactory.decodeFile(photoFile.absolutePath)
                if(bitmap!=null) {
                    binding.imageViewInput.setImageBitmap(bitmap)
                    runClassification(bitmap)
                }
            }

        }else{
            Toast.makeText(this@ImageHelperActivity,"Error",Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadFromUri(uri: Uri?) : Bitmap? {
        var bitmap:Bitmap?=null
        try {
            val source = uri?.let { ImageDecoder.createSource(contentResolver, it) }
            bitmap = source?.let { ImageDecoder.decodeBitmap(it) }
        }catch (_:Exception){
            binding.textViewOutput.text= Constants.UNSUPPORTED_TEXT_FORMAT
            Toast.makeText(this,Constants.UNSUPPORTED_TEXT_FORMAT,Toast.LENGTH_SHORT).show()
        }

        return bitmap
    }


    private fun runClassification(bitmap: Bitmap){
        val inputImage:InputImage = InputImage.fromBitmap(bitmap,0)
        imageLabeler.process(inputImage)
            .addOnSuccessListener { labels->
                if(labels.size > 0){
                    var resultLables = String()
                    for (label in labels){
                        resultLables+=label.text + " : " + label.confidence +"\n"
                    }
                    binding.textViewOutput.text = resultLables
                }
                else{
                    binding.textViewOutput.text = Constants.CLASSIFICATION_FAILED
                }
            }
            .addOnFailureListener { labels ->

            }
    }
}