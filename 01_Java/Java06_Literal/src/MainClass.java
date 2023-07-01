
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 리터럴 : 프로그래밍 언어에서 값을 작성하는 문법
		
		// 정수 리터럴
		System.out.println(100);
		System.out.println(200);
		
		System.out.println("정수 : " + 100);
		System.out.println("정수 : " + 200 + ", " + 300);
		
		System.out.printf("정수 : %d\n", 100);
		System.out.printf("정수 : %d, %d\n", 200, 300);
		
		// 정수를 작성하면 메모리에 4바이트 기억공간이 마련되어 저장된다.
		// 이에 총 42억개의 숫자 표현이 가능하다
		System.out.printf("정수최대 : %d\n", 2147483647);
		System.out.printf("정수최소 : %d\n", -2147483648);
		
		// 숫자 뒤에 l 이나 L 을 붙혀주면 8바이트 기억공기 마련되어 저장된다.
		// 이에 총 약 1900경개의 숫자 표현이 가능하다.
		System.out.printf("long 최대 : %d\n", 9223372036854775807L);
		System.out.printf("long 최소 : %d\n", -9223372036854775808L);
		
		// 문자(2byte)
		// 작은 따옴표로 작성한다.
		System.out.printf("문자 : %c\n", 'a');
		System.out.printf("문자 : %c\n", '가');
		
		// 실수 (기본 8byte)
		System.out.printf("실수 : %f\n", 11.11);
		
		// 실수 (byte)
		// 실수 뒤에 f 나 F 를 붙히면 4byte 기억공간을 사용한다.
		System.out.printf("float : %f\n", 11.11F);
		
		// 논리값 (1byte)
		System.out.printf("논리형 : %s\n", true);
		System.out.printf("논리형 : %s\n", false);
		
		// 문자열
		// 사실 문자열은 값이 아니다.
		// "" 로 묶어 준다.
		System.out.printf("문자열 : %s\n", "반갑습니다");
	}
}





