package top.haidong556.chat_server.common.util;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

public class NacosServiceUtil {

    private static final String SERVER_ADDR = "your_nacos_server_address";
    private static final String GROUP_ID = "DEFAULT_GROUP"; // or your custom group
    private static final long TIMEOUT_MS = 5000; // Timeout for querying service status

    // Method to get service status
    public static List<Instance> getServiceStatus(String serviceName) {
        try {
            NamingService namingService = NamingFactory.createNamingService(SERVER_ADDR);
            List<Instance> instances = namingService.selectInstances(serviceName, true);
            return instances;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param servicePrefix
     * @return
     * @description Method to get instances of services with given prefix
     */
    public static List<Instance> getServiceInstancesWithPrefix(String servicePrefix) {
        try {
            NamingService namingService = NamingFactory.createNamingService(SERVER_ADDR);
            return namingService.selectInstances(servicePrefix, true);
        } catch (NacosException e) {
            // Handle Nacos exception
            e.printStackTrace();
            return null; // Return null in case of exception
        }
    }

    /**
     * @param serviceName
     * @return 服务名的后缀  eg.chat_server_1(就是1）
     */
    public static String extractSuffixFromServiceName(String serviceName) {
        String[] parts = serviceName.split("_");

        if (parts.length >= 2) {
            // Concatenate all parts except the first one (prefix)
            StringBuilder suffix = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
                suffix.append(parts[i]);
                if (i < parts.length - 1) {
                    suffix.append("_"); // Add "_" between parts
                }
            }
            return suffix.toString();
        } else {
            return null;
        }
    }

}
