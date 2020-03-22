package cn.edu.jraft.rpc.factory;

import cn.edu.jraft.rpc.processor.AppendEntriesRequestProcessor;
import cn.edu.jraft.rpc.processor.RequestVoteRequestProcessor;
import com.alipay.remoting.ConnectionEventType;
import com.alipay.remoting.rpc.RpcServer;

import java.util.concurrent.Executor;

public class RaftRpcServerFactory {
    public static RpcServer createRaftRpcServer(int port, Executor raftExecutor) {
        final RpcServer rpcServer = new RpcServer(port, true, true);
        addRaftRequestProcessors(rpcServer, raftExecutor);

        return rpcServer;
    }

    public static void addRaftRequestProcessors(RpcServer rpcServer, Executor raftExecutor) {
        // raft core processors
        final AppendEntriesRequestProcessor appendEntriesRequestProcessor = new AppendEntriesRequestProcessor(raftExecutor);
        rpcServer.addConnectionEventProcessor(ConnectionEventType.CLOSE, appendEntriesRequestProcessor);
        rpcServer.registerUserProcessor(appendEntriesRequestProcessor);
        rpcServer.registerUserProcessor(new RequestVoteRequestProcessor(raftExecutor));
        rpcServer.registerUserProcessor(new RequestVoteRequestProcessor(raftExecutor));

    }
}
