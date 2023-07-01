
import java.util.*;

public class Ex5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 사용자에게 숫자를 입력 받아 그 숫자의 배수 100개를 출력한다.
		
		// 사용자에게 숫자를 입력 받는다.
		Scanner scan = new Scanner(System.in);
		System.out.print("숫자 입력 : ");
		int number = scan.nextInt();
		
		// 100번을 반복한다.
		for(int i = 0 ; i < 100 ; i++) {
			// 입력한 숫자를 곱하면서 출력한다.
			System.out.printf("%d ", number * (i + 1));
		}
		
		scan.close();
	}
}





