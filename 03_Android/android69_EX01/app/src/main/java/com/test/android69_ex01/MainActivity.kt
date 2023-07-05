package com.test.android69_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android69_ex01.databinding.ActivityMainBinding
import java.io.DataInput
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 사용자가 누른 항목 번호
    var rowPosition = 0

    companion object {
        // Activity가 관리할 프래그먼트들의 이름
        val MAIN_FRAGMENT = "MainFragment"
        val ADD_FRAGMENT = "AddFragment"
        val RESULT_FRAGMENT = "ResultFragment"

        // 학생수를 저장할 파일이름
        val STUDENT_COUNT_FILE_NAME = "studentCount.dat"
        // 학생 데이터를 저장할 파일 이름
        val STUDENT_INFO_FILE_NAME = "studentInfo.dat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        replaceFragment(MAIN_FRAGMENT, false, false)
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment = when(name){
            MAIN_FRAGMENT -> {
                MainFragment()
            }
            ADD_FRAGMENT -> {
                AddFragment()
            }
            RESULT_FRAGMENT -> {
                ResultFragment()
            }
            else -> {
                Fragment()
            }
        }

        if(newFragment != null) {
            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.mainContainer, newFragment)

            if (animate == true) {
                // 애니메이션을 설정한다.
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name:String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    // 학생 수를 읽어와 반환하는 함수
    fun getStudentCount() : Int {

        // 내부 저장소에 파일이 있을 경우에만 불러온다.
        val file = File("${filesDir?.path!!}/${STUDENT_COUNT_FILE_NAME}")
        if(file.exists() == true) {

            val fis = openFileInput(STUDENT_COUNT_FILE_NAME)
            val dis = DataInputStream(fis)

            val count = dis.readInt()

            dis.close()

            return count
        } else {
            return 0
        }
    }

    // 학생 수를 저장하는 메서드
    fun writeStudentCount(count:Int) {
        val fos = openFileOutput(STUDENT_COUNT_FILE_NAME, MODE_PRIVATE)
        val dos = DataOutputStream(fos)
        dos.writeInt(count)

        dos.flush()
        dos.close()
    }

    // 학생 데이터를 읽어오는 메서드
    fun getStudentInfo(count:Int) : MutableList<StudentClass> {
        val studentList = mutableListOf<StudentClass>()

        // 내부 저장소에 파일이 있을 경우에만 불러온다.
        val file = File("${filesDir?.path!!}/${STUDENT_INFO_FILE_NAME}")
        if(file.exists() == true) {

            val fis = openFileInput(STUDENT_INFO_FILE_NAME)
            val ois = ObjectInputStream(fis)

            for(idx in 0 until count){
                val obj = ois.readObject() as StudentClass
                studentList.add(obj)
            }

            ois.close()
        }
        return studentList
    }

    // 학생 객체 저장
    fun addStudentInfo(studentList:MutableList<StudentClass>){
        // 파일을 연다.
        val fos = openFileOutput(STUDENT_INFO_FILE_NAME, MODE_PRIVATE)
        val oos = ObjectOutputStream(fos)

        for(obj in studentList){
            oos.writeObject(obj)
        }

        oos.flush()
        oos.close()
    }
}
// 정보를 담을 객체
data class StudentClass(var name:String, var age:Int, var korean:Int) : Serializable







