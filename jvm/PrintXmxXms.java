import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintXmxXms {
    public static void main(String[] args) {
        float mb = 1024 * 1024;
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        float xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
        float xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
        System.out.println("Initial Memory (xms) : " + xms + "mb");
        System.out.println("Max Memory (xms) : " + xmx + "mb");
    }
}