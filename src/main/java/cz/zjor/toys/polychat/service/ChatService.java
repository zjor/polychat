package cz.zjor.toys.polychat.service;

import cz.zjor.toys.polychat.auth.User;
import cz.zjor.toys.polychat.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class ChatService {

    //TODO: consider synchronization issues: iterating over synchronized collection
    private LinkedList<Message> messages = new LinkedList<>();

    private Set<CompletableFuture<List<Message>>> polls = Collections.synchronizedSet(new HashSet<>());

    /**
     * Appends message to the list.
     * Wakes polling requests.
     * @param sender
     * @param content
     */
    public Message sendMessage(User sender, String content) {
        Message m = new Message(sender, content);
        messages.add(m);
        Iterator<CompletableFuture<List<Message>>> it = polls.iterator();
        while (it.hasNext()) {
            it.next().complete(Collections.singletonList(m));
            it.remove();
        }
        return m;
    }

    /**
     * Returns messages created earlier than the message of edgeId
     * or returns messages from the last message if edgeId is null.
     * @param edgeId
     * @param pageSize
     * @return
     */
    public List<Message> fetch(@Nullable String edgeId, int pageSize) {
        Iterator<Message> it = messages.descendingIterator();
        if (edgeId != null) {
            while (it.hasNext() && !it.next().getId().equals(edgeId));
        }

        List<Message> result = new LinkedList<>();
        while (it.hasNext()) {
            if (result.size() > pageSize) {
                break;
            }
            result.add(it.next());
        }

        return result;
    }

    /**
     * Returns messages created after the message of edgeId
     * or from the beginning if edgeId is null.
     * Blocks result is empty.
     * @param edgeId
     * @return
     */
    public List<Message> poll(@Nullable String edgeId) throws InterruptedException, ExecutionException, TimeoutException {
        Iterator<Message> it = messages.descendingIterator();
        List<Message> result = new LinkedList<>();
        if (edgeId != null) {
            while (it.hasNext()) {
                Message m = it.next();
                if (m.getId().equals(edgeId)) {
                    break;
                } else {
                    result.add(m);
                }
            }
        }
        if (result.isEmpty()) {
            CompletableFuture<List<Message>> future = new CompletableFuture<>();
            polls.add(future);
            result.addAll(future.get(15, TimeUnit.SECONDS));
        }
        return result;
    }

}
