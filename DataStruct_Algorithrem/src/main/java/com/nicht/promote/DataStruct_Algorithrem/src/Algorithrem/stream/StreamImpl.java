package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.stream;

import java.util.stream.Collector;

/**
 * @Description
 * @Date 2023/5/24
 */
public class StreamImpl<T> implements  Stream<T>{
   private  T value;

   private  boolean empty=false;

   private NextValueEval<T> nextItemEval;


    @Override
    public <R> StreamImpl<R> map(Function<R, T> mapper) {
        return null;
    }

    @Override
    public <R, A> R collect(Collector<T, A, R> collector) {
        return null;
    }

    public  static  class NextValueEval<T>{
       private  final EvalFunction<T> evalFunction;


       public  NextValueEval(EvalFunction<T> evalFunction) {
           this.evalFunction = evalFunction;
       }

       StreamImpl<T> eval(){
           return  evalFunction.apply();
       }

    }

}
