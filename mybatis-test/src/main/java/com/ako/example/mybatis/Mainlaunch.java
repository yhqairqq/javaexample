package com.ako.example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/30.
 */
public class Mainlaunch {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test1() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserInfo userInfo = (UserInfo) session.selectOne("com.ako.example.mybatis.UserMapper.selectUser", 1L);

            System.out.println(userInfo);
        } finally {
            session.close();
        }
    }

    @Test
    public void selectLimit(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            List<UserInfo> userInfos = session.selectList("selectLimit");

           for(UserInfo userInfo:userInfos){
               System.out.println(userInfo);
           }
        } finally {
            session.close();
        }
    }


    @Test
    public void insertUser() {
        SqlSession session = sqlSessionFactory.openSession(false);
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("admin");
            userInfo.setPassword("admin");
            userInfo.setAge(20);
            userInfo.setSex(SexEnum.FEMALE);
            session.insert("com.ako.example.mybatis.UserMapper.insertUser", userInfo);
            session.commit();
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
