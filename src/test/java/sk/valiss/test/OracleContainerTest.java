package sk.valiss.test;

import java.util.function.Consumer;

import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import com.github.dockerjava.api.command.CreateContainerCmd;

public class OracleContainerTest {

    private static final Logger log = LoggerFactory.getLogger(OracleContainerTest.class);
    private static final long ONE_GB = 1024L * 1024L * 1024L;
    private static Consumer<CreateContainerCmd> shmSizeModifier = cmd -> cmd.getHostConfig().withShmSize(ONE_GB);

    @ClassRule
    public static OracleContainer oracle = (OracleContainer) new OracleContainer("oracle/database:11.2.0.2-xe")
            .withCreateContainerCmdModifier(shmSizeModifier)
            .withLogConsumer(new Slf4jLogConsumer(log));

    @Test
    public void test1() {
        log.info("Hello");
    }
}
