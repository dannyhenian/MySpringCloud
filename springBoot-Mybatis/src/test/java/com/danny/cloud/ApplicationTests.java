package com.danny.cloud;

import com.danny.cloud.entity.City;
import com.danny.cloud.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by danny on 2017-07-31.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MySpringBootApplication.class)

public class ApplicationTests {
    @Autowired
    private CityService cityMapper;

    @Test
    public void contextLoads() {
        List<City>  list  = cityMapper.getAll();
        for (City vo: list) {
            System.out.println(vo.toString());
        }
    }



}
