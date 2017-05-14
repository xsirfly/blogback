package com.zc.blog.helper;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by zhangcong on 2017/5/14.
 */
@Component
@ConfigurationProperties(prefix="elasticsearch")
public class EsHelper {

    private static String domain;

    private static int port;

    public static String index;

    public static String type;

    public static String getDomain() {
        return domain;
    }

    public static void setDomain(String domain) {
        EsHelper.domain = domain;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        EsHelper.port = port;
    }

    public static String getIndex() {
        return index;
    }

    public static void setIndex(String index) {
        EsHelper.index = index;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        EsHelper.type = type;
    }

    public static Client getClient() throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(domain), port));
        return client;
    }
}
