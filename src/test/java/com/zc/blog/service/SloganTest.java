package com.zc.blog.service;

import com.zc.blog.domain.Slogan;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SloganTest {

    @Autowired
    private SloganService sloganService;

    @Test
    public void testFindAll() {
        List<Slogan> slogans = sloganService.findAll();
        Assert.assertEquals("i want to ", slogans.get(0).getTextPrefix());
    }

    @Test
    public void testUpdateSlogan() {
        List<Slogan> slogons = sloganService.findAll();
        Slogan slogan = slogons.get(0);
        slogan.setTextPrefix("I want to ");
        int i = sloganService.update(slogan);
        Assert.assertEquals(1, i);
    }

    @Test
    public void testDeleteSlogan() {
        int i = sloganService.deleteById(1);
        Assert.assertEquals(1, i);
    }

    @Test
    public void testInsertSlogan() {
        Slogan slogan = new Slogan();
        slogan.setTextPrefix("i want to ");
        slogan.setTextSuffix("see the truth");
        int i = sloganService.insert(slogan);
        Assert.assertEquals(1, i);
    }
}
