import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/4.
 */
public class MainLauncher {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans.xml");
        Object target = applicationContext.getBean("target");
        System.out.println(target);
    }

}
