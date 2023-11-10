package org.zerock.jdbcex.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;

public enum ConnectionUtil {

    INSTANCE;
    private HikariDataSource ds;

    private ConnectionUtil(){
        HikariConfig config = new HikariConfig();
        //Hikari는 connection pooling을 제공하는 JDBC DataSource의 구현체입니다.
        //데이터소스(DataSource)는 물리적인 데이터베이스에 연결하기 위한 팩토리입니다.
        //객체를 생성했으니 밑에는 연결해주는 설정

        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

         ds = new HikariDataSource(config);

    }

    public Connection getConnection() throws Exception{

        return this.ds.getConnection();
    }
}
