package edu.dongnao.rental.lang;

import java.io.Serializable;

/**
 * 服务接口通用结构
 * 
 */
public class ServiceResult<T> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success;
    private String message;
    private T result;
    /** 成功的结果 */
    public static final ServiceResult<Boolean> SUCCESS_RESULT = new ServiceResult<>(true, "操作成功", true);
    /** 失败结果 */
    public static final ServiceResult<Boolean> FAIL_RESULT = new ServiceResult<>(false, "操作失败", false);

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ServiceResult(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> ServiceResult<T> fail(String errorMsg) {
    	return new ServiceResult<>(false, errorMsg);
    }
    public static <T> ServiceResult<T> success() {
        return new ServiceResult<>(true);
    }

    public static <T> ServiceResult<T> of(T result) {
        ServiceResult<T> serviceResult = new ServiceResult<>(true);
        serviceResult.setResult(result);
        return serviceResult;
    }

    public static <T> ServiceResult<T> notFound() {
        return new ServiceResult<>(false, Message.NOT_FOUND.getValue());
    }

    public enum Message {
        NOT_FOUND("Not Found Resource!"),
        NOT_LOGIN("User not login!");

        private String value;

        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
