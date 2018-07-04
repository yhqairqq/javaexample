package com.ako.example.jdk.jmx;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by yanghuanqing@wdai.com on 23/08/2017.
 */
public class JmxDemo {

    public static void main(String[] args) {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor desc : vms) {
            VirtualMachine vm;
            try {
                System.out.println("desc:" + desc);
                System.out.println("进程id:"+desc.id());
                vm = VirtualMachine.attach(desc);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            JMXConnector connector = null;
            try {
                Properties props = vm.getAgentProperties();
                for (Map.Entry<Object, Object> entry : props.entrySet()) {
                    System.out.println(entry.getKey() + "->" + entry.getValue());
                }

                String connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
                if (connectorAddress == null) {
                    System.out.println("connectorAddress  is  null");
                    continue;
                }
                System.out.println("conn:" + connectorAddress);
                //以下代码用于连接指定的jmx，本地或者远程
                JMXServiceURL url = new JMXServiceURL(connectorAddress);
                //JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/TestJMXServer");
                connector = JMXConnectorFactory.connect(url);

                MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
                Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
                // ...
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connector != null) connector.close();
                    break;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
