package other.NBIO;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * author: ZGF
 * 09-2020/9/16 : 14:10
 * context : BIO服务端模拟
 */

public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动完成，监听端口8888");
        while (!serverSocket.isClosed()) {
            Socket accept = serverSocket.accept();
            System.out.println("接收到新的连接: " + accept.toString());
            InputStream is = accept.getInputStream();
            InputStreamReader isr = null;
            BufferedReader br = null;
            try {

                isr = new InputStreamReader(is, "UTF-8");
                br = new BufferedReader(isr);
                String msg;
                while (StringUtils.isNotEmpty((msg = br.readLine()))) {
                    System.out.println("消息是: " + msg);
                    break;
                }
                System.out.println("收到来自 " + accept.toString() + " 的消息");
            }finally {
                if (br != null) br.close();
                if (isr != null) isr.close();
                is.close();
            }
        }
    }
}
