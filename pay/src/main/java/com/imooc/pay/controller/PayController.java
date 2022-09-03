package com.imooc.pay.controller;

import com.imooc.pay.pojo.PayInfo;
import com.imooc.pay.service.impl.PayServiceImpl;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private PayServiceImpl payServiceImpl;

    @Autowired
    private WxPayConfig wxPayConfig;

    /**
     * 创建/发起支付
     * @param orderId
     * @param amount
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("amount") BigDecimal amount,
                               @RequestParam("payType") String payType
    ) {
        PayResponse response = payServiceImpl.create(orderId, amount, BestPayTypeEnum.WXPAY_NATIVE);
        Map map = new HashMap<>();
        map.put("codeUrl", response.getCodeUrl());
        map.put("orderId", orderId);
        map.put("returnUrl", wxPayConfig.getReturnUrl());
        return new ModelAndView("createForWxNative", map);
    }

    /**
     * 异步通知处理
     * @param notifyData
     * @return
     */
    @PostMapping("/notify")
    @ResponseBody
    public String asyncNotify(@RequestBody String notifyData) {
        return payServiceImpl.asyncNotify(notifyData);
    }

    /**
     * 查询支付记录（通过订单号）
     * @param orderId
     * @return
     */
    @GetMapping("/queryByOrderId")
    @ResponseBody
    PayInfo queryByOrderId(@RequestParam String orderId) {
        log.info("查询支付记录中。。。。");
        return payServiceImpl.queryByOrderId(orderId);
    }
}
