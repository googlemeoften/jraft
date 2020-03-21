package cn.edu.jraft.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Endpoint {
    private static final String IP_ANY = "0.0.0.0";
    private String ip = IP_ANY;
    private int port;
    private String url;


}
