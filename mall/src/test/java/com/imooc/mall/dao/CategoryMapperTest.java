package com.imooc.mall.dao;

import com.imooc.mall.MallApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class CategoryMapperTest  extends MallApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findById() {
//        Category category = categoryMapper.findById(100001);
//        System.out.println(category.toString());
    }

    @Test
    public void queryById() {
//        Category category = categoryMapper.queryById(100001);
//        System.out.println(category.toString());
    }
}