package com.test.android68_filestream

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android68_filestream.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var writeActivityLauncher:ActivityResultLauncher<Intent>
    lateinit var readActivityLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 쓰기용 런처
        val contracts1 = ActivityResultContracts.StartActivityForResult()
        writeActivityLauncher = registerForActivityResult(contracts1){
            // 사용자가 저장할 파일을 선택하고 돌아오면 ResultCode는 RESULT_OK가 들어온다.
            if(it.resultCode == RESULT_OK){
                // 사용자가 선택한 파일의 정보를 가지고 있는 Intent로 파일 정보를 가져온다.
                if(it.data != null){
                    // 저장할 파일에 접근할 수 있는 객체로 부터 파일 정보를 가져온다
                    // w : 쓰기, a : 이어쓰기, r : 읽기
                    val des1 = contentResolver?.openFileDescriptor(it.data?.data!!, "w")
                    // 스트림을 생성한다.
                    val fos = FileOutputStream(des1?.fileDescriptor)
                    val dos = DataOutputStream(fos)
                    dos.writeInt(300)
                    dos.writeDouble(33.33)
                    dos.writeBoolean(true)
                    dos.writeUTF("문자열3")
                    
                    dos.flush()
                    dos.close()
                    
                    activityMainBinding.textView.text = "Downloads 폴더에 저장"
                }
            }
        }

        // 읽기용 런처
        val contracts2 = ActivityResultContracts.StartActivityForResult()
        readActivityLauncher = registerForActivityResult(contracts2){
            // RESULT_OK 일 때만..
            if(it.resultCode == RESULT_OK){
                // 가져온 데이터가 있을 경우에만..
                if(it.data != null){
                    // 선택한 파일의 경로 정보를 가져온다.
                    val dest1 = contentResolver.openFileDescriptor(it.data?.data!!, "r")

                    val fis = FileInputStream(dest1?.fileDescriptor)
                    val dis = DataInputStream(fis)

                    val data1 = dis.readInt()
                    val data2 = dis.readDouble()
                    val data3 = dis.readBoolean()
                    val data4 = dis.readUTF()

                    dis.close()

                    activityMainBinding.textView.text = "data1 : ${data1}\n"
                    activityMainBinding.textView.append("data2 : ${data2}\n")
                    activityMainBinding.textView.append("data3 : ${data3}\n")
                    activityMainBinding.textView.append("data4 : $data4")
                }
            }
        }

        activityMainBinding.run{
            // 내부 저장소
            // 파일을 저장한 애플리케이션만 사용이 가능하다.
            button.setOnClickListener {
                // MODE_PRIVATE : 덮어 씌우기
                // MODE_APPEND : 이어서 쓰기
                val fos = openFileOutput("data1.dat", MODE_PRIVATE)
                val dos = DataOutputStream(fos)
                dos.writeInt(100)
                dos.writeDouble(11.11)
                dos.writeBoolean(true)
                dos.writeUTF("문자열1")

                dos.flush()
                dos.close()

                textView.text = "내부 저장소 쓰기 완료"
            }

            button2.setOnClickListener {
                val fis = openFileInput("data1.dat")
                val dis = DataInputStream(fis)

                val data1 = dis.readInt()
                val data2 = dis.readDouble()
                val data3 = dis.readBoolean()
                val data4 = dis.readUTF()

                dis.close()
                fis.close()

                textView.text = "data1 : ${data1}\n"
                textView.append("data2 : ${data2}\n")
                textView.append("data3 : ${data3}\n")
                textView.append("data4 : $data4")
            }

            // 외부 저장소
            // Android/data 폴더에 저장된다.
            // 이 곳에 저장된 파일은 다른 애플리케이션이 접근할 수 없으며
            // 애플리케이션을 삭제하면 같이 삭제된다.

            // 1. AndroidManfest.xml에 저장경로를 등록해준다.
            // Activity 태그 안에 meta-data로 등록한다.

            button3.setOnClickListener {
                // 외부 저정소의 경로를 가져온다.
                // emulated/Android/data/패키지명/files
                // getExternalFilesDir 메서드의 매개변수에는 문자열을 넣어줄 수 있으며
                // files의 하위 폴더 이름을 넣어서 사용할 수 있다.
                // null을 넣으면 files까지의 경로가 된다.
                val filePath = getExternalFilesDir(null).toString()

                val fos = FileOutputStream("${filePath}/data2.dat")
                val dos = DataOutputStream(fos)


                dos.writeInt(200)
                dos.writeDouble(22.22)
                dos.writeBoolean(false)
                dos.writeUTF("문자열2")
                
                dos.flush()
                dos.close()
                fos.close()
                
                textView.text = "외부 저장소 앱 데이터 폴더에 저장"
            }

            button4.setOnClickListener {
                val filePath = getExternalFilesDir(null).toString()
                val fis = FileInputStream("${filePath}/data2.dat")
                val dis = DataInputStream(fis)

                val data1 = dis.readInt()
                val data2 = dis.readDouble()
                val data3 = dis.readBoolean()
                val data4 = dis.readUTF()

                dis.close()
                fis.close()

                textView.text = "data1 : ${data1}\n"
                textView.append("data2 : ${data2}\n")
                textView.append("data3 : ${data3}\n")
                textView.append("data4 : $data4")
            }

            // FileApp을 통한 접근
            button5.setOnClickListener {
                // 파일 관리 앱의 액티비티를 실행한다.
                val fileIntent = Intent(Intent.ACTION_CREATE_DOCUMENT)
                fileIntent.addCategory(Intent.CATEGORY_OPENABLE)
                // MimeType을 설정한다.
                // MimeType이란? 파일에 저장되어 있는 데이터의 양식이 무엇인지를 타나내는 문자열
                // https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types
                fileIntent.type = "*/*"
                writeActivityLauncher.launch(fileIntent)
            }

            button6.setOnClickListener {
                // 파일 관리 앱의 액티비티를 실행한다.
                val fileIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                fileIntent.type = "*/*"
                readActivityLauncher.launch(fileIntent)
            }
        }
    }
}








