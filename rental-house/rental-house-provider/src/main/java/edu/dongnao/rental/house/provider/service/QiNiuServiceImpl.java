package edu.dongnao.rental.house.provider.service;

import java.io.File;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import edu.dongnao.rental.house.api.IQiNiuService;
import edu.dongnao.rental.house.domain.QiNiuPutRet;
import edu.dongnao.rental.lang.ApiResponse;

@Service(protocol = "dubbo")
public class QiNiuServiceImpl implements IQiNiuService,InitializingBean {
	@Autowired
	UploadManager uploadManager;
	
	@Autowired
	private BucketManager bucketManager;
	
	@Autowired
	private Auth auth;
	
	@Value("${qiniu.Bucket}")
	private String bucket;
	
	private StringMap putPolicy;
	
	@Autowired
	Gson gson;
	
	@Value("${qiniu.enable:true}")
	private boolean enable;
	
	@Override
	public ApiResponse uploadFile(byte[] fileData) {
		try {
			Response response = uploadManager.put(fileData, null, getUploadToken());
			int retry = 0;
			while(response.needRetry() && retry < 3) {
				response = uploadManager.put(fileData, null, getUploadToken());
				retry++;
			}
			if(response.isOK()) {
				QiNiuPutRet resut = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
				return ApiResponse.ofSuccess(resut);
			}else {
				return ApiResponse.ofMessage(response.statusCode, response.getInfo());
			}
			
		} catch (QiniuException e) {
			e.printStackTrace();
			try {
				Response response = e.response;
				return ApiResponse.ofMessage(response.statusCode, response.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
				return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	private String getUploadToken() {
		return auth.uploadToken(bucket, null, 3600, putPolicy);
	}

	@Override
	public ApiResponse uploadFile(File file) {
		try {
			Response response = uploadManager.put(file, null, getUploadToken());
			int retry = 0;
			while(response.needRetry() && retry < 3) {
				response = uploadManager.put(file, null, getUploadToken());
				retry++;
			}
			if(response.isOK()) {
				QiNiuPutRet resut = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
				return ApiResponse.ofSuccess(resut);
			}else {
				return ApiResponse.ofMessage(response.statusCode, response.getInfo());
			}
			
		} catch (QiniuException e) {
			e.printStackTrace();
			try {
				Response response = e.response;
				return ApiResponse.ofMessage(response.statusCode, response.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
				return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	public ApiResponse delete(String key) {
		try {
			Response response = bucketManager.delete(bucket, key);
			int retry = 0;
			while(response.needRetry() && retry < 3) {
				response = bucketManager.delete(bucket, key);
				retry++;
			}
			if(response.isOK()) {
				QiNiuPutRet resut = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
				return ApiResponse.ofSuccess(resut);
			}else {
				return ApiResponse.ofMessage(response.statusCode, response.getInfo());
			}
		} catch (QiniuException e) {
			e.printStackTrace();
			try {
				Response response = e.response;
				return ApiResponse.ofMessage(response.statusCode, response.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
				return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	public boolean isEnable() {
		return enable;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		StringMap policy = new StringMap();
		policy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
		putPolicy = policy;
	}

}
