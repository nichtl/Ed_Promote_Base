package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.stream;

/**
 * @Description
 * @Date 2023/5/24
 */
@FunctionalInterface
public interface EvalFunction<T> {

    /**
     * stream流的强制求值方法
     * @return 求值返回一个新的stream
     */
    StreamImpl<T> apply();
}
