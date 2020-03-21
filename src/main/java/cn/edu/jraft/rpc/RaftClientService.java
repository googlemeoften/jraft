package cn.edu.jraft.rpc;

import cn.edu.jraft.common.Endpoint;
import cn.edu.jraft.entry.AppendEntriesRequest;
import cn.edu.jraft.entry.Message;
import cn.edu.jraft.entry.RequestVoteRequest;

import java.util.concurrent.Future;

public interface RaftClientService {

    Future<Message> preVote(Endpoint endpoint, RequestVoteRequest request);

    Future<Message> requestVote(Endpoint endpoint, RequestVoteRequest request);

    Future<Message> appendEntries(Endpoint endpoint, AppendEntriesRequest request);


}
