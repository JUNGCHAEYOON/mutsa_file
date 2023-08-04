package com.test.boardproject_practice.MODEL.DATA

// 사용자 정보를 담을 클래스
data class UserClass(var userIdx:Long,            // 사용자 인덱스번호
                     var userId:String,           // 사용자 아이디
                     var userPw:String,           // 비밀번호
                     var userNickname: String,    // 닉네임
                     var userAge:Long,            // 나이
                     var hobby1:Boolean,          // 이하는 취미들
                     var hobby2:Boolean,
                     var hobby3:Boolean,
                     var hobby4:Boolean,
                     var hobby5:Boolean,
                     var hobby6:Boolean)