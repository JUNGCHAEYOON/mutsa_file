package com.test.firebasestorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.test.firebasestorage.databinding.ActivityMainBinding
import android.Manifest
import android.graphics.BitmapFactory
import android.provider.MediaStore
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.storage.FirebaseStorage
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 확인할 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION,
        Manifest.permission.INTERNET
    )

    val fileName = "image/test.jpg"

    // 앨범 액티비티를 실행하기 위한 런처
    lateinit var albumLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList,0)

        // 런처 생성
        val contract1 = ActivityResultContracts.StartActivityForResult()
        albumLauncher = registerForActivityResult(contract1){
            if(it?.resultCode == RESULT_OK){
                // firebase storage에 접근한다.
                val storage = FirebaseStorage.getInstance()
                // 나중에 프로젝트에 적용할 때 파일이름은 이런식으로 해주세요
                // 이 파일이름은 데이터베이스에 꼭 저장해주세요
                // val fileName = "image/test_${System.currentTimeMillis()}.jpg"
                // 파일에 접근할 수 있는 객체를 가져온다.
                val fileRef = storage.reference.child(fileName)
                // 파일을 업로드한다.
                fileRef.putFile(it.data?.data!!).addOnCompleteListener{
                    Snackbar.make(activityMainBinding.root, "업로드가 완료되었습니다", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        activityMainBinding.run{
            button.setOnClickListener {
                val newIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                newIntent.setType("image/*")
                val mimeType = arrayOf("image/*")
                newIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
                albumLauncher.launch(newIntent)
            }

            button2.setOnClickListener {
                val storage = FirebaseStorage.getInstance()
                val fileRef = storage.reference.child(fileName)

                // 데이터를 가져올 수 있는 경로를 가져온다.
                fileRef.downloadUrl.addOnCompleteListener {
                    thread {
                        // 파일에 접근할 수 있는 경로를 이용해 URL 객체를 생성한다.
                        val url = URL(it.result.toString())
                        // 접속한다.
                        val httpURLConnection = url.openConnection() as HttpURLConnection
                        // 이미지 객체를 생성한다.
                        val bitmap = BitmapFactory.decodeStream(httpURLConnection.inputStream)

                        runOnUiThread {
                            imageView.setImageBitmap(bitmap)
                        }
                    }
                }
            }

            button3.setOnClickListener {
                // 이미지 수정은 이미지 업로드와 동일하다.
                // 다른 이미지를 같은 파일 명으로 업로드하면 덮어 씌워진다.
                val newIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                newIntent.setType("image/*")
                val mimeType = arrayOf("image/*")
                newIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
                albumLauncher.launch(newIntent)
            }

            button4.setOnClickListener {
                val storage = FirebaseStorage.getInstance()
                val fileRef = storage.reference.child(fileName)
                // 파일을 삭제한다.
                fileRef.delete().addOnCompleteListener {
                    Snackbar.make(activityMainBinding.root, "삭제되었습니다", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}