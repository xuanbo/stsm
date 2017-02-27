# swagger-ui

spring-boot集成swagger-ui生成rest api文档

## 集成

添加依赖：
```
compile "io.springfox:springfox-swagger2:$swagger2Version"
compile "io.springfox:springfox-swagger-ui:$swagger2Version"
```

配置：
```
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("专题页api文档")
                .description("专题页api文档")
                .version("1.0")
                .build();
    }
}
```

## 使用

controller中配置rest信息：
```
    @ApiOperation(value = "根据id获取demo", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "demoId")
    })
    @GetMapping("/{id}")
    public ResultDTO<?> show(@PathVariable Long id) {
        return ResultDTO.success(null, demoService.findById(id));
    }

    @ApiOperation(value = "保存", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "demoDTO", required = true)
    })
    @PostMapping("/save")
    public ResultDTO<?> save(@Valid @RequestBody DemoDTO demoDTO) {
        demoService.save(demoDTO);
        return ResultDTO.success("success");
    }
```

DTO:
```java
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> implements Serializable {

    private static final int SUCCESS_CODE = 200;

    @ApiModelProperty(value = "状态码", example="200", required = true, position=-2)
    private Integer code;
    @ApiModelProperty(value = "消息", example="success", position=-1)
    private String message;
    @ApiModelProperty(value = "数据")
    private T result;

    public static ResultDTO<?> success(String message) {
        return new ResultDTO<String>(SUCCESS_CODE, message, null);
    }

    public static ResultDTO<?> success(String message, Object result) {
        return new ResultDTO<>(SUCCESS_CODE, message, result);
    }

    public static ResultDTO<?> fail(Integer code, String message) {
        return new ResultDTO<String>(code, message, null);
    }

    public static ResultDTO<?> fail(Integer code, String message, Object result) {
        return new ResultDTO<>(code, message, result);
    }

}
```

## 访问

http://localhost:{port}/swagger-ui.html

## 参考

* [Spring Boot中使用Swagger2构建强大的RESTful API文档](http://www.jianshu.com/p/8033ef83a8ed)

