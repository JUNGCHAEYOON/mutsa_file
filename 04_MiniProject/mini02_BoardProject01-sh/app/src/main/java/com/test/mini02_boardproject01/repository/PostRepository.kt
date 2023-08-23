package com.test.mini02_boardproject01.repository

import android.graphics.BitmapFactory
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.test.mini02_boardproject01.PostDataClass
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class PostRepository {
    companion object {
        // 게시글 인덱스 번호를 가져온다.
        fun getPostIdx(callback1: (Task<DataSnapshot>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            // 게시글 인덱스 번호
            val postIdxRef = database.getReference("PostIdx")
            postIdxRef.get().addOnCompleteListener(callback1)
        }

        // 게시글 인덱스 번호를 저장한다.
        fun setPostIdx(postIdx: Long, callback1: (Task<Void>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            // 게시글 인덱스 번호
            val postIdxRef = database.getReference("PostIdx")
            // 게시글 인덱스번호 저장
            postIdxRef.get().addOnCompleteListener {
                it.result.ref.setValue(postIdx).addOnCompleteListener(callback1)
            }
        }

        // 게시글 정보를 저장한다.
        fun addPostInfo(postDataClass: PostDataClass, callback1: (Task<Void>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            // PostData 데이터에 접근
            val postDataRef = database.getReference("PostData")
            // 게시글 업로드
            postDataRef.push().setValue(postDataClass).addOnCompleteListener(callback1)
        }

        // 이미지 업로드
        fun uploadImage(uploadUri: Uri, fileName: String, callback1: (Task<UploadTask.TaskSnapshot>) -> Unit) {
            // firebase storage에 접근한다.
            val storage = FirebaseStorage.getInstance()
            // 파일에 접근할 수 있는 객체를 가져온다.
            var imageRef = storage.reference.child(fileName)

            // 파일을 업로드한다.
            // 안드로이드에서 파일을 선택하면 파일에 접근할 수 있는 uri 객체를 반환함
            // putFile 매개변수로 해당 uri 객체가 들어감
            imageRef.putFile(uploadUri!!).addOnCompleteListener(callback1)
        }

        // postIdx를 사용해 게시글 정보를 가져온다.
        fun getPostInfo(postIdx: Double, callback1: (Task<DataSnapshot>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            val postDataRef = database.getReference("PostData")
            postDataRef.orderByChild("postIdx").equalTo(postIdx).get().addOnCompleteListener(callback1)
        }

        // 게시글 이미지를 가져온다.
        fun getPostImage(fileName: String, callback1: (Task<Uri>) -> Unit) {
            val storage = FirebaseStorage.getInstance()
            val fileRef = storage.reference.child(fileName)

            // 데이터를 가져올 수 있는 경로를 가져온다.
            fileRef.downloadUrl.addOnCompleteListener(callback1)
        }

        // 모든 게시글의 정보를 가져온다.
        fun getPostAll(callback1: (Task<DataSnapshot>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            val postDataRef = database.getReference("PostData")
            postDataRef.orderByChild("postIdx").get().addOnCompleteListener(callback1)
        }

        // 특정 게시판의 글 정보만 가져온다.
//        fun getPostOne(postType: Long, callback1: (Task<DataSnapshot>) -> Unit) {
//            val database = FirebaseDatabase.getInstance()
//            val postDataRef = database.getReference("PostData")
//            postDataRef.orderByChild("postType").equalTo(postType.toDouble()).orderByChild("postIdx").get()
//                .addOnCompleteListener(callback1)
//        }

        // 이미지 삭제
        fun removeImage(fileName: String, callback1: (Task<Void>) -> Unit) {
            val storage = FirebaseStorage.getInstance()
            val fileRef = storage.reference.child(fileName)
            // 파일을 삭제한다.
            fileRef.delete().addOnCompleteListener(callback1)
        }

        // 글 삭제
        fun removePost(postIdx: Long, callback1: (Task<Void>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            val testDataRef = database.getReference("PostData")

            // data1이 100인 데이터를 가져온다.
            testDataRef.orderByChild("postIdx").equalTo(postIdx.toDouble()).get().addOnCompleteListener {
                for (a1 in it.result.children) {
                    // 해당 데이터를 삭제한다.
                    a1.ref.removeValue().addOnCompleteListener(callback1)
                }
            }
        }

        // 글 수정
        fun modifyPost(postDataClass: PostDataClass, isNewImage: Boolean, callback1: (Task<Void>) -> Unit) {
            val database = FirebaseDatabase.getInstance()
            val postDataRef = database.getReference("PostData")

            postDataRef.orderByChild("postIdx").equalTo(postDataClass.postIdx.toDouble()).get().addOnCompleteListener {
                for (a1 in it.result.children) {
                    if (isNewImage == true) {
                        a1.ref.child("postImage").setValue(postDataClass.postImage)
                    }
                    a1.ref.child("postSubject").setValue(postDataClass.postSubject)
                    a1.ref.child("postText").setValue(postDataClass.postText).addOnCompleteListener(callback1)
                }
            }
        }
    }
}