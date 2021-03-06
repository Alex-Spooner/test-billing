package com.test.billing.dao.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
public class Balance {
    private Long balanceId;
    private Float amount;
    private Date creDate;

    public Balance(Long balanceId, Float amount, Date creDate) {
        this.balanceId = balanceId;
        this.amount = amount;
        this.creDate = creDate;
    }

    public Balance(){

    }

    public Long getBalanceId() {
        return balanceId;
    }

    public Float getAmount() {
        return amount;
    }

    public Date getCreDate() {
        return creDate;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final StringBuffer sb = new StringBuffer("Balance{");
        sb.append("balanceId=").append(balanceId);
        sb.append(", amount=").append(amount);
        sb.append(", creDate=").append(df.format(creDate));
        sb.append('}');
        return sb.toString();
    }
}
