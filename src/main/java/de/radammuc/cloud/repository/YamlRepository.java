package de.radammuc.cloud.repository;

import de.radammuc.cloud.config.PollsProperties;
import de.radammuc.cloud.domain.Poll;
import de.radammuc.cloud.domain.PollData;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class YamlRepository {

    private final PollsProperties pollsProperties;

    private final List<Poll> polls = new ArrayList<>();

    public YamlRepository(PollsProperties pollsProperties) {
        this.pollsProperties = pollsProperties;
    }

    @PostConstruct
    void init() {
        int pollId = 1;

        for (PollsProperties.PollProps prop : pollsProperties.polls()) {
            Poll poll = new Poll(pollId++, new ArrayList<>(), prop.name(), prop.description(), prop.maxSelections());

            int dataId = 1;

            for (String option : prop.options()) {
                PollData pollData = new PollData(dataId++, option, 0);
                poll.pollData().add(pollData);
            }

            polls.add(poll);
        }
    }

    public List<Poll> getPolls() {
        return polls;
    }

    public Poll getPoll(int pollId) {
        return polls.stream().filter(poll -> poll.id() == pollId).findFirst().orElseThrow(() -> notFound(Poll.class, pollId));
    }

    private IllegalArgumentException notFound(Class<?> clazz, int id) {
        return new IllegalArgumentException(clazz.getSimpleName() + " with id " + id + " not found");
    }
}
