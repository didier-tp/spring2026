package tp.conf;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@Profile("jta")
public class AtomikosConfig {
    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager utm = new UserTransactionManager();
        utm.setForceShutdown(false);
        return utm;
    }

    @Bean
    public UserTransactionImp atomikosUserTransaction() throws Exception {
        UserTransactionImp ut = new UserTransactionImp();
        ut.setTransactionTimeout(300);
        return ut;
    }

    @Bean(name="atomikosJtaTransactionManager")
    public JtaTransactionManager atomikosJtaTransactionManager() throws Exception {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager(atomikosUserTransaction(), atomikosTransactionManager());
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        return jtaTransactionManager;
    }
}
