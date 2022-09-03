package com.imooc.mall.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.service.ICartService;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CartServiceImplTest extends MallApplicationTests {

    @Autowired
    private ICartService cartService;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final Integer uid = 1;

    private final Integer productId = 26;

    @Test
    public void add() {
        log.info("添加购物车中。。。");
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(27);
        cartAddForm.setSelected(true);
        ResponseVo<CartVo> responseVo = cartService.add(uid, cartAddForm);
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo<CartVo> responseVo = cartService.list(uid);
        log.info("result={}", gson.toJson(responseVo));
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void update() {
        CartUpdateForm form = new CartUpdateForm();
        form.setQuantity(19);
        form.setSelected(false);
        ResponseVo<CartVo> responseVo = cartService.update(uid,productId, form);
        log.info("result={}", gson.toJson(responseVo));
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void delete() {
        log.info("删除购物车中。。。");
        ResponseVo<CartVo> responseVo = cartService.delete(uid,productId);
        log.info("result={}", gson.toJson(responseVo));
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void selectAll() {
        ResponseVo<CartVo> responseVo = cartService.selectAll(uid);
        log.info("result={}", gson.toJson(responseVo));
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void unSelectAll() {
        ResponseVo<CartVo> responseVo = cartService.unSelectAll(uid);
        log.info("result={}", gson.toJson(responseVo));
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void sum() {
        ResponseVo<Integer> responseVo = cartService.sum(uid);
        log.info("result={}", gson.toJson(responseVo));
        Assertions.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}