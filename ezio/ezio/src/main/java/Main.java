import com.github.espresso.FileX;

public class Main {
    public static void main(String[] args) throws Exception {
        FileX.write("victory.txt").write("FileX is officially live on JitPack!");
        System.out.println(FileX.read("victory.txt").readLine());
    }
}