package com.ssm.project;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/***
 * 用来配置spring和junit的整合 junit启动的时候加载spring
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit Spring的配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BaseTest {
}
