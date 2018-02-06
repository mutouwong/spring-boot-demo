# spring-boot-demo

##配置文件说明

### 自定义属性
```properties
### 自定义配置 ###
info.name = Hello
info.age = 29
```
自定义属性取值:
```java
    @Value("${info.name}")
    private String name;

    @Value("${info.age}")
    private Integer age;
```
也可以把自定义属性绑定至相应的对象Bean中
对象Bean类：
```java
@Component
@ConfigurationProperties(prefix = "info")
public class InfoBean {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
Controller使用如下：
```java
@RestController
@EnableAutoConfiguration()
public class IndexController {

    /*@Value("${wong.name}")
    private String name;

    @Value("${wong.age}")
    private Integer age;*/

    @Autowired
    private InfoBean infoBean;

    @RequestMapping(value = "/")
    String index(){
        return "Hello Spring Boot!" ;
    }

    @RequestMapping(value = "/info/bean")
    String info1() {
        return JSON.toJSONString(infoBean);
    }

}
```

以上自定义属性存放在application.properties中，有时候我们不希望把所有配置都放在application.properties中，这时候我们可以自定义一个，路经跟application.properties同级
配置信息如下：
```properties
### 自定义配置 ###
info.name = Hello
info.age = 29
```
对应的对象Bean类需要加上我们自定义文件的路经
```java
@Component
@PropertySource("classpath:info.properties")
@ConfigurationProperties(prefix = "info")
public class InfoBean {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

## Spring-boot 集成Mybatis
* 在pom文件中引入相关依赖
```xml
    <!-- 添加 MyBatis -->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.1</version>
    </dependency>
    
    <!-- Mysql -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>6.0.6</version>
    </dependency>
		
```
* 在`application.properties`配置文件中加如数据库相关配置，如下：
```properties
### 数据库配置 ###
spring.datasource.url=jdbc:mysql://XXX:3306/db_permission_wg?useUnicode=true&characterEncoding=UTF-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
* 在dao中使用Mybatis注解对数据库进行操作，如下：
```java
@Mapper
public interface SysRoleMapper {

    /**
     * 查询
     * @return
     */
    @Select("select id,name,description from sys_role_wg")
    List<SysRole> selectAll();

    /**
     * 新增
     * @param role
     */
    @Insert("insert into sys_role_wg (name,seq,isdefault) values(#{name},#{seq},#{isdefault})")
    void addSysRole(SysRole role);

}
```

## Spring-boot 事务控制
* 在启动类上面新加注解`@EnableTransactionManagement`
如下：
```java
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
```
* 在相应的Service方法上面加入注解`@Transactional`

## Spring-boot 日志
* 可使用Spring-boot自带的日志来记录日志
参考地址：https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-logging.html
* 使用Logback的指定配置文件来实现日志的记录
在resource目录下面新增logback.xml文件，根据需求加入相应的配置即可
因为在Spring-boot中引入了spring-boot-starter，其中包含了spring-boot-starter-loggin，该依赖内容就是SpringBoot默认的日志框架Logback,因此我们直接可以使用，不需要再次引入相关依赖。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="30 seconds">
    <appender name="ROLLING" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <prudent>true</prudent>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>./logs/log-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxHistory>10</maxHistory>
            <timeBasedFileNamingAndTriggeringPolicy	class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder>
            <pattern>%date %level [%thread] %logger.%class{0}#%method[%file:%line] %msg%n</pattern>
        </encoder>
    </appender>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%date %level [%thread] %logger#%method[%file:%line] %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="ROLLING" />
    </root>

    <logger name="com.wong.springbootdemo" level="INFO" />

</configuration>
```
