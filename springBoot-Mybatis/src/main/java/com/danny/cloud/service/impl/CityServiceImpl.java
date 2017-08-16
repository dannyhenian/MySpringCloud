package com.danny.cloud.service.impl;

import com.danny.cloud.entity.City;
import com.danny.cloud.mapper.CityMapper;
import com.danny.cloud.service.CityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danny on 2017-07-31.
 */

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityDao;

//    @Transactional
    public List<City> getAll() {
        PageHelper.startPage(1,1);
        return cityDao.selectAll();
    }
}
