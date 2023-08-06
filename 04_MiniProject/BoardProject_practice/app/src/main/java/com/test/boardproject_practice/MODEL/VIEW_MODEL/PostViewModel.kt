package com.test.boardproject_practice.MODEL.VIEW_MODEL

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.boardproject_practice.MODEL.DATA.PostDataClass
import com.test.boardproject_practice.MODEL.REPOSITORY.PostRepository
import com.test.boardproject_practice.MODEL.REPOSITORY.UserRepository
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class PostViewModel : ViewModel() {
    var postSubject = MutableLiveData<String>()
    var postText = MutableLiveData<String>()
    var postImage = MutableLiveData<Bitmap>()
    var postNickname = MutableLiveData<String>()
    var postWriteDate = MutableLiveData<String>()

    var postFileName = MutableLiveData<String>()

    // 게시글 목록
    var postDataList = MutableLiveData<MutableList<PostDataClass>>()
    // 게시글 작성자 닉네임
    var postWriterNicknameList = MutableLiveData<MutableList<String>>()

    init{
        postDataList.value = mutableListOf<PostDataClass>()
        postWriterNicknameList.value = mutableListOf<String>()
    }

    fun setPostReadData(postIdx : Double){
        PostRepository.getPostInfo(postIdx){
            for(c1 in it.result.children){
                // 게시글 제목
                postSubject.value = c1.child("postSubject").value as String
                // 게시글 내용
                postText.value = c1.child("postText").value as String
                // 게시글 작성 날짜
                postWriteDate.value = c1.child("postWriteDate").value as String
                // 이미지 파일 이름
                postFileName.value = c1.child("postImage").value as String

                val postWriterIdx = c1.child("postWriterIdx").value as Long
                UserRepository.getUserInfoByUserIdx(postWriterIdx){
                    for(c2 in it.result.children){
                        postNickname.value = c2.child("userNickname").value as String
                    }
                }

                if(postFileName.value != "None"){
                    PostRepository.getPostImage(postFileName.value!!){
                        thread{
                            val url = URL(it.result.toString())
                            val httpURLConnection = url.openConnection() as HttpURLConnection
                            val bitmap = BitmapFactory.decodeStream(httpURLConnection.inputStream)
                            postImage.postValue(bitmap)
                        }
                    }
                }
            }
        }
    }

    // 게시글 목록
    fun getPostAll(getPostType:Long){
        val tempList = mutableListOf<PostDataClass>()
        postWriterNicknameList.value = mutableListOf<String>()

        PostRepository.getPostAll {
            for(c1 in it.result.children){
                val postIdx = c1.child("postIdx").value as Long
                val postImage = c1.child("postImage").value as String
                val postSubject = c1.child("postSubject").value as String
                val postText = c1.child("postText").value as String
                val postType = c1.child("postType").value as Long
                val postWriteDate = c1.child("postWriteDate").value as String
                val postWriterIdx = c1.child("postWriterIdx").value as Long

                if(getPostType != 0L && getPostType != postType){
                    continue
                }

                val p1 = PostDataClass(postIdx, postType, postSubject, postText, postWriteDate, postImage, postWriterIdx)
                tempList.add(p1)

                UserRepository.getUserInfoByUserIdx(postWriterIdx){

                    for(c2 in it.result.children){
                        val postWriterNickname = c2.child("userNickname").value as String
                        postWriterNicknameList.value?.add(postWriterNickname)
                    }
                }
            }
            // 데이터가 postIdx를 기준으로 오름 차순 정렬되어 있기 때문에
            // 순서를 뒤집는다.
            tempList.reverse()

            postDataList.value = tempList
        }
    }
}