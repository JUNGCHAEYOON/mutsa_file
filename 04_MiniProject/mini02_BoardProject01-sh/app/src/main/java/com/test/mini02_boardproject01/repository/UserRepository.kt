package com.test.mini02_boardproject01.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.test.mini02_boardproject01.AddUserInfoFragment
import com.test.mini02_boardproject01.UserClass

class UserRepository {
    companion object {
        // 사용자 인덱스 번호를 가져온다.
        fun getUserIdx(callback1: (Task<DataSnapshot>) -> Unit) {
            // 사용자 인덱스 값을 가져온다.
            val database = FirebaseDatabase.getInstance()
            val userIdxRef = database.getReference("UserIdx")

            userIdxRef.get().addOnCompleteListener(callback1)
        }

        // 사용자 인덱스 번호를 설정한다.
        fun setUserIdx(userIdx: Long, callback1: (Task<Void>) -> Unit) {
            // 사용자 인덱스 값을 가져온다.
            val database = FirebaseDatabase.getInstance()
            val userIdxRef = database.getReference("UserIdx")

            userIdxRef.get().addOnCompleteListener {
                it.result.ref.setValue(userIdx).addOnCompleteListener(callback1)
            }
        }

        // 사용자 정보를 저장한다.
        fun addUserInfo(userClass: UserClass, callback1: (Task<Void>) -> Unit) {
            // 저장한다.
            val database = FirebaseDatabase.getInstance()
            val userDataRef = database.getReference("UserData")
            userDataRef.push().setValue(userClass).addOnCompleteListener(callback1)
        }

        // 사용자 아이디를 통해 사용자 정보를 가져온다.
        fun getUserInfoByUserId(loginUserId: String, callback1: (Task<DataSnapshot>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            val userDataRef = database.getReference("UserData")

            // ----- 규칙 등록하는 부분 정리 -----
            // userId가 사용자가 입력한 아이디와 같은 데이터를 가져온다.
            userDataRef.orderByChild("userId").equalTo(loginUserId).get().addOnCompleteListener(callback1)
        }

        // 사용자 인덱스를 통해 사용자 정보를 가져온다.
        fun getUserInfoByUserIdx(userIdx: Long, callback1: (Task<DataSnapshot>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            val userDataRef = database.getReference("UserData")

            userDataRef.orderByChild("userIdx").equalTo(userIdx.toDouble()).get().addOnCompleteListener(callback1)
        }

        // 사용자 정보를 수정하는 메서드
        fun modifyUserInfo(userClass: UserClass, callback1: (Task<Void>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            val userDataRef = database.getReference("UserData")
            userDataRef.orderByChild("userIdx").equalTo(userClass.userIdx.toDouble()).get().addOnCompleteListener {
                for (a1 in it.result.children) {
                    a1.ref.child("userPw").setValue(userClass.userPw)
                    a1.ref.child("userNickname").setValue(userClass.userNickname)
                    a1.ref.child("userAge").setValue(userClass.userAge)
                    a1.ref.child("hobby1").setValue(userClass.hobby1)
                    a1.ref.child("hobby2").setValue(userClass.hobby2)
                    a1.ref.child("hobby3").setValue(userClass.hobby3)
                    a1.ref.child("hobby4").setValue(userClass.hobby4)
                    a1.ref.child("hobby5").setValue(userClass.hobby5)
                    a1.ref.child("hobby6").setValue(userClass.hobby6).addOnCompleteListener(callback1)
                }
            }
        }
    }
}
