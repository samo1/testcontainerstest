package sk.valiss.test;

import java.util.function.Consumer;

import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import com.github.dockerjava.api.command.CreateContainerCmd;

public class TestContainerTest {

    private static final Logger log = LoggerFactory.getLogger(TestContainerTest.class);
    private static final long ONE_GB = 1024L * 1024L * 1024L;
    private static final Consumer<CreateContainerCmd> shmSizeModifier = cmd -> cmd.getHostConfig().withShmSize(ONE_GB);
    private static final Consumer<OutputFrame> logConsumer = new Slf4jLogConsumer(log);

    @ClassRule
    public static OracleContainer oracle = (OracleContainer) new OracleContainer("oracle/database:11.2.0.2-xe")
            .withCreateContainerCmdModifier(shmSizeModifier)
            .withLogConsumer(logConsumer);

    @ClassRule
    public static MSSQLServerContainer mssql = (MSSQLServerContainer) new MSSQLServerContainer("microsoft/mssql-server-linux:2017-GDR")
            .withLogConsumer(logConsumer);

    @Test
    public void test1() {
        log.info("Hello");
    }
}
