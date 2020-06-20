package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<User> list=getUserData();
        List<User> list2=getUserData();
        Stream<User> stream=list.stream();
        stream=stream.filter(user -> user.getGender()==0&&user.getAge()>50);
        stream.forEach(user -> list2.add(user));
        System.out.println(list2.size());

    }
    private static List<User> getUserData() {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName(String.format("古时的风筝 %s 号", i));
            user.setAge(random.nextInt(100));
            user.setGender(i % 2);
            user.setPhone("18812021111");
            user.setAddress("无");
            users.add(user);
        }
        return users;
    }
}
