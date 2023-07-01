// 자바 프로그램 실행 구조
// 1. 개발자는 java 파일에 코드를 작성한다.
// 2. 이클립스에서 java 파일을 저장하면 컴파일을 해준다.
// 3. 컴파일 하게 되면 java 파일은 class 파일로 변환된다.
// 4. class 파일을 jvm에게 전달하면 os 맞는 명령어 체계로 변환하며
// 실행한다.
// java 파일 : 개발자가 작성하는 코드를 가지고 있는 소스코드 파일
// class 파일 : jvm에 의해 실행되는 class 파일
// 컴파일러 : 컴파일을 해주는 소프트웨어
// java에서의 컴파일 : java 파일의 코드를 jvm의 코드로 변환해준다.
// 인터프리터 : 인트프리트를 해주는 소프트웨어
// 인터프리트 : 코드를 os가 인식할 수 있는 코드로 변환하며 실행하는 것.
// jvm은 자바 코드를 실행해주는 역할을 하기 때문에 JRE(Java Runtime
// Environment)라고 부르기도 한다.

public class MainClass {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HelloWorld");
		System.out.println("안녕하세요");
	}

}
