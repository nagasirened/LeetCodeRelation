package other.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.DateTime;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * author: ZGF
 * 09-2020/9/11 : 13:10
 * context :
 */

public class PoiUtils {

    public static void main(String[] args) throws Exception {
        read();
    }

    public static List<UserDemo> list = new ArrayList<UserDemo>(){
        {
            add(new UserDemo("宋江", 48, new Date(), "songjiang@shuihu.com"));
            add(new UserDemo("卢俊义", 44, new Date(), "lujunyi@shuihu.com"));
            add(new UserDemo("吴用", 35, new Date(), "wuyong@shuihu.com"));
            add(new UserDemo("公孙胜", 40, new Date(), "gongsunsheng@shuihu.com"));
        }
    };

    public static String[] titles = {"姓名", "年龄", "生日", "邮箱"};

    public static final String PATH = "C:\\Users\\Shinelon\\Desktop\\LeetCode Study\\src\\main\\java\\other\\poi\\";
    /**
     * 写入数据, 非工具方法
     */
    public static void poiWrite(List<UserDemo> list) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(); // 03版本的是用HSSF  07用XSSH和SXSSF
        HSSFSheet sheet = workbook.createSheet("聚义");
        if (!CollectionUtils.isEmpty(list)) {
            HSSFRow titleRow = sheet.createRow(0);
            for (int i = 0; i < 4; i++) {
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titles[i]);
            }

            // 填充数据
            for (int i = 1; i <= list.size(); i++) {
                HSSFRow currentRow = sheet.createRow(i);
                for (int j = 0; j < 4; j++) {
                    HSSFCell cell = currentRow.createCell(j);
                    switch (j){
                        case 0:
                            cell.setCellValue(list.get(i - 1).getName());
                            break;
                        case 1:
                            cell.setCellValue(list.get(i - 1).getAge());
                            break;
                        case 2:
                            cell.setCellValue(list.get(i - 1).getBirth());
                            break;
                        case 3:
                            cell.setCellValue(list.get(i - 1).getEmail());
                            break;
                    }
                }
            }

            FileOutputStream os = new FileOutputStream(PATH + "HSSF03.xls");
            workbook.write(os);
            os.close();
        }
    }

    /**
     * 读取的工具方法
     */
    public static void read () throws Exception {
        FileInputStream fis = new FileInputStream(PATH + "HSSF03.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);   // 获取第一张表
        /**
         * sheet 中第一列全是字符串
         */
        int cellNumbers = 0;
        HSSFRow titleRow = sheet.getRow(0);
        if (Objects.nonNull(titleRow)) {
            cellNumbers = titleRow.getPhysicalNumberOfCells();
            for (int i = 0; i < cellNumbers; i++) {
                if (Objects.nonNull(titleRow.getCell(i))) {
                    System.out.print(titleRow.getCell(i).getStringCellValue() + "  ");
                }
            }
            System.out.println();
        }

        // 标题打印完成之后，开始打印
        // 总的行数，除去第一行之后还剩 rows - 1行
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rows; i++) {
            HSSFRow currentRow = sheet.getRow(i);
            for (int j = 0; j < cellNumbers; j++) {
                HSSFCell cell = currentRow.getCell(j);
                CellType cellTypeEnum = cell.getCellTypeEnum();
                switch (cellTypeEnum) {
                    case STRING:
                        System.out.print("字符—" + cell.getStringCellValue() + "  ");
                        break;
                    case BOOLEAN:
                        System.out.print("布尔—" + cell.getBooleanCellValue() + "  ");
                        break;
                    case NUMERIC:
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            System.out.print("日期—" + new DateTime(cell.getDateCellValue()).toString("yyyy-MM-dd HH:mm:ss") + "  ");
                            break;
                        }else {
                            cell.setCellType(CellType.STRING);
                            System.out.print("数字—" + cell.getStringCellValue() + "  ");
                            break;
                        }
                    case BLANK:
                        System.out.print("空值-  ");
                        break;
                    case ERROR:
                        System.out.print("错误-  ");
                        break;
                    case FORMULA:
                        String cellFormula = cell.getCellFormula();
                        System.out.print("公式-" + cellFormula + "  ");
                        HSSFFormulaEvaluator hssfFormulaEvaluator = new HSSFFormulaEvaluator(workbook);

                        CellValue evaluate = hssfFormulaEvaluator.evaluate(cell);
                        String cellValue = evaluate.formatAsString();
                        System.out.print(cellValue);
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }


    }
}


























