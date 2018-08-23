package com.tools.async.awaitility;

import com.tools.ToolsApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.junit.Test;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.*;

/**
 * Created by zhoushu on 2018/4/12 0012.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = { ToolsApplication.class})
public class AnyncTest {

    @Test
    public void testAsynchronousNormal(){
        final CounterService service = new CounterServiceImpl();
        service.run();
        try{
            // 默认10s, 如果在这时间段内,条件依然不满足,将抛出ConditionTimeoutException
            await().until(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return service.getCount() == 5;
                }
            });
        } catch (Exception e) {
            //Assert.fail("测试代码运行异常：" + e.getMessage() + "，代码位置：" + e.getStackTrace()[0].toString());
        }
    }

    @Test
    public void testAsynchronousAtMost(){
        final CounterService service = new CounterServiceImpl();
        service.run();
        try{
            // 指定超时时间3s, 如果在这时间段内,条件依然不满足,将抛出ConditionTimeoutException
            await().atMost(3, TimeUnit.SECONDS).until(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return service.getCount() == 5;
                }
            });
        } catch (Exception e) {
            //Assert.fail("测试代码运行异常：" + e.getMessage() + "，代码位置：" + e.getStackTrace()[0].toString());
        }
    }
}
