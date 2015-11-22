package cz.zjor.toys.polychat.service;

import cz.zjor.toys.polychat.auth.User;
import cz.zjor.toys.polychat.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context-test.xml"})
public class ChatServiceTest {

    public static final String MSG_CONTENT = "hello world";
    @Inject
    private ChatService chatService;

    private User alice, bob;

    @Before
    public void setUp() {
        alice = new User("Alice");
        bob = new User("Bob");
    }

    @Test
    public void shouldCreateAndFetch() {
        String firstId = chatService.sendMessage(alice, "first").getId();
        String id = chatService.sendMessage(bob, "second").getId();
        List<Message> ms = chatService.fetch(id, 15);
        Assert.assertEquals(firstId, ms.get(0).getId());
        Assert.assertEquals(1, ms.size());
    }

    @Test
    public void shouldPoll() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        Future<List<Message>> f1 = executors.submit(() -> chatService.poll(null));
        Future<List<Message>> f2 = executors.submit(() -> chatService.poll(null));
        executors.submit(() -> {
            Thread.sleep(500L);
            chatService.sendMessage(alice, MSG_CONTENT);
            return null;
        });

        Message m1 = f1.get(5, TimeUnit.SECONDS).get(0);
        Message m2 = f2.get(5, TimeUnit.SECONDS).get(0);
        Assert.assertEquals(MSG_CONTENT, m1.getContent());
        Assert.assertEquals(MSG_CONTENT, m2.getContent());

        executors.shutdownNow();
        executors.awaitTermination(5, TimeUnit.SECONDS);
    }

}
