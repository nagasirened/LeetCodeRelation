package other;

import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 *
 * @description: </p>
 * @author: ZengGuangfu
 * @date 2020/2/13
 * @return: other.QTest
 */
public class QTest {

    public static void main(String[] args) {
        /*User user = new User();
        String username = user.getUsername();
        String password = user.getPassword();
        String secondPwd = user.getSecondPwd();

        if (StringUtils.isEmpty(username) || username.length() < 3){
            throw new RuntimeException("用户名长度不能小于3");
        }

        if (StringUtils.isEmpty(password) || password.length() < 6){
            throw new RuntimeException("密码长度不能小于6");
        }
        if (!password.equals(secondPwd)){
            throw new RuntimeException("两次输入的密码不一致，请重新输入");
        }*/



        /*
        // 14 题
        int base = 1;
        int count = 0;
        for (int i = 1; i<= 10; i++){
            base = i * base;
            count += base;
        }
        System.out.println("累计的和是：" + count);*/



        /*
        13题
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入10个数字，并以‘:’分隔开来");
        String baseInfo = scanner.next();
        if (Objects.isNull(baseInfo) || StringUtils.isEmpty(baseInfo)){
            throw new RuntimeException("请输入内容");
        }

        String[] numArray = baseInfo.split("\\:");
        if (numArray.length != 10){
            throw new RuntimeException("数字数量须为10个，请检查");
        }

        try{
            // 双指针
            Integer minNumber = Integer.valueOf(numArray[0]);
            Integer maxNumber = Integer.valueOf(numArray[0]);

            for (int i = 1; i< numArray.length; i++ ){
                int temp = Integer.valueOf(numArray[i]);
                if (temp > maxNumber){
                    maxNumber = temp;
                }

                if (temp < minNumber){
                    minNumber = temp;
                }
            }

            HashMap<Object, Object> result = new HashMap<>();
            result.put("minNumber", minNumber);
            result.put("maxNumber", maxNumber);
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}

class User{
    String username;
    String password;
    String secondPwd;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSecondPwd() {
        return secondPwd;
    }
}
