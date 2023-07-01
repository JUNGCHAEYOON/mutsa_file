package utils.category

import main.Category
import main.MainClass
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class CategoryManagementClass(var scanner: Scanner, var mainClass: MainClass) {

    var cl = ArrayList<Category>()

    // 이전으로 돌아가는 경우 true : CMState.CMSTATE_BACK 를 반환, 이외에는 false 를 반환
    fun manage(): Boolean {
        while (true) {
            try {
                println("==============================")

                // 카테고리 리스트 파일을 불러와서 저장하기
                getCategoryListClass()

                // 카테고리가 있는가?
                // 있다 -> 출력
                // 없다 -> 등록된 카테고리가 없습니다.
                if (cl.isEmpty()) {
                    println("등록된 카테고리가 없습니다.")
                } else {
                    for (index in 0..cl.size - 1) {
                        println("${index + 1} : ${cl[index].categoryName}")
                    }
                }

                println()
                println("1. 카테고리 등록")
                println("2. 카테고리 삭제")
                println("3. 카테고리 수정")
                println("4. 이전")
                print("메뉴를 선택해 주세요 : ")
                var inputTemp = scanner.next()
                var inputNumber = inputTemp.toInt()

                if (inputNumber !in 1..4) {
                    println("ERR : 잘못 입력 하였습니다")
                } else {
                    when (inputNumber) {
                        // 1. 카테고리 등록
                        CMState.CMSTATE_CATEGORY_REGISTER.i -> {
                            scanner.nextLine()
                            print("등록할 카테고리 이름을 입력해 주세요 : ")
                            var name = scanner.nextLine()
                            // 카테고리 객체 생성
                            setCategoryListClass(name)

                            return false
                        }
                        // 2. 카테고리 삭제
                        CMState.CMSTATE_CATEGORY_DELETE.i -> {
                            print("삭제할 카테고리 번호를 입력해 주세요 : ")
                            val temp = scanner.next()
                            val number = temp.toInt()
                            deleteCategory(number)

                            return false
                        }
                        // 3. 카테고리 수정
                        CMState.CMSTATE_CATEGORY_REFACTOR.i -> {
                            print("수정할 카테고리 번호를 입력해 주세요 : ")
                            val temp = scanner.next()
                            val number = temp.toInt()
                            changeCategory(number)
                            return false
                        }
                        // 4. 이전
                        CMState.CMSTATE_BACK.i -> {
                            // 되돌아가면서 mainClass 의 mainCl 에 cl을 덮어씌워준다.
                            mainClass.mainCl = cl
                            return true
                        }
                    }
                }
            }catch (e : Exception){
                println("ERR : 잘못 입력 하였습니다")
            }

        }
    }

    private fun changeCategory(number: Int) {
        // 해당 숫자가 있는 번호인지 확인후 없으면 에러 출력후 함수 종료
        // 해당 숫자가 있는경우 cl 에서 해당 내용 수정
        // 카테고리리스트 파일 다시 저장

        if (number > cl.size || number < 0) {
            println("잘못된 번호 입니다.")
            return
        } else {
            val index = number - 1
            scanner.nextLine()
            print("${cl[index].categoryName} -> ")
            var newName = scanner.nextLine()
            cl[index].categoryName = newName
            // cl 저장
            setCltoFile()
        }
    }

    private fun deleteCategory(number: Int) {
        // 해당 숫자가 있는 번호인지 확인후 없으면 에러 출력후 함수 종료
        // 해당 숫자가 있는경우 cl 에서 해당 내용 삭제
        // 카테고리리스트 파일 다시 저장

        if (number > cl.size || number < 0) {
            println("잘못된 번호 입니다.")
            return
        } else {
            val index = number - 1
            cl.removeAt(index)
            // cl 저장
            setCltoFile()
        }
    }

    // 카테고리 리스트 클래스 객체를 불러오기
    private fun getCategoryListClass() {
        val file = File("카테고리리스트")
        if (file.exists()) {
            try {
                val fis = FileInputStream("카테고리리스트")
                val bis = BufferedInputStream(fis)
                val ois = ObjectInputStream(bis)

                val temp = ois.readObject()
                cl = temp as ArrayList<Category>

                ois.close()
                bis.close()
                fis.close()
            } catch (e: Exception) {
                println("역직렬화 실패")
            }
        } else {
            println("생성된 파일이 없습니다.")
        }
    }

    // 카테고리 리스트 클래스 객체를 파일로 저장
    private fun setCategoryListClass(categoryName: String) {
        try {
            val fos = FileOutputStream("카테고리리스트")
            val bos = BufferedOutputStream(fos)
            val oos = ObjectOutputStream(bos)

            val tempCategory = Category(categoryName)
            cl.add(tempCategory)

            oos.writeObject(cl)
            println("직렬화 성공")

            // 메모리 할당 해제
            oos.close()
            bos.close()
            fos.close()

        } catch (e: Exception) {
            println("직렬화 실패")
        }
    }

    // 현재 cl 을 카테고리 리스트로 저장하는 함수
    private fun setCltoFile() {
        try {
            val fos = FileOutputStream("카테고리리스트")
            val bos = BufferedOutputStream(fos)
            val oos = ObjectOutputStream(bos)
            oos.writeObject(cl)
            // 메모리 할당 해제
            oos.close()
            bos.close()
            fos.close()

        } catch (e: Exception) {
            println("직렬화 실패")
        }
    }
}

// Category Management State
enum class CMState(var i: Int) {
    // 1. 카테고리 등록
    CMSTATE_CATEGORY_REGISTER(1),

    // 2. 카테고리 삭제
    CMSTATE_CATEGORY_DELETE(2),

    // 3. 카테고리 수정
    CMSTATE_CATEGORY_REFACTOR(3),

    // 4. 이전
    CMSTATE_BACK(4)
}