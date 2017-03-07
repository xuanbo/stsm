// see http://vuejs-templates.github.io/webpack for documentation.
const path = require('path')

// npm run build后将静态资源发布到 xx/web-ui/build/resources/main/ui下
// gradle build时将ui目录极其下面的文件打成jar
// 由于web模块依赖web-ui模块，会将web-ui的jar放入web模块的classpath下
// 然后将/ui/**映射classpath:/ui/就可以访问静态资源了
const assetsRoot = path.resolve(__dirname, '../build/resources/main/ui')
module.exports = {
  build: {
    env: require('./prod.env'),
    index: path.resolve(assetsRoot, 'index.html'),
    assetsRoot: assetsRoot,
    assetsSubDirectory: 'static',
    // 默认是/，由于我将web-ui模块映射到web模块的/ui/**下了，因此要修改，为了正确的显示上下文及路径，这里不加path
    assetsPublicPath: '',
    productionSourceMap: true,
    // Gzip off by default as many popular static hosts such as
    // Surge or Netlify already gzip all static assets for you.
    // Before setting to `true`, make sure to:
    // npm install --save-dev compression-webpack-plugin
    productionGzip: false,
    productionGzipExtensions: ['js', 'css'],
    // Run the build command with an extra argument to
    // View the bundle analyzer report after build finishes:
    // `npm run build --report`
    // Set to `true` or `false` to always turn it on or off
    bundleAnalyzerReport: process.env.npm_config_report
  },
  dev: {
    env: require('./dev.env'),
    port: 3000,
    autoOpenBrowser: true,
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    proxyTable: {
      '/team/**': 'http://localhost:9090',
      '/user/**': 'http://localhost:9090',
      '/flowable/**': 'http://localhost:9090'
    },
    // CSS Sourcemaps off by default because relative paths are "buggy"
    // with this option, according to the CSS-Loader README
    // (https://github.com/webpack/css-loader#sourcemaps)
    // In our experience, they generally work as expected,
    // just be aware of this issue when enabling this option.
    cssSourceMap: false
  }
}
