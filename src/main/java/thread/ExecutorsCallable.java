package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xiaodc
 * @date 2020/6/2021:21
 */
public class ExecutorsCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
//        System.out.println("单任务获取结果集 start...");
//        //单个线程获取结果集
//        Future<List<Long>> threadRes= executorService.submit(new FutureTask());
         //不获取结果集不会阻断主线程，获取结果集会阻断主线程
//        System.out.println(threadRes.get().toString());
//        System.out.println("单任务获取结果集 end...");

        System.out.println("多任务获取结果集 start...");
        List<FutureTask> list=new ArrayList<>();
        list.add(new FutureTask());
        list.add(new FutureTask());
        list.add(new FutureTask());
        //多任务时要等所有任务都执行完毕才会返回结果集 获不获取结果集都会阻断主线程
       List<Future<List<Long>>> threadResp= executorService.invokeAll(list,5000,TimeUnit.MILLISECONDS);
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
