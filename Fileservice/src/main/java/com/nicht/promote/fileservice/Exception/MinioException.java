package com.nicht.promote.fileservice.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/6/8
 * @Link
 */
@Data
@Component
@NoArgsConstructor
public class MinioException extends  Exception {

    private static final long serialVersionUID = 1L;
    // 提供一个有参数的构造方法，可自动生成
    public MinioException(String message) {
        super(message);// 把参数传递给Throwable的带String参数的构造方法
    }

}
