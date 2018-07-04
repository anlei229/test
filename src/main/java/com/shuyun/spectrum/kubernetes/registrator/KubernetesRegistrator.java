package com.shuyun.spectrum.kubernetes.registrator;

import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shuyun.spectrum.kubernetes.registrator.k8s.KuberneteService;



/**
 * k8s注册中心
 * @author anlei
 *
 */
@SpringBootApplication
public class KubernetesRegistrator {

	public static void main(String[] args) throws Exception {
		KuberneteService service = new KuberneteService();
		service.getService();
		
	}
}






