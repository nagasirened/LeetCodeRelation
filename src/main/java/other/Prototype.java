package other;

import java.io.*;

/**
 * @program: LeetCode Study
 * @description: 原型模式
 * @author: ZengGuangfu
 * @create 2019-06-10 13:41
 * 原型模式虽然是创建型的模式，但是与工程模式没有关系。
 * 从名字即可看出，该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。
 */
public class Prototype implements Cloneable {

    private static final long serialVersionUID = 1L;
    private String name;

    private Integer age;

    /* 浅复制 */
    public Object clone() throws CloneNotSupportedException {
        Prototype object = (Prototype)super.clone();
        return object;
    }

    /* 深复制 */
    public Object deepClone() throws IOException, ClassNotFoundException {
        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
