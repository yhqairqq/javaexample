package com.ako.example.dubbo.nio;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public class CommunicationParam {

    private String              ip;                                 // 通讯ip
    private int                 port;                               // 通讯端口
    private CummunicationMethod comMethod = CummunicationMethod.RMI; // 通讯方式

    /**
     * 远程通讯方式
     */
    private static enum CummunicationMethod {
        RMI;
    }

    // ================ setter / getter ====================

    public CummunicationMethod getComMethod() {
        return comMethod;
    }

    public void setComMethod(String comMethod) {
        CummunicationMethod.valueOf(comMethod);
    }

    public void setComMethod(CummunicationMethod comMethod) {
        this.comMethod = comMethod;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    // ==================== hashcode & equals ===================

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comMethod == null) ? 0 : comMethod.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + port;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CommunicationParam)) {
            return false;
        }
        CommunicationParam other = (CommunicationParam) obj;
        if (comMethod == null) {
            if (other.comMethod != null) {
                return false;
            }
        } else if (!comMethod.equals(other.comMethod)) {
            return false;
        }
        if (ip == null) {
            if (other.ip != null) {
                return false;
            }
        } else if (!ip.equals(other.ip)) {
            return false;
        }
        if (port != other.port) {
            return false;
        }
        return true;
    }
}
