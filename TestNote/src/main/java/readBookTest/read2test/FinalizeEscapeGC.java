package readBookTest.read2test;

/**
 * @Description
 * @Date 2023/1/16
 * @ 此代码演示
 * 1 对象可以在被GC时自我拯救
 * 2 这种自救的机会只有一次 因为一个对象上 finalize()方法最多只会被系统调用一次
 */
public class FinalizeEscapeGC {
    private  static  FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes i am still  alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws  Exception{
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();;
        // 因为 Finalizer 方法优先级很低 暂停0.5 秒等待它

        Thread.sleep(500);

        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println( " i am dead ");
        }

        //第二次重复上面步骤 但是自救失败
        SAVE_HOOK = null;
        System.gc();;
        // 因为 Finalizer 方法优先级很低 暂停0.5 秒等待它

        Thread.sleep(500);

        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println( " i am dead ");
        }




    }




}




















