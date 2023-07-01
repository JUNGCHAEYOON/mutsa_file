package utils.category

import main.Category
import main.MainClass
import utils.category.categoryChoice.MemoMenuStateCorrect
import utils.category.categoryChoice.MemoMenuStateDelete
import utils.category.categoryChoice.MemoMenuStateRegister
import utils.category.categoryChoice.MemoMenuStateShow
import java.io.*
import java.lang.Exception
import java.util.Scanner

class CategoryChoiceClass(var scanner: Scanner, var mainClass: MainClass) {

    // 가장 큰 범주의 리스트
    var cl = ArrayList<Category>()

    // 하위 클래스 객체
    var memoMenuStateShow = MemoMenuStateShow(scanner)
    var memoMenuStateRegister = MemoMenuStateRegister(scanner)
    var memoMenuStateCorrect = MemoMenuStateCorrect(scanner)
    var memoMenuStateDelete = MemoMenuStateDelete(scanner)

    //
    var chosenCategoryNumber : Int = -1

    // 시작 상태
    var ccState = CategoryChoiceState.CCSTATE_SHOW.i

    // true 를 반환 할경우 5번 이전
    fun choice(): Boolean {
        while (true) {
            try {
                when (ccState) {
                    CategoryChoiceState.CCSTATE_SHOW.i -> {
                        println("==============================")
                        // 카테고리가 있으면 보여주기
                        // 없으면 등록한 카테고리가 없습니다.
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
                        print("선택할 카테고리 번호를 입력해주세요(0. 이전) : ")
                        var inputTemp = scanner.next()
                        var inputNumber = inputTemp.toInt()
                        if (inputNumber == 0) {
                            // 0 인경우 이전
                            return true
                        }else{
                            if(inputNumber > cl.size || inputNumber < 0){
                                println()
                                println("해당 번호의 카테고리는 없습니다.")
                            }else{
                                chosenCategoryNumber = inputNumber
                                ccState = CategoryChoiceState.CCSTATE_INTO_MEMO_MENU.i
                            }
                        }
                    }

                    CategoryChoiceState.CCSTATE_INTO_MEMO_MENU.i -> {
                        // 선택한 카테고리의 메모리스트의 메모의 제목을 쭉 보여줌
                        println("==============================")
                        choiceCategoryAndShow(chosenCategoryNumber)
                        println()
                        print("1. 메모보기, 2. 메모등록, 3. 메모수정, 4. 메모삭제, 5. 이전 : ")
                        var inputTemp2 = scanner.next()
                        var inputNumber2 = inputTemp2.toInt()
                        if (inputNumber2 !in 1..5) {
                            println("ERR : 잘못 입력 하였습니다")
                        } else {
                            when (inputNumber2) {
                                // 1. 메모보기
                                MemoMenuState.MMSTATE_SHOW.i -> {
                                    memoMenuStateShow.show(cl[chosenCategoryNumber - 1])
                                }
                                // 2. 메모등록
                                MemoMenuState.MMSTATE_REGISTER.i -> {
                                    cl[chosenCategoryNumber - 1] = memoMenuStateRegister.register(cl[chosenCategoryNumber - 1])
                                    // 새 메모를 등록해서 해당 카테고리를 받아온뒤 전체 cl 파일로 저장
                                    setCltoFile()
                                }
                                // 3. 메모 수정
                                MemoMenuState.MMSTATE_CORRECT.i -> {
                                    cl[chosenCategoryNumber - 1] = memoMenuStateCorrect.correct(cl[chosenCategoryNumber - 1])
                                    // 메모를 수정한뒤 해당 카테고리 받아와 cl 파일로 저장 저장
                                    setCltoFile()
                                }
                                // 4. 메모 삭제
                                MemoMenuState.MMSTATE_DELETE.i -> {
                                    cl[chosenCategoryNumber - 1] = memoMenuStateDelete.delete(cl[chosenCategoryNumber - 1])
                                    setCltoFile()
                                }
                                // 5. 이전
                                MemoMenuState.MMSTATE_BACK.i -> {
                                    // 이전으로 돌아감
                                    ccState = CategoryChoiceState.CCSTATE_SHOW.i
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                println("ERR : 잘못 입력 하였습니다")
            }
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


    private fun choiceCategoryAndShow(number: Int) {
        if (number > cl.size || number < 0) {
            println("잘못된 번호 입니다.")
            return
        } else {
            val index = number - 1
            var memoList = cl[index].MemoList
            if (memoList.isEmpty()) {
                println("등록된 메모가 없습니다.")
            } else {
                for (i in 1..memoList.size) {
                    println("${i} : ${memoList[i - 1].title}")
                }
            }
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
}

enum class CategoryChoiceState(var i: Int) {
    CCSTATE_SHOW(1),
    CCSTATE_INTO_MEMO_MENU(2)
}

enum class MemoMenuState(var i: Int) {
    MMSTATE_SHOW(1),
    MMSTATE_REGISTER(2),
    MMSTATE_CORRECT(3),
    MMSTATE_DELETE(4),
    MMSTATE_BACK(5)
}