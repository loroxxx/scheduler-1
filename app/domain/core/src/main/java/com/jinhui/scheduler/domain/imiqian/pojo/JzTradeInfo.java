package com.jinhui.scheduler.domain.imiqian.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/3/5.
 */
public class JzTradeInfo implements Serializable{
    //id
    private int id;
    //用户ID(晋中生成)
    private String user_id;
    //TA机构ID
    private String account_id;
    //交易申请订单号
    private String trading_id;
    //订单确认流水号
    private String tradingconfirm_id;
    //销售机构代码
    private String distributor_code;
    //产品代码
    private String product_code;
    //项目代码
    private String project_code;
    //产品名称
    private String product_name;
    //产品类型
    private String product_type;
    //业务类型
    private String trading_type;
    //收费类别
    private String shareclass;
    //币种
    private String currency_type;
    //交易订单状态
    private String trading_status;
    //申请金额
    private BigDecimal money_amount;
    //申请份额
    private BigDecimal share_amount;
    //手续费
    private BigDecimal charge;
    //确认金额
    private BigDecimal confirmed_money;
    //确认份额
    private BigDecimal confirmed_share;
    //交易发生日期
    private String apply_date;
    //交易发生时间
    private String apply_time;
    //交易确认日期
    private String confirm_date;
    //交易确认时间
    private String confirm_time;
    //交易返回状态
    private String return_code;
    //错误信息
    private String error_info;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getTrading_id() {
        return trading_id;
    }

    public void setTrading_id(String trading_id) {
        this.trading_id = trading_id;
    }

    public String getTradingconfirm_id() {
        return tradingconfirm_id;
    }

    public void setTradingconfirm_id(String tradingconfirm_id) {
        this.tradingconfirm_id = tradingconfirm_id;
    }

    public String getDistributor_code() {
        return distributor_code;
    }

    public void setDistributor_code(String distributor_code) {
        this.distributor_code = distributor_code;
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

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getTrading_type() {
        return trading_type;
    }

    public void setTrading_type(String trading_type) {
        this.trading_type = trading_type;
    }

    public String getShareclass() {
        return shareclass;
    }

    public void setShareclass(String shareclass) {
        this.shareclass = shareclass;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public String getTrading_status() {
        return trading_status;
    }

    public void setTrading_status(String trading_status) {
        this.trading_status = trading_status;
    }

    public BigDecimal getMoney_amount() {
        return money_amount;
    }

    public void setMoney_amount(BigDecimal money_amount) {
        this.money_amount = money_amount;
    }

    public BigDecimal getShare_amount() {
        return share_amount;
    }

    public void setShare_amount(BigDecimal share_amount) {
        this.share_amount = share_amount;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getConfirmed_money() {
        return confirmed_money;
    }

    public void setConfirmed_money(BigDecimal confirmed_money) {
        this.confirmed_money = confirmed_money;
    }

    public BigDecimal getConfirmed_share() {
        return confirmed_share;
    }

    public void setConfirmed_share(BigDecimal confirmed_share) {
        this.confirmed_share = confirmed_share;
    }

    public String getApply_date() {
        return apply_date;
    }

    public void setApply_date(String apply_date) {
        this.apply_date = apply_date;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(String confirm_date) {
        this.confirm_date = confirm_date;
    }

    public String getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(String confirm_time) {
        this.confirm_time = confirm_time;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
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
        return "JzTradeInfo{" +
                "id='" + id + '\'' +
                "user_id='" + user_id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", trading_id='" + trading_id + '\'' +
                ", tradingconfirm_id='" + tradingconfirm_id + '\'' +
                ", distributor_code='" + distributor_code + '\'' +
                ", product_code='" + product_code + '\'' +
                ", project_code='" + project_code + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_type='" + product_type + '\'' +
                ", trading_type='" + trading_type + '\'' +
                ", shareclass='" + shareclass + '\'' +
                ", currency_type='" + currency_type + '\'' +
                ", trading_status='" + trading_status + '\'' +
                ", money_amount=" + money_amount +
                ", share_amount=" + share_amount +
                ", charge=" + charge +
                ", confirmed_money=" + confirmed_money +
                ", confirmed_share=" + confirmed_share +
                ", apply_date='" + apply_date + '\'' +
                ", apply_time='" + apply_time + '\'' +
                ", confirm_date='" + confirm_date + '\'' +
                ", confirm_time='" + confirm_time + '\'' +
                ", return_code='" + return_code + '\'' +
                ", error_info='" + error_info + '\'' +
                '}';
    }
}
