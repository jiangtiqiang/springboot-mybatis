# springboot-mybatis
这是一个springboot整合mybatis以及mybatisGenerator，cache的demo框架
https://github.com/huangsanyeah/springboot-mybatis


一.springboot-mybatis整合用法：
1.引入依赖

<dependency>
		<groupId>org.mybatis.spring.boot</groupId>
		<artifactId>mybatis-spring-boot-starter</artifactId>
		<version>1.1.1</version>
</dependency>
<dependency>
		<groupId> mysql</groupId>
		<artifactId> mysql-connector-java</artifactId>
		<version> 5.1.39</version>
</dependency>

引入mybatis-generator插件
	<plugin>
				<groupId>org.mybatis.generator</groupId>
				<artifactId>mybatis-generator-maven-plugin</artifactId>
				<version>1.3.5</version>
				<dependencies>
					<dependency>
						<groupId>org.mybatis.generator</groupId>
						<artifactId>mybatis-generator-core</artifactId>
						<version>1.3.5</version>
					</dependency>
				</dependencies>
				<configuration>
					<!--允许移动生成的文件 -->
					<verbose>true</verbose>
					<!-- 是否覆盖 -->
					<overwrite>true</overwrite>
					<!-- 自动生成的配置 -->
					<configurationFile>
						src/main/resources/mybatis-generator.xml</configurationFile>
				</configuration>
	</plugin>
	
file-settings安装插件：idea-mybatis-generator

引入mybatis-generator.xml并配置好数据库连接信息（以及mapper.xml mapper接口 实体类生成位置 名称 注意大小写）

application.properties改为application.yml并配置好数据库连接信息

日志打印级别：（控制台打印SQL语句）
logging:
         level:
               #debug就可以打印mybatis日志了
               com.wonders.mapper: DEBUG
2.启动类加注解@MapperScan("com.wonders.mapper") 扫描

3.Mapper接口加@Repository注解 同理service controller均加上各自层级注解

4.编写代码 测试即可。

存在问题：mybatis-config文件还没有引入项目中来




二.springboot-cache整合用法：
整合了Cacheable缓存，CachePut缓存，CacheEvict尚未实现
1.引入依赖
2.启动类开启注解@EnableCaching
3.ehcahe.xml配置（resources下自动扫描）
4.ServiceImpl
方法是开启注解，也可以直接在类上加//@CacheConfig(cacheNames = "userInfo")统一命名,userInfo要在ehcahe.xml中配置
@Cacheable(value = "userInfo",key = "#userInfo.uid")
@CachePut(value = "userInfo",key = "#userInfo.uid")
5.调用调试即可。

注意的问题：
@CachePut缓存可以进行save或者update操作，切记返回结果并不是int类型的更新或者保存记录数，而是整个对象，这个对象加入缓存才有意义
之后调用根据主键查询这个对象的方法，就直接走缓存了不会打印查询语句。
前提是： @Cacheable(value = "userInfo",key = "#userInfo.uid")
        @CachePut(value = "userInfo",key = "#userInfo.uid")
中value和key是相等的。

存在问题：
1.update的xml中 flushCache="false"作用是什么。
2.ehcache.xml中的配置还不清楚，同时还可以采用java配置舍弃xml配置方式