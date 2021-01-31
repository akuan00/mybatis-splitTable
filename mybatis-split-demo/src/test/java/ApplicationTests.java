
import com.qinkuan.split.demo.Application;
import com.qinkuan.split.demo.model.User;
import com.qinkuan.split.demo.service.DepartmentService;
import com.qinkuan.split.demo.service.UserService;
import com.qinkuan.split.query.CriteriaQuery;
import com.qinkuan.split.query.MultiParamQuery;
import com.qinkuan.split.type.QueryType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qinkuan on 2019/5/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    DepartmentService departmentService;



    @org.junit.Test
    public void testSave() {
        User user = User.builder()
                .id(10)
                .sex(1)
                .mobileNo("13212345678")

                .build();
        user.setCreateTime(new Date());
        // ID分表
        userService.save(user);

        //自增ID
//        Department department = new Department();
//        department.setName("test1");
//        departmentService.saveAutoKey(department);
//        department=departmentService.get(department.getId());
//        System.out.println("find=="+department);
    }



    @Test
    public void testFind(){
        MultiParamQuery query =new MultiParamQuery();
        query.and(CriteriaQuery.builder()
                .key("email").queryType(QueryType.like).value("%er%")
                .build());
        User u = userService.findOne(query);
//        List<User> u=userService.findByJoon();
        System.out.println("find=="+u);
    }


    @Test
    public void testUpdate(){
        MultiParamQuery query =new MultiParamQuery();
        query.and(CriteriaQuery.builder()
                .key("id").queryType(QueryType.EQ).value(1)
                .build());
        Map sets = new HashMap();
        sets.put("email","ererter");
        int u = userService.updateSelective(1L,sets,query);
        System.out.println("testUpdate=="+u);

        User user=userService.findById(1L);
        user.setIcon("erterfgffd");
        userService.update(user);
        System.out.println("testUpdate=="+u);
    }



}
