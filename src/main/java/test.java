import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.UUID;

public class test {
    public static void main(String[] args) throws Exception {
        Properties properties=new Properties();
        properties.load(test.class.getResourceAsStream("druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        PreparedStatement statement = dataSource.getConnection().prepareStatement("insert into user(name) values (?)");
            int j=0;
        for (int i = 0; i < 100000000; i++) {
             j++;
             if (j==1000){
                 Thread.sleep(10);
                 j=0;
             }
            statement.setString(1, "zhangsan"+i);
            statement.execute();
        }
    }
}
