package com.danny.cloud.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by danny on 2017-07-31.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
