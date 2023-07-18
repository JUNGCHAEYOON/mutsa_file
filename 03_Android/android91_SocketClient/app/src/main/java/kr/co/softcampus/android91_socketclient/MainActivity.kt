package kr.co.softcampus.android91_socketclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android91_socketclient.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val SERVER_IP = "192.168.200.153"
    val SERVER_PORT = 55555

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                // 안드로이드는 모든 네트워크 코드는 쓰래드로 운영해야 한다.
                // 네트워크 특히 모바일은 통신에 문제가 발생할 가능성이 높기 때문에
                // 오류 발생시 안드로이드 애플리케이션 자체가 종료되는 것을 예방하기 위함이다.
                thread {
                    // 소켓 객체를 생성한다.
                    val socket = Socket(SERVER_IP, SERVER_PORT)

                    // 스트림을 추출한다.
                    val inputStream = socket.getInputStream()
                    val outputStream = socket.getOutputStream()

                    val dataInputStream = DataInputStream(inputStream)
                    val dataOutputStream = DataOutputStream(outputStream)
                    
                    // 서버로 부터 데이터를 수신 받는다.
                    val data1 = dataInputStream.readInt()
                    val data2 = dataInputStream.readDouble()
                    val data3 = dataInputStream.readBoolean()
                    val data4 = dataInputStream.readUTF()

                    // 서버에게 데이터를 전달한다.
                    dataOutputStream.writeInt(200)
                    dataOutputStream.writeDouble(22.22)
                    dataOutputStream.writeBoolean(false)
                    dataOutputStream.writeUTF("클라이언트가 보낸 문자열")

                    runOnUiThread {
                        // textView.text = socket.toString()
                        textView.text = "data1 : ${data1}\n"
                        textView.append("data2 : ${data2}\n")
                        textView.append("data3 : ${data3}\n")
                        textView.append("data4 : $data4")
                    }

                    socket.close()
                }
            }
        }
    }
}










