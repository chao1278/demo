package proxy;

import stream.User;

public class UserServiceImpl implements UserService {
    @Override
    public String getName(String msg) {
        System.out.println("###enter getName method###");
        return msg;
    }
}
