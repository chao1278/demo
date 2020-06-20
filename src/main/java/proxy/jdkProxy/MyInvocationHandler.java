package proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private  Object targetObj;
    public MyInvocationHandler(){

    }
    public MyInvocationHandler (Object obj){
        this.targetObj=obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("###enter invoke method pre###");
        Object result = method.invoke(targetObj, args);
        System.out.println("###enter invoke method after###");
        return result;
    }
}
