package hello;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.doma.repository.config.EnableDomaRepositories;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@SpringBootApplication
@EnableDomaRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Dialect dialect() {
        return new H2Dialect();
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).addScript("import.sql")
                .build();
        return new TransactionAwareDataSourceProxy(dataSource);
    }

    @Bean
    public Config confg(final Dialect dialect, final DataSource dataSource) {
        return new Config() {

            @Override
            public Dialect getDialect() {
                return dialect;
            }

            @Override
            public DataSource getDataSource() {
                return dataSource;
            }
        };
    }

}
