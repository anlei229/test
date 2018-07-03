package com.shuyun.spectrum.kubernetes.registrator.k8s;

import com.shuyun.spectrum.service.spec.v4.Metadata;
import com.shuyun.spectrum.service.spec.v4.Runtime;
import com.shuyun.spectrum.service.spec.v4.Service;

public class KuberneteServiceUtils {

	
	private Service KuberneteServiceToSpectrum(io.fabric8.kubernetes.api.model.Service service)
	{
		Service serviceSpectrum = new com.shuyun.spectrum.service.spec.v4.Service();
		
		serviceSpectrum.setKind(service.getKind());
		Metadata metadata = new Metadata();
		metadata.setApiVersion(service.getApiVersion());
		metadata.setName(service.getMetadata().getName());
		serviceSpectrum.setMetadata(metadata);
		Runtime runtime = new Runtime();
		runtime.setCluster(service.getMetadata().getClusterName());
		serviceSpectrum.setRuntime(runtime);
		return null;
	}
}
