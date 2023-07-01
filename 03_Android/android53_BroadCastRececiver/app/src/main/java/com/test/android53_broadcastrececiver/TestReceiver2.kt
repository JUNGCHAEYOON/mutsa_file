package com.test.android53_broadcastrececiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast

class TestReceiver2 : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // BR를 등록했을 때 사용한 이름을 가져온다.
        when(intent.action){
            // 부팅완료
            "android.intent.action.BOOT_COMPLETED" -> {
                Toast.makeText(context, "부팅 완료", Toast.LENGTH_SHORT).show()
            }
            // 문자 수신
            "android.provider.Telephony.SMS_RECEIVED" -> {
                // 수신 문자를 가지고 있는 객체를 추출한다
                if(intent.extras != null){
                    // 문자 메시지 정보 객체를 추출한다.
                    val objs = intent.extras?.get("pdus") as Array<Any?>
                    if(objs != null){
                        // 추출한 메시지 수 만큼 반복한다.
                        for(obj in objs){
                            // 문자 메시지 객체를 추출한다.
                            val format = intent.extras?.getString("format")
                            // 문자 메시지 객체를 생성한다.
                            val currentSMS = SmsMessage.createFromPdu(obj as ByteArray?, format)

                            val str1 = """전화번호 : ${currentSMS.displayOriginatingAddress}
                                | 내용 : ${currentSMS.displayMessageBody}
                            """.trimMargin()

                            Toast.makeText(context, str1, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}