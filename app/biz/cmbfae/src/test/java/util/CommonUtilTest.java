package util;

import org.junit.Test;

import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import static org.assertj.core.api.Assertions.assertThat;

public class CommonUtilTest {

	@Test
	public void certTypeConverter() {
		/**
		 * #身份证 0,0=0 #护照 0,1=4 #军官证 0,2=5 #士兵证 0,3=6 #港澳居民来往内地通行证 0,4=9 #户口本
		 * 0,5=8 #营业执照 1,0=2 #组织机构代码证 1,1=1
		 */
		assertThat(CommonUtil.certTypeConverter("0", "0")).isEqualTo("0");
		assertThat(CommonUtil.certTypeConverter("0", "1")).isEqualTo("4");
		assertThat(CommonUtil.certTypeConverter("0", "2")).isEqualTo("5");
		assertThat(CommonUtil.certTypeConverter("0", "3")).isEqualTo("6");
		assertThat(CommonUtil.certTypeConverter("0", "4")).isEqualTo("9");
		assertThat(CommonUtil.certTypeConverter("0", "5")).isEqualTo("8");
		assertThat(CommonUtil.certTypeConverter("1", "0")).isEqualTo("2");
		assertThat(CommonUtil.certTypeConverter("1", "1")).isEqualTo("1");
		assertThat(CommonUtil.certTypeConverter("0", "6")).isEqualTo("A");
		assertThat(CommonUtil.certTypeConverter("1", "2")).isEqualTo("A");
	}
	

	

}
