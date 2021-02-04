package other.NBIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * author: ZGF
 * 09-2020/9/16 : 14:20
 * context : BIO发送消息客户端模拟
 */

public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8888);
        OutputStream os = s.getOutputStream();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入内容：");
        String nextLine = scanner.nextLine();
        os.write(nextLine.getBytes());
        s.close();  // 关闭连接时才发送出去消息
    }
}
