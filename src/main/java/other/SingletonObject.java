package other;

/**
 * @program: LeetCode Study
 * @description: 单例模式
 * @author: ZengGuangfu
 * @create 2019-06-10 13:34
 */
public class SingletonObject {

    private SingletonObject(){

    };

    private static SingletonObject singletonObject = null;

    public static SingletonObject getInstence(){
        if (singletonObject == null){
            synchronized (SingletonObject.class){
                if (singletonObject == null){
                    singletonObject = new SingletonObject();
                }
            }
        }
        return singletonObject;
    }
}
