package com.danny.project.service.impl;

import com.danny.project.dao.CityMapper;
import com.danny.project.model.City;
import com.danny.project.service.CityService;
import com.danny.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/31.
 */
@Service
@Transactional
public class CityServiceImpl extends AbstractService<City> implements CityService {
    @Resource
    private CityMapper cityMapper;

}
