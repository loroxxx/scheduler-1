package service;

import com.jinhui.scheduler.biz.cmbfae.service.profit.ProfitService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ProfitServiceTest extends TestConfig {

    @Autowired
    private ProfitService profitService;

    @Test
    public void test1() throws IOException {

        profitService.exportFile("C:\\Users\\Administrator\\Desktop\\12.txt");
    }

}
