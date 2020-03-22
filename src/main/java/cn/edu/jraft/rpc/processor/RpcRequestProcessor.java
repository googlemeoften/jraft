package cn.edu.jraft.rpc.processor;

import cn.edu.jraft.Node;
import cn.edu.jraft.entry.BaseRequest;
import cn.edu.jraft.entry.Message;
import cn.edu.jraft.rpc.RaftServerService;
import cn.edu.jraft.rpc.RpcContext;
import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.AsyncUserProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;

@Slf4j
public abstract class RpcRequestProcessor<T extends BaseRequest> extends AsyncUserProcessor<T> {

    private final Executor executor;

    public RpcRequestProcessor(Executor executor) {
        super();
        this.executor = executor;
    }

    public abstract Message doProcessRequest(RaftServerService raftServerService, T request, RpcContext context);

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, T request) {
        try {
            Message msg = this.doProcessRequest((RaftServerService) Node.getInstance(), request, new RpcContext(bizCtx, asyncCtx));
            if (msg != null) {
                asyncCtx.sendResponse(msg);
            }
        } catch (final Throwable t) {
            log.error("handleRequest {} failed", request, t);
        }
    }

    @Override
    public Executor getExecutor() {
        return executor;
    }
}
