package com.jinhui.scheduler.domain.imiqian.pojo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/3/5.
 */
public class JzProductInfo {
    //主键
    private int id;
    //产品代码
    private String product_code;
    //项目代码
    private String project_code;
    //产品名称
    private String product_name;
    //产品发行商代码
    private String product_institution_code;
    //产品发行商名称
    private String product_institution;
    //风险等级，谨慎型，稳健型等
    private String risk_level;
    //风险等级测评，1是0否
    private boolean risk_test;
    //收益计算类型，01：非保本浮动收益类；02：保本浮动收益类；03：保证收益类
    private String income_cal_type;
    //预计收益率
    private BigDecimal pre_income_ratio;
    //头尾算法，不算头不算尾。。。
    private String headtail_cal_alg;
    //收益算法，截取和四舍五入
    private String income_cal_alg;
    //认购可撤单
    private boolean subscribe_cancel_flag;
    //募集起始日期
    private String subscribe_start_date;
    //募集起始时间
    private String subscribe_start_time;
    //募集结束日期
    private String subscribe_end_date;
    //募集结束时间
    private String subscribe_end_time;
    //产品成立日
    private String established_date;
    //产品起息日
    private String interest_date;
    //产品到期日
    private String expire_date;
    //人数上限
    private int max_person;
    //产品能成立的最小募集金额（元）
    private BigDecimal min_raised_amount;
    //个人认购起点金额（元）
    private BigDecimal person_min_amount;
    //个人认购递增金额（元）
    private BigDecimal person_increasing_amount;
    //个人认购最高金额（元）
    private BigDecimal person_max_amount;
    //个人累计金额上限（元）
    private BigDecimal person_total_amount;
    //产品总额度
    private BigDecimal product_total_amount;
    //分配总额度
    private BigDecimal product_assigned_amount;
    //产品总份额
    private BigDecimal product_total_share;
    //分配总份额
    private BigDecimal product_assigned_share;
    //产品每份金额
    private BigDecimal each_amount;
    //收益分配日
    private String pay_date;
    //固定收益率
    private BigDecimal fixed_income_ratio;
    //产品类型
    private String product_type;
    //还款方式
    private String payment_type;
    //产品状态，1-募集中，2-等待募集，3-停止交易
    private int product_status;
    //计算天数
    private int cal_day;
    //币种
    private String currency;
    //页号
    private int page_num;
    //每页记录数
    private int page_size;
    //开始索引
    private int offset;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_institution_code() {
        return product_institution_code;
    }

    public void setProduct_institution_code(String product_institution_code) {
        this.product_institution_code = product_institution_code;
    }

    public String getProduct_institution() {
        return product_institution;
    }

    public void setProduct_institution(String product_institution) {
        this.product_institution = product_institution;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public boolean isRisk_test() {
        return risk_test;
    }

    public void setRisk_test(boolean risk_test) {
        this.risk_test = risk_test;
    }

    public String getIncome_cal_type() {
        return income_cal_type;
    }

    public void setIncome_cal_type(String income_cal_type) {
        this.income_cal_type = income_cal_type;
    }

    public BigDecimal getPre_income_ratio() {
        return pre_income_ratio;
    }

    public void setPre_income_ratio(BigDecimal pre_income_ratio) {
        this.pre_income_ratio = pre_income_ratio;
    }

    public String getHeadtail_cal_alg() {
        return headtail_cal_alg;
    }

    public void setHeadtail_cal_alg(String headtail_cal_alg) {
        this.headtail_cal_alg = headtail_cal_alg;
    }

    public String getIncome_cal_alg() {
        return income_cal_alg;
    }

    public void setIncome_cal_alg(String income_cal_alg) {
        this.income_cal_alg = income_cal_alg;
    }

    public boolean isSubscribe_cancel_flag() {
        return subscribe_cancel_flag;
    }

    public void setSubscribe_cancel_flag(boolean subscribe_cancel_flag) {
        this.subscribe_cancel_flag = subscribe_cancel_flag;
    }

    public String getSubscribe_start_date() {
        return subscribe_start_date;
    }

    public void setSubscribe_start_date(String subscribe_start_date) {
        this.subscribe_start_date = subscribe_start_date;
    }

    public String getSubscribe_start_time() {
        return subscribe_start_time;
    }

    public void setSubscribe_start_time(String subscribe_start_time) {
        this.subscribe_start_time = subscribe_start_time;
    }

    public String getSubscribe_end_date() {
        return subscribe_end_date;
    }

    public void setSubscribe_end_date(String subscribe_end_date) {
        this.subscribe_end_date = subscribe_end_date;
    }

    public String getSubscribe_end_time() {
        return subscribe_end_time;
    }

    public void setSubscribe_end_time(String subscribe_end_time) {
        this.subscribe_end_time = subscribe_end_time;
    }

    public String getEstablished_date() {
        return established_date;
    }

    public void setEstablished_date(String established_date) {
        this.established_date = established_date;
    }

    public String getInterest_date() {
        return interest_date;
    }

    public void setInterest_date(String interest_date) {
        this.interest_date = interest_date;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public int getMax_person() {
        return max_person;
    }

    public void setMax_person(int max_person) {
        this.max_person = max_person;
    }

    public BigDecimal getMin_raised_amount() {
        return min_raised_amount;
    }

    public void setMin_raised_amount(BigDecimal min_raised_amount) {
        this.min_raised_amount = min_raised_amount;
    }

    public BigDecimal getPerson_min_amount() {
        return person_min_amount;
    }

    public void setPerson_min_amount(BigDecimal person_min_amount) {
        this.person_min_amount = person_min_amount;
    }

    public BigDecimal getPerson_increasing_amount() {
        return person_increasing_amount;
    }

    public void setPerson_increasing_amount(BigDecimal person_increasing_amount) {
        this.person_increasing_amount = person_increasing_amount;
    }

    public BigDecimal getPerson_max_amount() {
        return person_max_amount;
    }

    public void setPerson_max_amount(BigDecimal person_max_amount) {
        this.person_max_amount = person_max_amount;
    }

    public BigDecimal getPerson_total_amount() {
        return person_total_amount;
    }

    public void setPerson_total_amount(BigDecimal person_total_amount) {
        this.person_total_amount = person_total_amount;
    }

    public BigDecimal getProduct_total_amount() {
        return product_total_amount;
    }

    public void setProduct_total_amount(BigDecimal product_total_amount) {
        this.product_total_amount = product_total_amount;
    }

    public BigDecimal getProduct_assigned_amount() {
        return product_assigned_amount;
    }

    public void setProduct_assigned_amount(BigDecimal product_assigned_amount) {
        this.product_assigned_amount = product_assigned_amount;
    }

    public BigDecimal getProduct_total_share() {
        return product_total_share;
    }

    public void setProduct_total_share(BigDecimal product_total_share) {
        this.product_total_share = product_total_share;
    }

    public BigDecimal getProduct_assigned_share() {
        return product_assigned_share;
    }

    public void setProduct_assigned_share(BigDecimal product_assigned_share) {
        this.product_assigned_share = product_assigned_share;
    }

    public BigDecimal getEach_amount() {
        return each_amount;
    }

    public void setEach_amount(BigDecimal each_amount) {
        this.each_amount = each_amount;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public BigDecimal getFixed_income_ratio() {
        return fixed_income_ratio;
    }

    public void setFixed_income_ratio(BigDecimal fixed_income_ratio) {
        this.fixed_income_ratio = fixed_income_ratio;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public int getProduct_status() {
        return product_status;
    }

    public void setProduct_status(int product_status) {
        this.product_status = product_status;
    }

    public int getCal_day() {
        return cal_day;
    }

    public void setCal_day(int cal_day) {
        this.cal_day = cal_day;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "JzProductInfo{" +
                "id=" + id +
                ", product_code='" + product_code + '\'' +
                ", project_code='" + project_code + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_institution_code='" + product_institution_code + '\'' +
                ", product_institution='" + product_institution + '\'' +
                ", risk_level='" + risk_level + '\'' +
                ", risk_test=" + risk_test +
                ", income_cal_type='" + income_cal_type + '\'' +
                ", pre_income_ratio=" + pre_income_ratio +
                ", headtail_cal_alg='" + headtail_cal_alg + '\'' +
                ", income_cal_alg='" + income_cal_alg + '\'' +
                ", subscribe_cancel_flag=" + subscribe_cancel_flag +
                ", subscribe_start_date='" + subscribe_start_date + '\'' +
                ", subscribe_start_time='" + subscribe_start_time + '\'' +
                ", subscribe_end_date='" + subscribe_end_date + '\'' +
                ", subscribe_end_time='" + subscribe_end_time + '\'' +
                ", established_date='" + established_date + '\'' +
                ", interest_date='" + interest_date + '\'' +
                ", expire_date='" + expire_date + '\'' +
                ", max_person=" + max_person +
                ", min_raised_amount=" + min_raised_amount +
                ", person_min_amount=" + person_min_amount +
                ", person_increasing_amount=" + person_increasing_amount +
                ", person_max_amount=" + person_max_amount +
                ", person_total_amount=" + person_total_amount +
                ", product_total_amount=" + product_total_amount +
                ", product_assigned_amount=" + product_assigned_amount +
                ", product_total_share=" + product_total_share +
                ", product_assigned_share=" + product_assigned_share +
                ", each_amount=" + each_amount +
                ", pay_date='" + pay_date + '\'' +
                ", fixed_income_ratio=" + fixed_income_ratio +
                ", product_type='" + product_type + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", product_status='" + product_status + '\'' +
                ", cal_day=" + cal_day +
                ", currency=" + currency +
                ", page_num=" + page_num +
                ", page_size=" + page_size +
                ", offset=" + offset +
                '}';
    }
}
