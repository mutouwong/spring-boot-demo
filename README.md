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