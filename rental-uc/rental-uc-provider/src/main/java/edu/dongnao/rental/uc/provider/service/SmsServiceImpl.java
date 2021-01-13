package edu.dongnao.rental.uc.provider.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import edu.dongnao.rental.lang.ServiceResult;
import edu.dongnao.rental.uc.api.ISmsService;

/**
 * 短信发送服务
 * 
 *
 */
@Service(protocol = "dubbo")
public class SmsServiceImpl implements ISmsService, InitializingBean {
	@Value("${aliyun.sms.accessKey}")
    private String accessKey;

    @Value("${aliyun.sms.accessKeySecret}")
    private String secertKey;

    @Value("${aliyun.sms.template.code}")
    private String templateCode;
    
    private static final String[] NUMS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
    
	private IAcsClient acsClient;
	
	private static final Random random = new Random();
	private static final String SMS_CODE_CONTENT_PREFIX = "SMS::CODE::CONTENT::PREFIX";
	

	@Override
	public ServiceResult<String> sendSms(String telephone) {
		String gapKey = SMS_CODE_CONTENT_PREFIX+telephone;
		// 判断是否已经发送过了
		String result = redisTemplate.opsForValue().get(gapKey);
		if(result != null) {
			return new ServiceResult<String>(false, "请求次数太频繁");
		}
		
		// 准备发送短信
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 6; i++) {
			int index = random.nextInt(10);
			sb.append(NUMS[index]);
		}
		String code = sb.toString();
		String templateParam = String.format("{\"code\":\"%s\"}", code);
		
		SendSmsRequest request = new SendSmsRequest();

        // 使用post提交
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(telephone);
        request.setTemplateParam(templateParam);
        request.setTemplateCode(templateCode);
        request.setSignName("租个房");
        boolean success = false;
        try {
            SendSmsResponse response = acsClient.getAcsResponse(request);
            if ("OK".equals(response.getCode())) {
                success = true;
            } else {
                // TODO log this question
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (success) {
            redisTemplate.opsForValue().set(gapKey, code, 60, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(SMS_CODE_CONTENT_PREFIX + telephone, code, 10, TimeUnit.MINUTES);
            return ServiceResult.of(code);
        } else {
            return new ServiceResult<String>(false, "服务忙，请稍后重试");
        }
	}

	@Override
	public String getSmsCode(String telehone) {
		return this.redisTemplate.opsForValue().get(SMS_CODE_CONTENT_PREFIX + telehone);
	}

	@Override
	public void remove(String telephone) {
		this.redisTemplate.delete(SMS_CODE_CONTENT_PREFIX + telephone);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, secertKey);

        String product = "Dysmsapi";
        String domain = "dysmsapi.aliyuncs.com";

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        this.acsClient = new DefaultAcsClient(profile);
		
	}

}
