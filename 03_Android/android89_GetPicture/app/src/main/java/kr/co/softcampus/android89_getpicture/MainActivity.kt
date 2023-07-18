package kr.co.softcampus.android89_getpicture

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import kr.co.softcampus.android89_getpicture.databinding.ActivityMainBinding
import java.io.File

// 1. xml 폴더에 사진을 촬영하면 이미지 파일이 저장될 경로를 등록한다.
// xml/file_path.xml
// 2. AndroidManifest.xml 에 사진 촬영을 위한 프로바이더를 등록해준다.

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var cameraLauncher:ActivityResultLauncher<Intent>

    // 이미지가 저장될 위치
    lateinit var filePath:String
    // 저장된 파일에 접근하기 위한 Uri
    lateinit var contentUri:Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // 애플리케이션을 위한 외부 저장소 경로를 가져온다.
        // xml/file_path.xml 에 등록한 경로를 가져온다.
        filePath = getExternalFilesDir(null).toString()

        // 사진 촬영을 위한 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()

        // 아래의 코드는 썸네일 가져온다.
//        cameraLauncher = registerForActivityResult(contract1){
//            // 사진을 촬영하고 촬영한 사진을 선택하고 돌아 왔을 경우
//            if(it?.resultCode == RESULT_OK){
//                // Intent로 부터 사진 데이터를 가져온다.
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    val bitmap = it.data?.getParcelableExtra("data", Bitmap::class.java)!!
//                    activityMainBinding.imageView.setImageBitmap(bitmap)
//                } else {
//                    val bitmap = it.data?.getParcelableExtra<Bitmap>("data")!!
//                    activityMainBinding.imageView.setImageBitmap(bitmap)
//                }
//            }
//        }

        cameraLauncher = registerForActivityResult(contract1){
            if(it?.resultCode == RESULT_OK){
                // Uri를 이용해 이미지에 접근하여 Bitmap 객체로 생성한다.
                val bitmap = BitmapFactory.decodeFile(contentUri.path)

                // 이미지의 크기를 조정한다.
                // 이미지의 축소/ 확대 비율을 구한다.
                val ratio = 1024.0 / bitmap.width
                // 세로 길이를 구한다.
                val targetHeight = (bitmap.height * ratio).toInt()

                // 크기를 조정한 Bitmap을 생성한다.
                val bitmap2 = Bitmap.createScaledBitmap(bitmap, 1024, targetHeight, false)


                // 회전 각도를 가져온다.
                val degree = getDegree(contentUri)

                // 회전 이미지를 생성하기 위한 변환 행렬
                val matrix = Matrix()
                matrix.postRotate(degree.toFloat())

                // 회전 행렬을 적용하여 회전왼 이미지를 생성한다.
                // 원본 이미지, 원본 이미지에서의 X좌표, 원본 이미지에서의 Y좌표, 원본 이미지에서의 가로길이,
                // 원본 이미지에서의 세로길이, 변환행렬, 필터정보
                // 원본 이미지에서 지정된 x, y 좌표를 찍고 지정된 가로 세로 길이만큼의 이미지 데이터를 가져와
                // 변환 행렬을 적용하여 이미지를 변환한다.
                val bitmap3 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.width, bitmap2.height, matrix, false)


                activityMainBinding.imageView.setImageBitmap(bitmap3)

                // 이미지 파일은 삭제한다.
                val file = File(contentUri.path)
                file.delete()
            }
        }

        activityMainBinding.run{
            // 아래는 썸네일을 가져온다.
//            button.setOnClickListener {
//                // 사진 촬영을 위한 Activity를 실행한다.
//                val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                cameraLauncher.launch(newIntent)
//            }

            button.setOnClickListener {
                val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                // 촬영될 사진이 저장될 파일 이름
                val now = System.currentTimeMillis()
                val fileName = "/temp_${now}.jpg"
                // 경로 + 파일 이름
                val picPath = "${filePath}/${fileName}"

                val file = File(picPath)

                // 사진이 저장될 경로를 관리할 Uri 객체를 생성한다.
                contentUri = FileProvider.getUriForFile(this@MainActivity,
                    "com.test.getpicture.file_provider", file)

                newIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
                cameraLauncher.launch(newIntent)
            }

        }

        setContentView(activityMainBinding.root)
    }

    // 이미지 파일에 기록되어 있는 회전 정보를 가져온다.
    fun getDegree(uri:Uri) : Int{

        var exifInterface:ExifInterface? = null

        // 사진 파일로 부터 tag 정보를 관리하는 객체를 추출한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val photoUri = MediaStore.setRequireOriginal(uri)
            // 스트림을 추출한다.
            val inputStream = contentResolver.openInputStream(photoUri)
            // ExifInterface 정보를 읽엉돈다.
            exifInterface = ExifInterface(inputStream!!)
        } else {
            exifInterface = ExifInterface(uri.path!!)
        }

        var degree = 0
        if(exifInterface != null){
            // 각도 값을 가지고온다.
            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)

            when(orientation){
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        }
        return degree
    }
}







