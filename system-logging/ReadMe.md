1.在pom.xml中引入
        <dependency>
			<groupId>com.ssx</groupId>
			<artifactId>system-logging</artifactId>
			<version>0.0.1</version>
		</dependency>
__

2.在bootstrap.yml中配置如下：
    logging:
      config: classpath:logback-spring.xml

3.在项目中使用logback-spring.xml文件