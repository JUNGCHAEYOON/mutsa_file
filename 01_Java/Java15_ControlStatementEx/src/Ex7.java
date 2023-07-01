import java.util.*;

public class Ex7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 주민등록 번호를 입력받아 올바른 주민 등록 번호인지 확인한다.
		// (2 x 1자리) + (3 x 2자리) + (4 x 3자리) + (5 x 4자리) 
		// + (6 x 5자리) + (7 x 6자리) + (8 x 7자리) + (9 x 8자리)
		// + (2 x 9자리) + (3 x 10자리) + (4 x 11자리) + (5 x 12자리) 
		// 의 합을 구한다.
		// 위의 합을 11로 나눈 나머지를 구한다.
		// 11에서 나머지를 뺀다.
		// 뺀 숫자를 10으로 나눈 나머지를 구한다. 이 값이 13번째 자리 숫자가 된다.
		
		// 숫자를 입력받는다.
		Scanner scan = new Scanner(System.in);
		System.out.print("주빈번호 13자리를 입력해주세요(-없이) : ");
		long number = scan.nextLong();
		scan.close();
		
		// 각 자리수를 뜯어낸다.
		long a1 = number / 1000000000000L;
		number = number % 1000000000000L;
		
		long a2 = number / 100000000000L;
		number = number % 100000000000L;
		
		long a3 = number / 10000000000L;
		number = number % 10000000000L;
		
		long a4 = number / 1000000000L;
		number = number % 1000000000L;
		
		long a5 = number / 100000000L;
		number = number % 100000000L;
		
		long a6 = number / 10000000L;
		number = number % 10000000L;
		
		long a7 = number / 1000000L;
		number = number % 1000000L;
		
		long a8 = number / 100000L;
		number = number % 100000L;
		
		long a9 = number / 10000L;
		number = number % 10000L;
		
		long a10 = number / 1000L;
		number = number % 1000L;
		
		long a11 = number / 100L;
		number = number % 100L;
		
		long a12 = number / 10L;
		long a13 = number % 10L;
		
		// 공식에 의해서 계산한다.
		long total = (a1 * 2) + (a2 * 3) + (a3 * 4) + (a4 * 5)
				+ (a5 * 6) + (a6 * 7) + (a7 * 8) + (a8 * 9)
				+ (a9 * 2) + (a10 * 3) + (a11 * 4) + (a12 * 5);
		
		long total2 = total % 11;
		long total3 = 11 - total2;
		long total4 = total3 % 10;
		
		// 계산된 결과와 13번째 자리 숫자리를 비교한다.
		if(a13 == total4) {
			// 같으면 유효하다고 출력한다.
			System.out.println("올바른 주민번호입니다");
		} else {
			System.out.println("잘못된 주민번호입니다");
		}
	}

}




