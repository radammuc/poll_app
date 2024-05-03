package de.radammuc.cloud.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Poll(int id, List<PollData> pollData, String title, String description, int maxSelections) {
    public Poll {
        pollData = pollData == null ? new ArrayList<>() : pollData;
        maxSelections = Math.max(maxSelections, 1);
    }
}
