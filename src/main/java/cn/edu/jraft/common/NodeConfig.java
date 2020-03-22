package cn.edu.jraft.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class NodeConfig {
    private int serverPort;

    public Set<PeerId> peerIds;
}
