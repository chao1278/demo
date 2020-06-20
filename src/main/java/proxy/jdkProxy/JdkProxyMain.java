package proxy.jdkProxy;

import proxy.UserService;
import proxy.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkProxyMain {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        InvocationHandler invoke = new MyInvocationHandler(service);
        UserService proxy = (UserService) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), invoke);
        proxy.getName("abc");
    }
}
