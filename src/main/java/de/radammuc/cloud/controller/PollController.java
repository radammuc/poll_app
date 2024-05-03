package de.radammuc.cloud.controller;

import de.radammuc.cloud.domain.Poll;
import de.radammuc.cloud.domain.PollData;
import de.radammuc.cloud.domain.SelectedOptions;
import de.radammuc.cloud.service.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;

@RestController
@RequestMapping("polls")
class PollController {

    Logger log = LoggerFactory.getLogger(PollController.class);

    private final PollService pollService;

    PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAll();
    }

    @GetMapping(path = "{pollId}")
    public Poll getPoll(@PathVariable int pollId) {
        return pollService.find(pollId);
    }

    @PutMapping(path = "/{pollId}")
    public Poll selectOptions(@PathVariable int pollId, @RequestBody SelectedOptions selectedOptions, @RequestHeader("origin") String origin) {
        log.info("TODO - save origin {} to avoid multiple votes", origin);
        return pollService.selectOptions(pollId, selectedOptions);
    }

    @DeleteMapping(path = "{pollId}")
    public ResponseEntity<?> removePoll(@PathVariable int pollId) throws OperationNotSupportedException {
        boolean removed = pollService.remove(pollId);

        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "{pollId}/{dataId}")
    public ResponseEntity<?> removePollData(@PathVariable int pollId, @PathVariable int dataId) throws OperationNotSupportedException {
        boolean removed = pollService.removeData(pollId, dataId);

        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Poll addPoll(@RequestBody Poll poll) throws OperationNotSupportedException {
        return pollService.add(poll);
    }

    @PostMapping(path = "{pollId}")
    public PollData addPollData(@PathVariable int pollId, @RequestBody PollData pollData) throws OperationNotSupportedException {
        return pollService.addData(pollId, pollData);
    }

    @PutMapping
    public void updatePoll(@RequestBody Poll poll) throws OperationNotSupportedException {
        pollService.update(poll);
    }

    @PutMapping(path = "data/{pollId}")
    public void updateData(@PathVariable int pollId, @RequestBody PollData pollData) throws OperationNotSupportedException {
        pollService.updateData(pollId, pollData);
    }
}
