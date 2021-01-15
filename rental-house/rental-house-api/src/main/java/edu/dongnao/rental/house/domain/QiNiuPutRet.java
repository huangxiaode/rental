package edu.dongnao.rental.house.domain;

import java.io.Serializable;

/**
 * 
 */
public final class QiNiuPutRet implements Serializable {
    private static final long serialVersionUID = 8918735582286008182L;
    public String key;
    public String hash;
    public String bucket;
    public int width;
    public int height;

    @Override
    public String toString() {
        return "QiNiuPutRet{" +
                "key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", bucket='" + bucket + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
