import java.io.*

fun main(){

    // saveDataStream()
    // readDataStream()
    saveObjectStream()
}

class TestClass1(var a1:Int, var a2:Int) : Serializable {

    fun printValue(){
        println("a1 : $a1")
        println("a2 : $a2")
    }
}

// DataStream으로 쓰기
fun saveDataStream(){
    val fos = FileOutputStream("data1.dat")
    val dos = DataOutputStream(fos)

    dos.writeInt(100)
    dos.writeDouble(11.11)
    dos.writeUTF("문자열1")

    dos.close()
    fos.close()

    println("쓰기 완료")
}

// DataStream으로 읽기
fun readDataStream(){
    val fis = FileInputStream("data1.dat")
    val dis = DataInputStream(fis)

    val a1 = dis.readInt()
    val a2 = dis.readDouble()
    val a3 = dis.readUTF()

    dis.close()
    fis.close()

    println("a1 : $a1")
    println("a2 : $a2")
    println("a3 : $a3")
}

// ObjectStream으로 쓰기
fun saveObjectStream(){
    val fos = FileOutputStream("data2.dat")
    val oos = ObjectOutputStream(fos)

    val t1 = TestClass1(100, 200)
    val t2 = TestClass1(1000, 2000)

    oos.writeObject(t1)
    oos.writeObject(t2)

    oos.flush()
    oos.close()
    fos.close()
    println("쓰기 완료")
}