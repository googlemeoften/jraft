package cn.edu.jraft.core;

import cn.edu.jraft.Node;
import cn.edu.jraft.common.NodeStatus;
import cn.edu.jraft.common.PeerId;
import cn.edu.jraft.common.RaftScheduler;
import cn.edu.jraft.entry.AppendEntriesRequest;
import cn.edu.jraft.entry.Message;
import cn.edu.jraft.entry.RequestVoteRequest;
import cn.edu.jraft.rpc.RaftClientService;
import cn.edu.jraft.rpc.RaftServerService;
import cn.edu.jraft.rpc.RpcContext;
import cn.edu.jraft.rpc.factory.RaftRpcServerFactory;
import cn.edu.jraft.rpc.impl.BoltRaftClientService;
import com.alipay.remoting.rpc.RpcServer;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class DefaultNode implements Node, RaftServerService {
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final AtomicBoolean start = new AtomicBoolean(false);

    private NodeStatus status;

    private PeerId leaderId;

    // raft client
    private RaftClientService raftClientService;

    private int currentTerm;

    private long lastLeaderTimestamp;

    // 已投票
    private PeerId votedId;

    private RaftScheduler electionScheduler;

    private RaftScheduler heartBeatScheduler;

    private RpcServer rpcServer;


    private DefaultNode(Builder builder) {
        init(builder);
    }

    private void init(Builder builder) {
        currentTerm = 0;

        if (!start.compareAndSet(false, true)) {
            return;
        }

        status = NodeStatus.FOLLOWER;
        raftClientService = new BoltRaftClientService();

        rpcServer = RaftRpcServerFactory.createRaftRpcServer(builder.serverPort, Executors.newFixedThreadPool(CORE_POOL_SIZE));

        electionScheduler = new RaftScheduler("raft-heartBeat-scheduler", builder.heartBeatTimeoutMS) {
            @Override
            protected void onTrigger() {
                // 超时选举
            }
        };

        heartBeatScheduler = new RaftScheduler("raft-election-scheduler", builder.electionTimeoutMS) {
            @Override
            protected void onTrigger() {
                // 心跳检测
            }

            @Override
            protected int adjustTimeout(int timeoutMs) {
                return randomTimeout(builder.electionTimeoutMS, builder.maxElectionDelayMs);
            }
        };

    }

    public PeerId getLeader() {
        return null;
    }

    public boolean isLeader() {
        return false;
    }

    public Message handlePreVoteRequest(RequestVoteRequest request) {
        return null;
    }

    public Message handleRequestVoteRequest(RequestVoteRequest request) {
        return null;
    }

    public Message handleAppendEntriesRequest(AppendEntriesRequest request, RpcContext context) {
        return null;
    }

    private int randomTimeout(int timeoutMs, int maxElectionDelayMs) {
        return ThreadLocalRandom.current().nextInt(timeoutMs, timeoutMs + maxElectionDelayMs);
    }

    public static final class Builder {
        private static final int DEFAULT_ELECTION_TIMEOUTMS = 15 * 1000;
        private static final int DEFAULT_HEARTBEAT_TIMEOUTMS = 5 * 1000;
        private static final int MAX_ELECTION_DELAYMS = 1000;

        private int serverPort;
        private int electionTimeoutMS;
        private int heartBeatTimeoutMS;
        private int maxElectionDelayMs;

        private Builder() {
        }

        public Builder serverPort(int serverPort) {
            this.serverPort = serverPort;
            return this;
        }

        public Builder electionTimeoutMS(int electionTimeoutMS) {
            this.electionTimeoutMS = electionTimeoutMS;
            return this;
        }

        public Builder heartBeatTimeoutMS(int heartBeatTimeoutMS) {
            this.heartBeatTimeoutMS = heartBeatTimeoutMS;
            return this;
        }

        public Builder maxElectionDelayMs(int maxElectionDelayMs) {
            this.maxElectionDelayMs = maxElectionDelayMs;
            return this;
        }

        public DefaultNode build() throws Exception {

            if (serverPort <= 0) {
                throw new Exception();
            }

            electionTimeoutMS = electionTimeoutMS > 0 ? electionTimeoutMS : DEFAULT_ELECTION_TIMEOUTMS;
            heartBeatTimeoutMS = heartBeatTimeoutMS > 0 ? heartBeatTimeoutMS : DEFAULT_HEARTBEAT_TIMEOUTMS;
            maxElectionDelayMs = maxElectionDelayMs > 0 ? maxElectionDelayMs : MAX_ELECTION_DELAYMS;

            return new DefaultNode(this);
        }
    }

}
