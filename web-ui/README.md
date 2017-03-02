# web-ui

> web-ui

由于vue+webpack的build文件夹与gradle的build文件夹冲突，将其修改为build-scripts.

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

For detailed explanation on how things work, checkout the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

## spring boot整合vue-webpack

主要原理是将vue-webpack打包后放到web模块的classpath下。

参考：[springboot+gradle+vue+webpack 组合使用](https://segmentfault.com/a/1190000007021883)
