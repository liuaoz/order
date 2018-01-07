package com.liuaoz.order.core;

import java.util.Random;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 22:02
 */
public final class KeyUtil {

    /**
     * 生成唯一主键<br>
     * 格式：时间13位+随机数5位
     *
     * @return
     */
    public static String generateUniqueKey() {
        Integer fivePlace = new Random().nextInt(90000) + 10000;
        return System.currentTimeMillis() + String.valueOf(fivePlace);
    }
}
