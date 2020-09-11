package other.poi;

/**
 * author: ZGF
 * 09-2020/9/11 : 13:11
 * context :
 */

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDemo {

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "生日")
    private Date birth;

    @ExcelIgnore
    private String email;
}
