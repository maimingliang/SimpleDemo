package com.maiml.common.http.intercepter;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * post請求緩存数据
 * Created by WZG on 2016/10/26.
 */
@Entity
public class CookieResult {
    @Id
    private long id;
    /*url*/
    private String url;
    /*返回结果*/
    private String resulte;
    /*时间*/
    private long time;

    public CookieResult(String url, String resulte, long time) {
        this.url = url;
        this.resulte = resulte;
        this.time = time;
    }


    @Keep
    public CookieResult(long id, String url, String resulte, long time) {
        this.id = id;
        this.url = url;
        this.resulte = resulte;
        this.time = time;
    }

    @Keep
    public CookieResult() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getResulte() {
        return this.resulte;
    }
    public void setResulte(String resulte) {
        this.resulte = resulte;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}
