package edu.dongnao.rental.house.form;

import java.io.Serializable;

/**
 * 图片信息表单
 */
public class PhotoForm implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String path;

    private int width;

    private int height;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}
