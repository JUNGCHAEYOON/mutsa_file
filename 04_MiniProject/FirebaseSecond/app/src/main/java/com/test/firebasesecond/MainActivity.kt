package com.test.firebasesecond

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.test.firebasesecond.databinding.ActivityMainBinding
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    val database = FirebaseDatabase.getInstance()
                    val postDataRef = database.getReference("PostData")
                    postDataRef.orderByChild("postIdx").equalTo(2.0).get().addOnCompleteListener{
                        for(c1 in it.result.children) {
                            // 게시글 제목
                            val postSubject = c1.child("postSubject").value as String
                            // 게시글 내용
                            val postText = c1.child("postText").value as String
                            // 게시글 작성 날짜
                            val postWriteDate = c1.child("postWriteDate").value as String
                            // 이미지 파일 이름
                            val postFileName = c1.child("postImage").value as String

                            val postWriterIdx = c1.child("postWriterIdx").value as Long

                            textView.text = """제목 : $postSubject
                                | 내용 : $postText
                                | 작성날짜 : $postWriteDate
                                | 작성자 번호 : $postWriterIdx
                            """.trimMargin()

                            // 이미지가 있다면
                            if(postFileName != "None"){
                                val storage = FirebaseStorage.getInstance()
                                val fileRef = storage.reference.child(postFileName)

                                // 데이터를 가져올 수 있는 경로를 가져온다.
                                fileRef.downloadUrl.addOnCompleteListener{
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
                        }
                    }
                }
            }
        }
    }
}