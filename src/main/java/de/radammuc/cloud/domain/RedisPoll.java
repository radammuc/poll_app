package de.radammuc.cloud.domain;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("poll")
public class RedisPoll {

    private String id;

    private int votes;

    public RedisPoll() {
    }

    public RedisPoll(int pollId, int dataId) {
        id = pollId + ":" + dataId;
        votes = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPollId() {
        return Integer.parseInt(id.split(":")[0]);
    }

    public int getDataId() {
        return Integer.parseInt(id.split(":")[1]);
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
