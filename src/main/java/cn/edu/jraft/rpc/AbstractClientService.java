package cn.edu.jraft.rpc;

import cn.edu.jraft.common.Endpoint;
import cn.edu.jraft.entry.Message;
import com.alipay.remoting.exception.RemotingException;
import com.alipay.remoting.rpc.RpcClient;
import com.alipay.remoting.rpc.RpcResponseFuture;
import lombok.Getter;

@Getter
public abstract class AbstractClientService {
    private static final int TIMEOUT = 3000;
    protected RpcClient rpcClient;




    public RpcResponseFuture invoke(Endpoint endpoint, Message request) {
        try {
            return rpcClient.invokeWithFuture(endpoint.getUrl(), request, TIMEOUT);
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}