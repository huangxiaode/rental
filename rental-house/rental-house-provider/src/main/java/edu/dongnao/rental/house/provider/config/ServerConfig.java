package edu.dongnao.rental.house.provider.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * ServerConfig
 * 
 */
@Configuration
public class ServerConfig {
	
	/**
	 * 构建一个上传实例，能够支持断点续传。
	 * @return
	 */
	@Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }
	
	/**
     * 七牛云机房配置
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
    	// huanan、region2都表示华南地区
        return new com.qiniu.storage.Configuration(Region.huanan());
    }
    
    @Value("${qiniu.AccessKey}")
    private String accessKey;
    @Value("${qiniu.SecretKey}")
    private String secretKey;

    /**
     * 认证信息实例
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

