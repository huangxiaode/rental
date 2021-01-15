package edu.dongnao.rental.house.api;

import java.io.File;

import edu.dongnao.rental.lang.ApiResponse;

/**
 * 七牛云服务
 * 
 */
public interface IQiNiuService {
	
	ApiResponse uploadFile(byte[] fileData);
	
	ApiResponse uploadFile(File file);

    ApiResponse delete(String key);
    /**
     * 是否开启七牛云服务，通过配置文件配置
     * @return
     */
    boolean isEnable();
}
