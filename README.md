# mybatis-splitTable
mybatis工具＋分表

#使用 引入jar包

     <dependency>
        <groupId>com.qinkuan</groupId>
        <artifactId>mybatis-split-code</artifactId>
        <version>0.1</version>
     </dependency>
     
     定义表对象 继承 BaseModel，com.qinkuan.split.annotation.Table定义表名
     @Table(name = "user_info")
     public class User   extends BaseModel {
     
         long id;
     
         int sex;
         String mobileNo; // 手机号
         String email;
         String icon; //用户头像
     
         int departmentId;
     
         @Transient
         String departmentName;
     }
     
     Service 继承 AbstractMapperService
     
     如果要分表 重写getSplitType 指定分表策略，不分表返回null
    protected SplitType getSplitType() {
        return SplitType.ID_RANG;
    }
  

  
    
#自定义分表策略 配置SplitConfig
    如用户ID段分表，每10条记录一个表
     Strategy userStrategy = new Strategy();
     userStrategy.setTableName("user_info");
     userStrategy.setType(SplitType.ID_RANG);
     userStrategy.setCount(10);
     SplitTables.putStrategy(userStrategy.getTableName(),userStrategy);