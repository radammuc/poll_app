package de.radammuc.cloud.service;

import de.radammuc.cloud.domain.Poll;
import de.radammuc.cloud.domain.PollData;
import de.radammuc.cloud.domain.RedisPoll;
import de.radammuc.cloud.domain.SelectedOptions;
import de.radammuc.cloud.repository.RedisRepository;
import de.radammuc.cloud.repository.YamlRepository;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    private final YamlRepository yamlRepository;

    private final RedisRepository redisRepository;

    public PollService(YamlRepository yamlRepository, RedisRepository redisRepository) {
        this.yamlRepository = yamlRepository;
        this.redisRepository = redisRepository;
    }

    public List<Poll> getAll() {
        return yamlRepository.getPolls();
    }

    public Poll find(int pollId) {

        Poll poll = yamlRepository.getPoll(pollId);

        List<PollData> dataWithVotes = new ArrayList<>();

        for (PollData pollData : poll.pollData()) {
            Optional<RedisPoll> redisPoll = redisRepository.findById(pollId + ":" + pollData.id());

            if (redisPoll.isPresent()) {
                dataWithVotes.add(new PollData(pollData.id(), pollData.option(), redisPoll.get().getVotes()));
            } else {
                dataWithVotes.add(pollData);
            }
        }

        return new Poll(pollId, dataWithVotes, poll.title(), poll.description(), poll.maxSelections());
    }

    public Poll selectOptions(int pollId, SelectedOptions selectedOptions) {

        for (Integer dataId : selectedOptions.getIdList()) {
            String key = "poll:" + pollId + ":" + dataId;

            RedisPoll poll = redisRepository.findById(pollId + ":" + dataId).orElse(new RedisPoll(pollId, dataId));
            poll.setVotes(poll.getVotes() + 1);
            redisRepository.save(poll);
        }

        return find(pollId);
    }

    public Poll add(Poll poll) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("add poll");
    }

    public PollData addData(int pollId, PollData pollData) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("add data");
    }

    public boolean remove(int pollId) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("remove poll");
    }

    public boolean removeData(int pollId, int dataId) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("add data");
    }

    public void update(Poll poll) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("update poll");
    }

    public void updateData(int pollId, PollData pollData) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("add data");
    }
}

