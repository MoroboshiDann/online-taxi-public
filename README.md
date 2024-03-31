# online-taxi-public

# 马士兵飞滴出行网约车项目

## 环境依赖


| 环境            | 版本     |
|---------------|--------|
| api-passenger | 1.8    |
| nacos-server  | 2.03   |
| redis x64     | 5.0.14 |
| mysql-server  | 8.0    |

## 服务端口管理
| 服务名                      | 端口号  |
|--------------------------|------|
| api-passenger            | 8081 |
| service-verificationcode | 8082 |
| service-passenger-user   | 8083 |
| service-price            | 8084 |
| service-map              | 8085 |
| service-driver-user      | 8086 |
| api-boss                 | 8087 |
| api-driver               | 8088 |
| service-order            | 8089 |
| service-order            | 8090 |
| service-sse              | 8091 |

## 安全问题

### 三级等保

- CIA: 保密性，完整性，可用性
  - 手机号，身份证号等脱敏
- 数据层面：sql注入，xss，csrf人机交互token
  - 过滤框架，如jsoup
  - spring的HtmlUtils来解决
  - referer
- 权限控制





