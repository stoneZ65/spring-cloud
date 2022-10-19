package com.demo.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @description: 人员信息 Model
 * @author: lijiayu
 * @date: 2020-03-10 10:12:07
 */
@Data
@TableName("t_user")
public class User {

    private static final long serialVersionUID = 1585030751548L;

    /**
     *
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("f_username")
    private String username;

    /**
     * 密码
     */
    @TableField("f_password")
    private String password;

    /**
     * 人员姓名
     */
    @TableField("f_name")
    private String name;

    /**
     * 职务级别
     */
    @TableField("f_office_level")
    private Long officeLevel;

    /**
     * 行政职务
     */
    @TableField("f_offic_position")
    private Long officPosition;

    /**
     * 行政职务名称
     */
    @TableField("f_offic_position_name")
    private String officPositionName;

    /**
     * 部门/科室
     */
    @TableField("f_departments")
    private String departments;
    /**
     * 职称
     */
    @TableField("f_job_title")
    private String jobTitle;
    /**
     * 专业
     */
    @TableField("f_profession")
    private String profession;

    /**
     * 移动电话
     */
    @TableField("f_telephone")
    private String telephone;

    /**
     * 应急电话
     */
    @TableField("f_mgc_phone")
    private String mgcPhone;

    /**
     * 住宅电话
     */
    @TableField("f_home_phone")
    private String homePhone;

    /**
     * 办公电话
     */
    @TableField("f_office_phone")
    private String officePhone;

    /**
     * 证件类型:0-身份证
     */
    @TableField("f_card_type")
    private Integer cardType;

    /**
     * 证件号码
     */
    @TableField("f_card_no")
    private String cardNo;

    /**
     * 性别，0:未知，1:男，2:女
     */
    @TableField("f_gender")
    private Integer gender;

    /**
     * 出生日期
     */
    @TableField("f_birthday")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    /**
     * 民族
     */
    @TableField("f_nation")
    private Long nation;

    /**
     * 人员类型
     */
    @TableField("f_person_type")
    private String personType;

    /**
     * 是否可登录，0:否，1:是
     */
    @TableField("f_is_login")
    private Boolean isLogin;

    /**
     * 传真
     */
    @TableField("f_fax")
    private String fax;

    /**
     * 电子邮箱
     */
    @TableField("f_email")
    private String email;

    /**
     * 数据来源单位
     */
    @TableField("f_data_source")
    private String dataSource;

    /**
     * 行政区划
     */
    @TableField("f_region")
    private String region;

    /**
     * 所在企业
     */
    @TableField("f_company")
    private String company;

    /**
     * 主要职责
     */
    @TableField("f_duty")
    private String duty;

    /**
     * 备注
     */
    @TableField("f_remark")
    private String remark;

    /**
     * 头像
     */
    @TableField("f_avatar")
    private Long avatar;

    /**
     * 注册方式，0-默认（终端设备、手动导入、web新增），1-手机注册
     */
    @TableField("f_reg_type")
    private Integer regType;

    /**
     * 组织账号是否禁用
     */
    @TableField("f_is_enable")
    private Boolean isEnable;

    /**
     * 微信登录时唯一标识符
     */
    @TableField("f_open_id")
    private String openId;

    /**
     * 用户登录时唯一标识符
     */
    @TableField("f_union_id")
    private String unionId;

    /**
     * 籍贯
     */
    @TableField("f_hometown")
    private String hometown;

    /**
     * 文化程度
     */
    @TableField("f_level_of_education")
    private Long levelOfEducation;

    /**
     * 住址
     */
    @TableField("f_address")
    private String address;

    /**
     * 入职时间
     */
    @TableField("f_job_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate jobTime;

    /**
     * 是否退伍军人  0：否 1：是
     */
    @TableField("f_veteran_or_not")
    private Boolean veteranOrNot;

    /**
     * 工作证类型
     */
    @TableField("f_work_permit_type")
    private String workPermitType;

    /**
     * 工作证号
     */
    @TableField("f_work_permit_number")
    private String workPermitNumber;

    /**
     * 身份标签
     */
    @TableField("f_identity_tags")
    private Long IdentityTags;

    /**
     * 是否被删除 0：未删除 1：已删除
     */
    @TableField("f_isdeleted")
    private Boolean isdeleted;

    /**
     * 修改时间
     */
    @TableField("f_update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("f_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;


}
