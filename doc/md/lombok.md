# lombok

使用lombok简化java pojo的getter、setter等

## 集成

依赖：
```
compile "org.projectlombok:lombok:$lombokVersion"
```

idea安装lombok插件。

## 使用

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Length(min = 8, max = 18)
    @Column
    private String name;

}
```

## 参考

* [Lombok features](https://projectlombok.org/features/index.html)