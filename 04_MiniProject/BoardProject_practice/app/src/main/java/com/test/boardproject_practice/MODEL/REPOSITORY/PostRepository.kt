package com.test.boardproject_practice.MODEL.REPOSITORY

import android.net.Uri
import android.provider.ContactsContract.RawContacts.Data
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.test.boardproject_practice.MODEL.DATA.PostDataClass

class PostRepository {
    companion object{
        fun getPostIdx(callback1 : (Task<DataSnapshot>) -> Unit){
            val database = FirebaseDatabase.getInstance()
            val postIdxRef = database.getReference("PostIdx")
            postIdxRef.get().addOnCompleteListener(callback1)
        }

        fun setPostIdx(postIdx : Long, callback1: (Task<Void>) -> Unit){
            val database = FirebaseDatabase.getInstance()
            val postIdxRef = database.getReference("PostIdx")
            postIdxRef.get().addOnCompleteListener {
                it.result.ref.setValue(postIdx).addOnCompleteListener(callback1)
            }
        }

        fun addPostInfo(postDataClass: PostDataClass, callback1: (Task<Void>) -> Unit){
            val database = FirebaseDatabase.getInstance()
            val postDataClass = database.getReference("PostData")
            postDataClass.push().setValue(postDataClass).addOnCompleteListener(callback1)
        }

        fun uploadImage(uploadUri : Uri, fileName : String, callback1: (Task<UploadTask.TaskSnapshot>) -> Unit){
            val storage = FirebaseStorage.getInstance()
            val imageRef = storage.reference.child(fileName)
            imageRef.putFile(uploadUri).addOnCompleteListener(callback1)
        }

        fun getPostInfo(postIdx : Double, callback1: (Task<DataSnapshot>) -> Unit){
            val database = FirebaseDatabase.getInstance()
            val postDataRef = database.getReference("PostData")
            postDataRef.orderByChild("postIdx").equalTo(postIdx).get().addOnCompleteListener(callback1)
        }

        // 게시글 이미지를 가져온다.
        fun getPostImage(fileName:String, callback1: (Task<Uri>) -> Unit){
            val storage = FirebaseStorage.getInstance()
            val fileRef = storage.reference.child(fileName)

            // 데이터를 가져올 수 있는 경로를 가져온다.
            fileRef.downloadUrl.addOnCompleteListener(callback1)
        }

        // 게시글 정보 전체를 가져온다.
        fun getPostAll(callback1: (Task<DataSnapshot>) -> Unit){
            val database = FirebaseDatabase.getInstance()
            val postDataRef = database.getReference("PostData")
            postDataRef.orderByChild("postIdx").get().addOnCompleteListener(callback1)
        }

        // 특정 게시판의 글 정보만 가져온다.
        fun getPostOne(postType:Long, callback1:(Task<DataSnapshot>) -> Unit){
            val database = FirebaseDatabase.getInstance()
            val postDataRef = database.getReference("PostData")
            postDataRef.orderByChild("postType").equalTo(postType.toDouble())
                .ref.orderByChild("postIdx").get().addOnCompleteListener(callback1)
        }
    }
}