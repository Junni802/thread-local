package covy.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ThreadLocalTest {

    private FieldService service = new FieldService();

    @DisplayName("ThreadLocal 테스트")
    @Test
    void test1() {
        log.info("main start");
        Runnable userA = () -> {
            service.logic("userA");
        };

        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000); //동시성 문제 발생X
        sleep(100); //동시성 문제 발생O
        threadB.start();

        sleep(3000); //메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class FieldService {

        private ThreadLocal<String> nameStore= new ThreadLocal<>();

        public String logic(String name) {
            log.info("저장 name={} -> nameStore={}", name, nameStore.get());
            nameStore.set(name);

            sleep(1000);
            log.info("조회 nameStore={}", nameStore.get());
            String nameStore1 = nameStore.get();
            nameStore.remove();
            return nameStore1;
        }

        private void sleep(int millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
