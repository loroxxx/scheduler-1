import com.jinhui.scheduler.biz.core.util.UtilTool;
import com.jinhui.scheduler.biz.gzefe.service.export.ExportService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
public class ExportTest extends TestConfig {

    @Autowired
    private ExportService exportService;


    @Test
    public void exportTest() throws Exception {
        exportService.export("2017-07-10");
    }


    @Test
    public void rollback() {
        exportService.rollback(7,"20170710");
    }


    public static void main(String[] args) {
        String admin = UtilTool.md5Tool("admin2");
        System.out.println(admin);
    }

}
