package com.dong;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * @author lidong14@kuaishou.com
 * Created on 2023-08-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class BaseTest {
}
