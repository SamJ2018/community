## 社区项目

## ddl
```sql
create table USER
(
	ID INTEGER default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_D7C4463A_D2B6_4B16_9B7D_64E19B441A7C) auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);
```
## flyway migration 开源的数据库管理 依赖
```xml
 <plugin>
                <groupId>org.flywaydb</groupId>
                <artifactId>flyway-maven-plugin</artifactId>
                <version>5.2.4</version>
                <configuration>
                    <url>jdbc:h2:~/community</url>
                    <user>sam</user>
                    <password>123</password>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>com.h2database</groupId>
                        <artifactId>h2</artifactId>
                        <version>1.4.197</version>
                    </dependency>
                </dependencies>
            </plugin>
```


### bash速查
```bash
mvn flyway:migrate
mvn flyway:repair
git status
git commit -m   git push  git add .
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```