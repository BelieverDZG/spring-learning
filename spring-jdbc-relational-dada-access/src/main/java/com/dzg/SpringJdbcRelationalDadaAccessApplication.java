package com.dzg;

import com.dzg.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现CommandLineRunner接口，在应用程序上下文加载完成后，自动执行run方法
 *
 * spring boot支持内存模式的关系数据库引擎H2，并自动创建连接
 */
@SpringBootApplication
public class SpringJdbcRelationalDadaAccessApplication implements CommandLineRunner {

    public static final Logger logger = LoggerFactory.getLogger(SpringJdbcRelationalDadaAccessApplication.class);

    @Autowired //创建一个JDBC模板
    JdbcTemplate jdbcTemplate;//spring-boot-starter-jdbc中的模板，用来操作关系型数据

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcRelationalDadaAccessApplication.class, args);
    }


    /**
     * 1、首先使用jdbc模板的execute方法，用DDL来创建一个数据表格
     * 2、使用J8数据流的方式，将一组名称数据分为两个数组对的形式
     * 3、批量添加数据
     * 4、使用query来查询复合要求的 数据
     * @param strs
     * @throws Exception
     */
    @Override
    public void run(String... strs) throws Exception {

        logger.info("创建数据表格");
        jdbcTemplate.execute("DROP TABLE tb_customer IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE tb_customer(" +
                "id SERIAL,first_name varchar(100),last_name varchar(100))");

        //将一组用名称划分为first/last两个数组
        List<String> strings = Arrays.asList("Da Dan", "li lade", "deng tianyi", "xiao yangfan");
        List<Object[]> splictUpNames = strings.
                stream().
                map(name -> name.split(" ")).
                collect(Collectors.toList());

        //使用Stream流，输出每一个表单项
        splictUpNames.forEach(name ->logger.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        //批量插入元素数据
        jdbcTemplate.batchUpdate("INSERT INTO tb_customer(first_name,last_name) values(?,?)", splictUpNames); //SQL使用？占位符，防止sql注入

        logger.info("Query for customer records where first_name = 'xiao'：");

        RowMapper<Customer> customerRowMapper = (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));

        jdbcTemplate.query("SELECT * FROM tb_customer where first_name = ?",
                new Object[]{"xiao"},
                customerRowMapper)
        .forEach(customer -> logger.info(customer.toString()));

    }
}
