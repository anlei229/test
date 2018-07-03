package com.shuyun.spectrum.kubernetes.registrator.k8s;

import java.util.List;


import io.fabric8.kubernetes.api.model.Service;

public class KuberneteService {

	private KuberneteClientUtils kuberneteClientUtils;
	
	public void getService() throws Exception
	{
		List<Service> items = kuberneteClientUtils.getKuberneteClient().services().list().getItems();
		items.forEach(x->{
			System.out.println(x.getMetadata().getName()+"->"+x.getSpec().getClusterIP()+":"+x.getSpec().getPorts().get(0).getPort());
		});
	}
}
