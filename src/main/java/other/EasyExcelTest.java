package other;

import org.springframework.util.CollectionUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 *
 * @description: 测试Ali SDK
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/15
 * @return: other.EasyExcelTest
 */
public class EasyExcelTest {

    public static void main(String[] args) {
        /*// 年份
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Integer currentYearInt = cal.get(Calendar.YEAR);
        String currentYearStr = currentYearInt.toString();
        currentYearStr = currentYearStr.substring(currentYearStr.length() - 2);

        // 部门
        String orgNum = "";
        String orgseq = ".101.104800.";
        if (org.springframework.util.StringUtils.isEmpty(orgseq) || orgseq.split("\\.").length < 2){
            throw new RuntimeException();
        }
        String[] split = orgseq.split("\\.");
        if (org.springframework.util.StringUtils.isEmpty(split[split.length - 1])){
            orgNum = split[split.length - 2];
        }else{
            orgNum = split[split.length - 1];
        }
        if (orgNum.length() > 4){
            orgNum = orgNum.substring(0,4);
        }
        while (orgNum.length() < 4){
            orgNum = "0" + orgNum;
        }

        // 根据拼接字符串查询，获取已审核通过的编号最新的年度计划，然后在这个计划的基础上加1
        // XXX 数据库给这个字段加唯一索引，防止可能因为并发产生同样的编号
        String param = currentYearStr + orgNum;

        // 产品编号
        Integer planNo = 1;
        String yearPlanNo = null;
        if (Objects.nonNull(yearPlanNo)){
            //planNo = Integer.valueOf(yearPlanNo.substring(6, 9));
            planNo = Integer.valueOf(yearPlanNo.substring(param.length(), param.length() + 3));
            planNo++;
        }
        String planStr = planNo.toString();
        while (planStr.length() < 3){
            planStr = "0" + planStr;
        }

        // 数据来源
        String taskSource = "1";
        System.out.println(param + planStr + "00" + taskSource);*/

        Integer integer = Integer.valueOf("001");
        System.out.println(integer);
    }
}
