package com.imooc.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.service.IShippingService;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
public class ShippingServiceImplTest extends MallApplicationTests {

    @Autowired
    private IShippingService shippingService;

    private static Integer uid = 1;

    private ShippingForm form;

    private Integer shippingId;

    @Before
    public void before() {
        ShippingForm form = new ShippingForm();
        form.setReceiverName("昊哥哥");
        form.setReceiverAddress("湖南长沙");
        form.setReceiverCity("长沙");
        form.setReceiverDistrict("岳麓区");
        form.setReceiverPhone("188123456");
        form.setReceiverMobile("123456789");
        form.setReceiverProvince("湖南");
        form.setReceiverZip("0000");
        this.form = form;
        add();
    }



    public void add() {
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form);
        log.info("result={}", responseVo);
        this.shippingId = responseVo.getData().get("shippingId");
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @After
    public void delete() {
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("result={}", responseVo);
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void update() {
        form.setReceiverCity("广州");
        ResponseVo responseVo = shippingService.update(uid, shippingId, form);
        log.info("result={}", responseVo);
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = shippingService.list(uid, 1, 5);
        log.info("result={}", responseVo);
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}