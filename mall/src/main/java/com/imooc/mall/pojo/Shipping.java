package com.imooc.mall.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class Shipping {

    @NotBlank
    private Integer id;

    @NotBlank
    private Integer userId;

    @NotBlank
    private String receiverName;

    @NotBlank
    private String receiverPhone;

    @NotBlank
    private String receiverMobile;

    @NotBlank
    private String receiverProvince;

    @NotBlank
    private String receiverCity;

    @NotBlank
    private String receiverDistrict;

    @NotBlank
    private String receiverAddress;

    @NotBlank
    private String receiverZip;

    @NotBlank
    private Date createTime;

    @NotBlank
    private Date updateTime;

}