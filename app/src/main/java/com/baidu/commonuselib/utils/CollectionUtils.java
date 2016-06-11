package com.baidu.commonuselib.utils;

import java.util.Collection;

/**
 * CollectionUtils
 *
 * @author linjunwu
 * @since 2016/6/11
 */
public class CollectionUtils {

    public <T extends Collection> boolean isEmpty(T obj) {
       if (obj == null || obj.size() == 0) {
           return true;
       } else {
           return false;
       }
    }
}
