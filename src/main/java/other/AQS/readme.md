

1.如果线程是不断交替执行，没有存在任何竞争的情况，那么AQS的队列的节点不会被初始化
2.存在竞争后(此时t1占有锁)，t2先在acquire方法中如下
    if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
tryAcquire是尝试获取锁，因为已经被t1占有，所以state不是0。   tryAcquire(arg)返回false，继续执行。
此时就需要将t2加入队列，这个时候队列的Node节点会初始化。
重点是enq(Node node)方法，将生成一个thread是null的节点，而t2则是占用第二个Node节点。
因为AQS默认是将初始化后的第一个节点作为占用锁的节点。就算后面t1完了，将head的指针指向了第二个，也会将第二个的thread变为null
在t2线程park之前，会进行两次自旋尝试再次获取锁，要是还没有获取到的话，就会进行park
这两次自旋有一次是通过prev节点的wsStatus来进行更改和判断。也就是当前节点的上一个节点的属性（这样设计可能是认为节点自己不知道自己park睡着了，使用链表上一个节点去判断）
（两次自旋的代码位置）
acquireQueued方法有两次判断 p==head和tryAcquire()自旋（第一次是初始化，第二次是枷锁）

* 被唤醒（unpark）的线程，接着在LockSupport.park方法后面执行    
普通Lock方法加锁被unpark唤醒之后进行了一次 Thread.intertupted()判断
    -- 这儿设计有点奇怪，因为intertupted() 返回true只有，会把interrupt还原为false
    -- 方法不断返回，会将Thread.currentThread.interrupt()执行一次，还原用户的标记行为。
    -- 对于普通Lock方法而言，这个判断没用，不Thread.intertupted()的判断，直接void也是一样的，因为都不会打断线程，没用
    
    其实它是和  lockInterruptibly 配合使用相关联使用的，普通Lock并不会做出打断lock的行为。
    lockInterruptibly 和 lock 的唯一区别就是被打断后，会直接抛出InterruptedExeception异常
    **catch**住这个异常，就可以做出对应的一些响应
    
reentrantLock提供了一个 lockInterruptibly 方法，在线程被打断的时候调用方法

try{
    reentrantLock.lockInterruptibly();
}catch(InterruptedExeception e){
    执行被打断后的逻辑...
}











