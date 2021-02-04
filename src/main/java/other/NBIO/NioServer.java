package other.NBIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * other.NBIO 服务器端
 *
 * 1.创建一个Selector
 * 2.通过ServerSocketChannel创建Channel通道
 * 3.为这个Channel绑定监听端口
 * 4.设置Channel为非阻塞模式
 * 5.将Channel注册到Selector上，并监听连接事件
 * 6.循环等待新接入的连接
 * 7.根据调用状态处理，调用对应方法处理业务逻辑

/**
 * Buffer 的几个属性  capacity  position  limit  mark
 * 写模式中 limit 为 capacity
 * 读模式中 limit 为 position的最大值
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.start();
    }

    /**
     * 开始方法
     */
    public void start() throws IOException{
        // 1.创建一个Selector
        Selector selector = Selector.open();

        // 2.通过ServerSocketChannel创建Channel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 3.为这个Channel绑定监听端口,此时所有的客户端对Server的接入都由这个Channel处理
        serverSocketChannel.bind(new InetSocketAddress(61111));

        // 4.设置Channel为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 5.将Channel注册到Selector上，并监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务器启动成功");
        // 6.循环等待新接入的连接
        for (;;){
            // FIXME 获取可用的channel的数量
            int readyChannels = selector.select();

            // TODO
            if (readyChannels == 0)  continue;
            // 获取可用Channel的集合，包含了所有注册到Selector上的Channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                // SelectionKey 的实例
                SelectionKey selectionKey = (SelectionKey)iterator.next();
                // 从集合中移除当前实例，要不然就越积越多了
                iterator.remove();


                // 7.根据调用状态处理，调用对应方法处理业务逻辑
                //如果是接入事件
                if (selectionKey.isAcceptable()){
                    acceptHandler(serverSocketChannel, selector);
                }

                //如果是可读事件
                if (selectionKey.isReadable()){
                    readHandler(selectionKey, selector );
                }

            }
        }
    }

    /**
     * 接入事件处理器
     * 1.如果是接入事件，创建socketChannel
     * 2.将socketChannel设置为非阻塞工作模式
     * 3.将channel注册到Selector上，监听可读事件
     * 4.回应客户端提示信息
     */
    public void acceptHandler(ServerSocketChannel serverSocketChannel,Selector selector ) throws IOException {
        // 1 创建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 2 将socketChannel设置为非阻塞工作模式
        socketChannel.configureBlocking(false);

        // 3 将channel注册到Selector上，监听可读事件
        socketChannel.register(selector,SelectionKey.OP_READ);

        // 4 回应客户端提示信息
        socketChannel.write(Charset.forName("UTF-8").encode("你和其他人都不认识，麻烦你长点心"));
    }

    /**
     * 可读事件处理器
     * 1.从SelectionKey中获取到就绪的channel
     * 2.创建buffer
     * 3.将客户端发送的请求信息
     * 4.将channel再次注册到selector上，监听它的可读事件
     * 5.将客户端发送的请求信息 广播到其他客户端
     */
    public void readHandler(SelectionKey selectionKey, Selector selector) throws IOException{
        // 1.从SelectionKey中获取到就绪的channel
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

        // 2.创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 3.将客户端发送的请求信息
        StringBuffer requestBuffer = new StringBuffer("");
        while (socketChannel.read(byteBuffer) > 0){
            // 切换为读模式
            byteBuffer.flip();

            // 读取buffer中的内容
            requestBuffer.append(Charset.forName("UTF-8").decode(byteBuffer));
        }
        String request = requestBuffer.toString();

        // 4.将channel再次注册到selector上，监听它的可读事件
        socketChannel.register(selector,SelectionKey.OP_READ);

        // 5.将客户端发送的请求信息   TODO 广播到其他客户端
        if (request.length() > 0){
            System.out.println(request);

        }
    }


}
