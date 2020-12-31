
import com.qinkuan.split.demo.Application;
import com.qinkuan.split.demo.model.User;
import com.qinkuan.split.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by qinkuan on 2019/5/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ApplicationTests {

    @Autowired
    UserService userService;



    @org.junit.Test
    public void testSave() {
        User user = User.builder()
                .id(1)
                .sex(1)
                .mobileNo("13212345678")

                .build();
        user.setCreateTime(new Date());

        userService.save(user);

    }



    @Test
    public void testFind(){
        User u=userService.findById(1L);
        System.out.println(u);
    }




}
