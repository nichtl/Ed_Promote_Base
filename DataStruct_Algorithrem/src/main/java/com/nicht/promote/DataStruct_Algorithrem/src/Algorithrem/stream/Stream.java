package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.stream;

import java.util.stream.Collector;

/**
 * @Description
 * @Date 2023/5/24
 */
public interface Stream<T> {

    <R> StreamImpl<R> map(Function<R,T> mapper);

    <R,A> R collect(Collector<T,A,R> collector);

  static  <T> StreamImpl<T> emptyStream(){
      return  null;
  }

}
