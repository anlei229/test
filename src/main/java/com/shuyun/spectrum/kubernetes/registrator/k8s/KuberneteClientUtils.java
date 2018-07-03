package com.shuyun.spectrum.kubernetes.registrator.k8s;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shuyun.k8sClient.K8sClient;
import com.shuyun.spectrum.kubernetes.registrator.KubernetesRegistrator;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import okhttp3.TlsVersion;


@Component
public class KuberneteClientUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(KuberneteClientUtils.class);

	private static final Object LOCK = new Object();
	private static String region;
	private static String cluster;
	private static String serviceName;
	private static String appVersion;
	
	private  static KubernetesClient client = null;

	public static KubernetesClient getKuberneteClient() throws Exception
	{
		synchronized(LOCK)
		{
			if(client!=null)
			{
				return client;
			}
			Properties pro = getSystemProperties();
			String serverUrls = pro.getProperty("system.k8s.address");
			String caCertFile = pro.getProperty("system.k8s.caCertFile");
			String clientCertFile = pro.getProperty("system.k8s.clientCertFile");
			String clientKeyFile = pro.getProperty("system.k8s.clientKeyFile");
			String namespace = pro.getProperty("system.k8s.namespace");
			client = new K8sClient(getConfig(namespace, serverUrls, caCertFile, clientCertFile, clientKeyFile));
			return client;
		}
	}
 
	
	private static Properties getSystemProperties() throws IOException
	{
		Properties properties = new Properties();
		InputStream resourceAsStream = KubernetesRegistrator.class.getClassLoader().getResourceAsStream("application.properties");
		properties.load(resourceAsStream);
		System.getProperties().forEach((x,y)->
		{
			properties.put(x, y);
		});
		return properties;
	}
	 
	private static Config getConfig(String namespace, String serverUrls, String caCertFile, String clientCertFile, String clientKeyFile)
	{
		return new ConfigBuilder().withMasterUrl(serverUrls)
				.withTrustCerts(true)
				.withNamespace(namespace)
				.withCaCertFile(caCertFile)
				.withClientCertFile(clientCertFile)
				.withClientKeyFile(clientKeyFile)
				.removeFromTlsVersions(TlsVersion.TLS_1_0)
				.removeFromTlsVersions(TlsVersion.TLS_1_1)
				.removeFromTlsVersions(TlsVersion.TLS_1_2)
				.withRequestTimeout(15000)
				.withConnectionTimeout(15000)
				.build();
	}
}
