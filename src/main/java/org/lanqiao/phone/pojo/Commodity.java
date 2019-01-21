package org.lanqiao.phone.pojo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Commodity {
    private int s_Id;//商家ID
    private String c_img;//商品图片
    private String c_name;//商品名字
    private String c_xinghao;//商品型号
    private String c_chicun;//屏幕尺寸
    private String c_time;//发布时间
    private String c_xitong;//系统
    private String c_cpu;//CPU
    private String c_qianshe;//前置摄像头
    private String c_houshe;//后置摄像头
    private String c_dianchi;//电池
    private String c_dianjiekou;//充电接口
    private String c_chongdianqi;//充电器型号
    private String c_zhiwen;//是否支持指纹
    private String c_erji;//耳机
    private String c_showtime;//上架时间
}
