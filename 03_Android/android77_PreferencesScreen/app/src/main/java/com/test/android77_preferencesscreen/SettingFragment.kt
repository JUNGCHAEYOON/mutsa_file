package com.test.android77_preferencesscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat

// build.gradle 에 androidx.perference:perference 라이브러리를 추가한다.
class SettingFragment : PreferenceFragmentCompat() {
    // PreferenceScreen이 생성될 때 호출된다
    // PreferenceScreen을 구성하기 위한 xml 파일을 지정한다.
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref, rootKey)
    }

    // EditTextPreference
    // defaultValue : 초기값
    // title : 화면에 보여지는 이름
    // key : 데이터를 가져올 때 사용하는 이름
    // summary : 표시는 설명
    // icon : 좌측에 표시될 아이콘
    // dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
    // dialogTitle : 입력을 위해 뜨는 다이얼로그의 타이틀
    // dialogMessage : 입력을 위해 뜨는 다이얼로그의 메시지
    // dependency : true나 false를 저장하는 요소의 값에 따라 활성화 비활성화가 설정된다.
    // true면 활성화되고, false면 비활성화된다.

    // CheckBoxPreference
    // defaultValue : 초기값
    // key : 데이터를 가졍로 때 사용하는 이름
    // title : 화면에 보여지는 이름
    // summary : 표시되는 설명
    // icon : 좌측에 표시될 아이콘
    // summaryOff : 체크 해제 되어 있을 때 보여줄 설명
    // summaryOn : 체크 되어 있을 때 보여줄 설명
    // dependency : true나 false를 저장하는 요소의 값에 따라 활성화 비활성화가 설정된다.
    // true면 활성화되고, false면 비활성화된다.

    // SwitchPreference
    // defaultValue : 초기값
    // key : 데이터를 가졍로 때 사용하는 이름
    // title : 화면에 보여지는 이름
    // summary : 표시되는 설명
    // icon : 좌측에 표시될 아이콘
    // summaryOff : off 상태 있을 때 보여줄 설명
    // summaryOn : on 상태 있을 때 보여줄 설명
    // dependency : true나 false를 저장하는 요소의 값에 따라 활성화 비활성화가 설정된다.
    // true면 활성화되고, false면 비활성화된다.

    // ListPreference
    // defaultValue : 초기값
    // key : 데이터를 가졍로 때 사용하는 이름
    // title : 화면에 보여지는 이름
    // icon : 좌측에 표시될 아이콘
    // summary : 표시되는 설명
    // dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
    // entries : 화면상에 보여줄 항목의 문자열
    // entryValues : 코드에서 사용할 값

    // MultiSelectListPreference
    // defaultValue : 초기값
    // key : 데이터를 가졍로 때 사용하는 이름
    // title : 화면에 보여지는 이름
    // icon : 좌측에 표시될 아이콘
    // summary : 표시되는 설명
    // dialogIcon : 입력을 위해 뜨는 다이얼로그의 아이콘
    // entries : 화면상에 보여줄 항목의 문자열
    // entryValues : 코드에서 사용할 값
}