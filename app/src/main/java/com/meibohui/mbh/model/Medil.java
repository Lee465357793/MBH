package com.meibohui.mbh.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lenovo on 2017/1/2.
 * 里边的类必须全部实现序列化  否则  会出错
 */
public class Medil implements Serializable{
    /**
     * status : true
     * message :
     * code : 0
     * data : {"withdraw":[]}
     */

    private boolean status;
    private String message;
    private int code;
    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private List<?> withdraw;

        public List<?> getWithdraw() {
            return withdraw;
        }

        public void setWithdraw(List<?> withdraw) {
            this.withdraw = withdraw;
        }
    }


// {"status":true,"message":"","code":0,"data":{"withdraw":[]}}


    @Override
    public String toString() {
        return "任性{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
