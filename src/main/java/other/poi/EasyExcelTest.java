package other.poi;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * @description: 测试Ali SDK
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/15
 * @return: other.poi.EasyExcelTest
 */
public class EasyExcelTest {

    public static final String PATH = "C:\\Users\\Shinelon\\Desktop\\LeetCode Study\\src\\main\\java\\other\\poi\\";

    public static void main(String[] args) {
        simpleWrite();
    }

    public static void simpleWrite () {
        ArrayList<UserDemo> userDemos = new ArrayList<UserDemo>(){
            {
                add(new UserDemo("宋江", 48, new Date(), "songjiang@shuihu.com"));
                add(new UserDemo("卢俊义", 44, new Date(), "lujunyi@shuihu.com"));
                add(new UserDemo("吴用", 35, new Date(), "wuyong@shuihu.com"));
                add(new UserDemo("公孙胜", 40, new Date(), "gongsunsheng@shuihu.com"));
            }
        };

        String fileName = PATH + "XSSF07.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, UserDemo.class).sheet("hero").doWrite(userDemos);
    }

    public static void simpleRead () {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = PATH + "HSSF03.xls";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        DemoDataListener demoDataListener = new DemoDataListener();
        EasyExcel.read(fileName, UserDemo.class, demoDataListener).sheet().doRead();
        List<UserDemo> list = demoDataListener.list;
        System.out.println(JSON.toJSONString(list, true));

        // 写法2：
//        fileName = PATH + "HSSF03.xls";
//        ExcelReader excelReader = null;
//        try {
//            excelReader = EasyExcel.read(fileName, UserDemo.class, new DemoDataListener()).build();
//            ReadSheet readSheet = EasyExcel.readSheet(0).build();
//            excelReader.read(readSheet);
//        } finally {
//            if (excelReader != null) {
//                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//                excelReader.finish();
//            }
//        }
    }

}

class DemoDataListener extends AnalysisEventListener<UserDemo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<UserDemo> list = new ArrayList<>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    // private DemoDAO demoDAO;

    public DemoDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        // demoDAO = new DemoDAO();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param demoDAO
     */
    /*public DemoDataListener(DemoDAO demoDAO) {
        this.demoDAO = demoDAO;
    }*/

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    public void invoke(UserDemo data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     * 存进去
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        // demoDAO.save(list);
        LOGGER.info("存储数据库成功！");
    }
}
