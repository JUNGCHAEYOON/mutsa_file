public class JavaMain {

    public static int JavaA1 = 100;

    public static void javaMethod1(){
        System.out.println("javaMethod1");
    }

    public static void main(String [] args){
        // kotlin에서 정의한 companion 멤버 사용
        int a1 = TestClass1.Companion.getKotlinA3();
        System.out.printf("a1 : %d\n", a1);
        TestClass1.Companion.kotlinMethod3();
    }
}
