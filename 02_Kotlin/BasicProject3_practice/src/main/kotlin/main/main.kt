package main

import utils.*
import utils.category.CategoryChoiceClass
import utils.category.CategoryManagementClass
import utils.category.CategoryShowAllClass
import utils.login.LoginClass
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

class MainClass {
    // 스캐너
    val scanner = Scanner(System.`in`)

    // 프로그램 초기상태 로그인 화면
    var mainState = MainState.MAIN_STATE_LOGIN

    // 각 클래스 객체 생성
    var loginClass = LoginClass(scanner)
    var menuClass = MenuClass(scanner)
    var categoryManagementClass = CategoryManagementClass(scanner, this)
    var categoryChoiceClass = CategoryChoiceClass(scanner,this)
    var categoryShowAllClass = CategoryShowAllClass(scanner,this)

    // 메인 카테고리 리스트
    var mainCl = ArrayList<Category>()


    // 메인루프
    fun running(){
        while(true){
            when(mainState){
                // 0. 로그인
                MainState.MAIN_STATE_LOGIN ->{
                    val islogined = loginClass.login()
                    if(islogined){
                        mainState = MainState.MAIN_STATE_MENU
                    }
                }
                // 1. 메뉴 실행
                MainState.MAIN_STATE_MENU ->{
                    val menuNumber = menuClass.menu()
                    when(menuNumber){
                        // 1.1. 메모 카테고리 관리
                        MenuState.MENU_STATE_CATEGORY_MANAGEMENT.num->{
                            mainState = MainState.MAIN_STATE_CATEGORY_MANAGEMENT
                        }
                        // 1.2. 메모 카테고리 선택
                        MenuState.MENU_STATE_CATEGORY_CHOICE.num->{
                            mainState = MainState.MAIN_STATE_CATEGORY_CHOICE
                        }
                        // 1.3. 메모 내용 전체 보기
                        MenuState.MENU_STATE_SHOWALL.num->{
                            mainState = MainState.MAIN_STATE_SHOWALL
                        }
                        // 1.4. 종료
                        MenuState.MENU_STATE_EXIT.num->{
                            mainState = MainState.MAIN_STATE_EXIT
                        }
                    }
                }

                // 1.1. 메모 카테고리 관리
                MainState.MAIN_STATE_CATEGORY_MANAGEMENT ->{
                    val cmStateIsBack = categoryManagementClass.manage()
                    if(cmStateIsBack){
                        mainState = MainState.MAIN_STATE_MENU
                    }
                }

                // 1.2. 메모 카테고리 선택
                MainState.MAIN_STATE_CATEGORY_CHOICE ->{
                    val ccStateIsBack = categoryChoiceClass.choice()
                    if(ccStateIsBack){
                        mainState = MainState.MAIN_STATE_MENU
                    }
                }

                // 1.3. 메모 내용 전체 보기
                MainState.MAIN_STATE_SHOWALL ->{
                    val ccStateIsBack = categoryShowAllClass.showAll()
                    if(ccStateIsBack){
                        mainState = MainState.MAIN_STATE_MENU
                    }
                }

                // 1.4. 종료
                MainState.MAIN_STATE_EXIT ->{
                    // 프로그램을 강제 종료시킨다
                    // 0 : 정상 종료를 나타내는 코드
                    println()
                    println("프로그램을 종료합니다")
                    exitProcess(0)
                }

            }
        }
    }
}

enum class MainState{
    // 0. 로그인
    MAIN_STATE_LOGIN,
    
    // 1. 메뉴 
    MAIN_STATE_MENU,

    // 1.1. 메모 카테고리 관리
    MAIN_STATE_CATEGORY_MANAGEMENT,

    // 1.2. 메모 카테고리 선택
    MAIN_STATE_CATEGORY_CHOICE,

    // 1.3. 메모 내용 전체 보기
    MAIN_STATE_SHOWALL,

    // 1.4. 종료
    MAIN_STATE_EXIT
}