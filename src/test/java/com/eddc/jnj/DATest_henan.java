package com.eddc.jnj;

import com.eddc.jnj.dao.DADao;
import com.eddc.jnj.service.DAService;
import freemarker.template.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DATest_henan {

    @Autowired
    private DAService daService;

    @Autowired
    private Configuration configuration;

    @Autowired
    private JavaMailSender jms;

    @Autowired
    private DADao daDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //  private static final String DATE = "2018-10-24";
//    private static final String DATE = "2018-11-07";
    private static final String DATE = "2018-10-17";


    //1.执行存储过程
    //2.mybits 执行sql 拿到返回结果
    //3.写到文件

    //1.执行存储过程
    @Test
    public void updateHeNan() {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("BU", "BW");
        params.put("date", DATE);
        params.put("num", "-7");
        params.put("num2", "1");
        daService.updateExportHeNanOrderData(params);
    }

    //mybits 执行sql 拿到返回结果
    //2.导出获得正常数据的附件
    @Test
    public void getHeNanNormalData() {
        Map<String, Object> params = new HashMap<String, Object>(6);
        //文件生成日期， 输入要对比的历史数据时长，目前客户要求对比上一周，那输入-7即可，但如果周三碰到节假日没有推送，数据要累积到下周三发，那这里输入-14
        params.put("num", "-7");
        //sql参数
        params.put("date", DATE);
        params.put("BU", "BW");
        params.put("isNew", "是");
//        params.put("user", "hs1589");
        //获得所有的用户
        String sql = "SELECT * FROM [dbo].[sycm_account] where shop_name like '%河南%'";
        logger.info("- 查询所有用户sql :" + sql);
        List<Map<String, Object>> userListMap = daDao.getaHeNanUsers(sql);
        if (userListMap.size() > 0) {
            logger.info("- 查询到：" + userListMap.size() + " 个用户");
            for (Map<String, Object> usermap : userListMap) {
                String userName = usermap.get("name").toString();
                logger.info("user: " + userName);
                params.put("user", userName);
                daService.getDataHeNan(params);
            }
        } else {
            logger.error("没有查询到用户！！");
        }
    }

    //2.导出获得异常数据的附件
    @Test
    public void getHeNanAbnormalData() {
        Map<String, Object> params = new HashMap<String, Object>(6);
        params.put("date", DATE);
        params.put("BU", "BW");
        params.put("isNew", "是");
        params.put("isNewAbnormal", "是");
        params.put("dataType", "GAP<0");
        params.put("dataType2", "GAP <> '退市'");
        daService.getDataHeNanabnormal(params);
    }

    @Test
    public void sendHeNanOrderData() {
        Map<String, Object> params = new HashMap<String, Object>(6);
        params.put("province_name", "河南省");
        params.put("BU", "BW");
        params.put("date", DATE);
        params.put("num", "-7");
//        params.put("num2", "2");
        daService.sendHeNanOrderData(params);
    }


}
