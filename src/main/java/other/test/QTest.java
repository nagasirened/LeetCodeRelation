package other.test;

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
 * @return: other.test.QTest
 */
public class QTest {
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    public static void main(String[] args) {
        System.out.println(3 >>> SHARED_SHIFT);
    }
}

