package edu.dongnao.rental.house.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 */
public class HousePictureDTO implements Serializable {
    private static final long serialVersionUID = 8918735582286008182L;
    private Long id;

    @JsonProperty(value = "house_id")
    private Long houseId;

    private String path;

    @JsonProperty(value = "cdn_prefix")
    private String cdnPrefix;

    private int width;

    private int height;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCdnPrefix() {
        return cdnPrefix;
    }

    public void setCdnPrefix(String cdnPrefix) {
        this.cdnPrefix = cdnPrefix;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "HousePictureDTO{" +
                "id=" + id +
                ", houseId=" + houseId +
                ", path='" + path + '\'' +
                ", cdnPrefix='" + cdnPrefix + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
