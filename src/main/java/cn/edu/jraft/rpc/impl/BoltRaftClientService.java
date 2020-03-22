package cn.edu.jraft.rpc.impl;

import cn.edu.jraft.common.Endpoint;
import cn.edu.jraft.entry.AppendEntriesRequest;
import cn.edu.jraft.entry.Message;
import cn.edu.jraft.entry.RequestVoteRequest;
import cn.edu.jraft.rpc.AbstractClientService;
import cn.edu.jraft.rpc.RaftClientService;

import java.util.concurrent.Future;

public class BoltRaftClientService extends AbstractClientService implements RaftClientService {

    public BoltRaftClientService() {
        init();
    }

    public Future<Message> preVote(Endpoint endpoint, RequestVoteRequest request) {
        return (Future<Message>) invoke(endpoint, request);
    }

    public Future<Message> requestVote(Endpoint endpoint, RequestVoteRequest request) {
        return (Future<Message>) invoke(endpoint, request);
    }

    public Future<Message> appendEntries(Endpoint endpoint, AppendEntriesRequest request) {
        return (Future<Message>) invoke(endpoint, request);
    }
}
