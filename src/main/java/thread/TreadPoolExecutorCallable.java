package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xiaodc
 * @date 2020/6/2022:42
 */
public class TreadPoolExecutorCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //线程池和队列满了之后的处理方式
        //1.对拒绝任务抛弃处理，并且抛出异常。
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        //这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功
        RejectedExecutionHandler handler2 = new ThreadPoolExecutor.CallerRunsPolicy();
        //对拒绝任务直接无声抛弃，没有异常信息
        RejectedExecutionHandler handler3 = new ThreadPoolExecutor.DiscardPolicy();
        //对拒绝任务不抛弃，而是抛弃队列里面等待最久的一个线程，然后把拒绝任务加到队列
        RejectedExecutionHandler handler4 = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExecutor executor=new ThreadPoolExecutor(5,10,20000,TimeUnit.MILLISECONDS,
                workQueue,handler);

//        System.out.println("单任务获取结果集 start...");
//        //单个线程获取结果集
//        Future<List<Long>> threadRes = executor.submit(new FutureTask());
//        //不获取结果集不会阻断主线程，获取结果集会阻断主线程
//        System.out.println(threadRes.get().toString());
//        System.out.println("单任务获取结果集 end...");
//
//        System.out.println("多任务获取结果集 start...");
        List<ExecutorsCallable.FutureTask> list=new ArrayList<>();
        list.add(new ExecutorsCallable.FutureTask());
        list.add(new ExecutorsCallable.FutureTask());
        list.add(new ExecutorsCallable.FutureTask());
        //多任务时要等所有任务都执行完毕才会返回结果集 获不获取结果集都会阻断主线程
        List<Future<List<Long>>>threadResp=executor.invokeAll(list);
        List<Long> resp=new ArrayList<>();
        for(Future<List<Long>> future:threadResp){
            resp.addAll(future.get());
        }
        System.out.println("多任务结果集："+resp.toString());
        System.out.println("多任务获取结果集 end...");

    }

    static class FutureTask implements Callable<List<Long>>{
        @Override
        public List<Long> call() throws Exception {
            List<Long> res=new ArrayList<>();
            res.add(1000l);
            Thread.sleep(4000);
            System.out.println(".......................");
            return res;
        }
    }
}
