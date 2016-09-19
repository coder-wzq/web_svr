package cc.blueview.config;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cc.blueview.dao", annotationClass = TargetDataSource.class, sqlSessionFactoryRef = "bvcSqlSessionFactory")
public class JDBCConfig {
	public static final String bvcSqlSessionFactoryName = "bvcSqlSessionFactory";

	@Autowired
	private Environment env;

	@Primary
	@Bean(name = "dataSource", destroyMethod = "close")
	@ConfigurationProperties(prefix = "spring.datasource.bvc")
	public DataSource dataSource() {
		System.out.println("----------" + env.getProperty("spring.datasource.bvc.url"));
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass(env.getProperty("spring.datasource.bvc.driver-class-name"));
		dataSource.setJdbcUrl(env.getProperty("spring.datasource.bvc.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.bvc.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.bvc.password"));
		dataSource.setIdleConnectionTestPeriodInMinutes(20);
		dataSource.setIdleMaxAgeInMinutes(1);
		dataSource.setMaxConnectionsPerPartition(20);
		dataSource.setMinConnectionsPerPartition(1);
		dataSource.setPartitionCount(4);
		dataSource.setAcquireIncrement(5);
		dataSource.setStatementsCacheSize(100);
		return dataSource;

	}

	@Bean
	public DataSourceTransactionManager transactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean(name = "jdbcTemp")
	@Autowired
	public JdbcTemplate bvcJdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Primary
	@Bean(name = bvcSqlSessionFactoryName)
	public SqlSessionFactory sboxSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setTypeAliasesPackage("cc.blueview.domain");
		return sessionFactoryBean.getObject();
	}

}
