package cn.leon.kubernetes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class KubernetesClientController {

    @Autowired
    private DiscoveryClient discoveryClient;
/*
    @Autowired
    private ServiceInstance serviceInstance;

    @Autowired
    private ServiceRegistry serviceRegistry;

    @Autowired
    private Registration Registration;
*/


    @GetMapping("services")
    public void getServices() {
        System.out.println(discoveryClient.getServices());
    }

    @GetMapping("kubernetes")
    public void create() throws IOException {
       /* ApiClient apiClient = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader("/Users/mujian/IdeaProjects/personal/springboot-study/jgit/src/main/resources/config"))).build();
        V1Service svc = new V1ServiceBuilder()
                .withNewMetadata()
                .withName("kubernetes")
                .endMetadata()
                .withNewSpec()
                .addNewPort()
                .withProtocol("TCP")
                .withPort(80)
                .withTargetPort(new IntOrString(8080))
                .endPort()
                .withSelector(selector)
                .endSpec()
                .build();

         Deployment and StatefulSet is defined in apps/v1, so you should use AppsV1Api instead of CoreV1API
        CoreV1Api api = new CoreV1Api(apiClient);
        V1Service v1Service = api.createNamespacedService("kong", svc, null, null, null);
        */
    }

    @GetMapping("fabric8")
    public void createServiceByFabric8() {
        /*Config config = new ConfigBuilder()
                .withMasterUrl("https://172.16.255.5:6443")
                .withNamespace("kong")
                .withApiVersion("v1")
                .withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUMvakNDQWVhZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJeE1UQXlNREEyTWpZME1Wb1hEVE14TVRBeE9EQTJNalkwTVZvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTjlSClh5SnBqRWdXTFFCS0VQbXkwbVM1ZnRCSlhjYkoya3ljSG5Wb3NvYXE1VTB6ZUpEeFVNaTdSYTV1NTBlWkdlZnIKSmpKamhXVjZTZ1ZyclpHSitYaGpZYS8zNnRva1BJUDd3dTJSUUt0OHI1TXd2TkFlcmNoNHJnSTQ0cDVlamJEUwpBdWdRYkJqZ2NUWkJtZ1JFdjVFMHcyT2VNckZPeUpYclYxWTErN1hmUDlPeVJETm01azg4aDRZZXo0RW10dlVuCmtEU01vb2tNcXBSMitkRVk0Q0lBVHJlUTJTeFVKcUlBOWNwTGxuZ2FxbWJlRFZMcWNuZEc1R09TSHNKQW9xVlgKNzhwcjVud1Y1S3NiWDRQUmFEL05IbndJOC80K2JXUks4KzV1ekNEemc1blJzRWIyaUxja3VTWWhVZ2E2eitwVgo4b2NCeEc1MCt3NmtadytoYTNFQ0F3RUFBYU5aTUZjd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0hRWURWUjBPQkJZRUZMOVR2ZkhjVnZsOFY2Ti9FSDEyVWpnRk1IY0JNQlVHQTFVZEVRUU8KTUF5Q0NtdDFZbVZ5Ym1WMFpYTXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBTFZyM1hvM2F1UGQ5dUo2Vk1nUgpCeXAzaFVIVnZxdjV6TTN1MDFHZ1FLaURzeXFscUhmUG4vdUNlY0ZCWGxOMmlYUWxQbzZhQURwb09EU0NuKzdoCmoyc2hlNFFEWm83M21WeEllZmM4M1huZnB4T1B4T0hKUXU4MHp0a1J0bmUxSXhOL0tKVWZGazJiZktXYjlSTE8KVjEzRkpXdlArUHA3UVlVRlhBZFF1eDdJMWpyaXgxQWRZN1JGV0NmaHZ0UDB2TTQydjBBbDBqOERZSGNOMFQxNgpyZ0lsMVRNbmtHaDBSUXJwRVVTcHAwdlZqdmpSNXNZSWZ4MzVqQXhuVVhEYzNqK0tTZlBTalllOFcrbXQydGs3CklFK3B6ZUoxclg2ekFrSG93UmpFd2FTQTdUT2pyNkd6dWJSL1JDNTQySkZxbUU4NGdJWHg5MDFiTDF0R0xKWnIKcklJPQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==")
                .withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURJVENDQWdtZ0F3SUJBZ0lJSlpPeVVnbFltM2N3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TVRFd01qQXdOakkyTkRGYUZ3MHlNakV3TWpBd05qSTJORFJhTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQTFFczZoNFpXR21vTHZ0YVEKd3hEaE1ZdmZpelptNVBoYWxJTklZQjdrdXk3cGM4SG1IVEZWZ3VqMlh1OWl3Qzk3NjB4aEFDQUZmNXFCd1d4UAo3MzV5RStpWnN6TlF1aHZxeFR5YVpoVE1Yc3BuNldQbDVXSkxNRDZaODNpb3k4TFI5MDlvNldGSlJ0TU9MN3lyCnpqMmgvcWtSS3JuekRlb3ViMmlEaHQ3M25wTlU4ajFRRGVvS05Qcnh0ZTZkbDFHK3l1UmoxUFJiZ0QybWRNUUsKblE0cm8yVHBsRWlmSHRXZFhwMkVNSkJBQ0RhVWgvbnc2VWlRYmVYWEFDVzdLT3Q1dXFXdGk5ckFMYk9ueGxRNwplUi9rQlBrK3h3ek9RZW5TT0ZlRnlwTitwUEUwWVVJZnc0WXlDMkM4QUI3V1VQQ3J1d0czWVBDRlVaVml2MmVkCkd4UkRTUUlEQVFBQm8xWXdWREFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RBWURWUjBUQVFIL0JBSXdBREFmQmdOVkhTTUVHREFXZ0JTL1U3M3gzRmI1ZkZlamZ4QjlkbEk0QlRCMwpBVEFOQmdrcWhraUc5dzBCQVFzRkFBT0NBUUVBaGJ2UktHZG1BS0NrRU0wQkpJMVNMdGVTbnhPd0pkeDlQOTdCCld1c2hVSzNsTVJMNG9rbjBuMlhoaEN0Vlh2Y3VFVFowSFBIL0FRTWkxZHFwUCt4SkZ1b0dseUcxMy96THQ0MXEKeEZWcDJQMThWZVFrdHdydksvdWFGYzFXSEZ2SkZONVd5TlZ4S2FHRTIyTDlKVmZVdWlaS1BEY0wrRUJCMGY5QgpSTDhVZUM1VFFIQkpSZ1U4U1NVVWptMFV4Qml0VmdlYTZIcVdVTFE1ZUxDNDZxL3pEeURieUFhTUtzaVFJcDg1Ci9PaDFuU05kVENiN0Q5VFZIYnpWc1NCOU9obVI2Z3FsQkZPcDhPNVpsSWFNNlFyQVVHeHF0UVd5bEhPQjYwT2cKRFRoVUVBd21SUFZYNWJMcGJ0cFNNOVVUZE9iZ2dNc1N3eXFlQnJwUGtjZzliSER1OGc9PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==")
                .withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb3dJQkFBS0NBUUVBMUVzNmg0WldHbW9MdnRhUXd4RGhNWXZmaXpabTVQaGFsSU5JWUI3a3V5N3BjOEhtCkhURlZndWoyWHU5aXdDOTc2MHhoQUNBRmY1cUJ3V3hQNzM1eUUraVpzek5RdWh2cXhUeWFaaFRNWHNwbjZXUGwKNVdKTE1ENlo4M2lveThMUjkwOW82V0ZKUnRNT0w3eXJ6ajJoL3FrUktybnpEZW91YjJpRGh0NzNucE5VOGoxUQpEZW9LTlByeHRlNmRsMUcreXVSajFQUmJnRDJtZE1RS25RNHJvMlRwbEVpZkh0V2RYcDJFTUpCQUNEYVVoL253CjZVaVFiZVhYQUNXN0tPdDV1cVd0aTlyQUxiT254bFE3ZVIva0JQayt4d3pPUWVuU09GZUZ5cE4rcFBFMFlVSWYKdzRZeUMyQzhBQjdXVVBDcnV3RzNZUENGVVpWaXYyZWRHeFJEU1FJREFRQUJBb0lCQURhcHhFRmZRczU4WGRmZQowZG1ZZ0JQTTYxdWlQMjVYMEtNTEJ0alJPNTVESHhPU2F0azdlY1JDamlXMXlCeXFYV01pQUp3U29yZlBwZmVUCkZ5WVl6ZkQ0WkpNeTlHZG5odFVRbk1VS04vRjBkcnN1NE9SbW9ldWpRdXJoTjcxM3hhN0RnTVVQS3RvVXdFTmsKZG15ZGpCZlJtcjBBc21mejA4OFYvei84SzVCbDhROUdOR3RWZmRROThyaTR6QWZQK1liM09aajlkRnVmenNrRQpReUZTR09LcVJ5YUgrbUZ1MEZDdmRJaXhSZ09wbk4xUmMrWFJkQUdUKy9qc2docTdKRVB0SW9OTko0QU9kWEUxCmtuVFFkM1RrcWR0cjc3SjNodXRMOFpBV1NvREl5em1zYjB2VHFGUjlNZVV3RGtYU3FNZmU1NEdBb3Zib2w3MzgKbTF5SUFZRUNnWUVBK2psdEtDdjhPd0Q1MFRsUDNvS0ZiMEhMc1U1d0JCMWF6MTI4eDd5SlZMdmhJb2dzemxiRAprNDBYQ01rN0pMWXlYMWNNL3FwVGRjZzNIaFdLaHpWNFFIOW05a1dJbFhZYUw4cXdkSGhFbFhVYUhaZ3FRME9mCndiOHNsbEZkejdpTk1DQlVjY2l3M3FJRE1RWHlQc2pvblAvelFESkhabFMvdkZxUnEwbkp3VEVDZ1lFQTJUR3IKNDFtTWRXbnNXL1dyK280VnluSmdBVFg5NlZMMFpybkdmeWU5aHl3b2dBMklZRVdXVUFqWENJdnBQL1V4MFN5RgpkMFZTM0JHbWNqVnZOVnV5ZUFrbGR6dmppRWpxeGppN0NlSTJ0RW4rdTdqd1BXK3ZOdXJ1c2k5SlpWU3V0eUt5CjlKL1AwUXprM3pabXU2T0JqRUhwSkFiODZrTW43SmYwWHY4aFhaa0NnWUJJVFhtdUp1K09PUnoyaU9IOFFLaS8KMmpvNmRKakhoNWZxM1lJa0VPVjUxNFFaVDFIckdZVVB5TEFFT09sZkZVcCs1QTk4TXNsRSttSjZ6ZXltWHh2dApQeU1JUEZWWUlyVTBaQlFTdzRIRjBmMkcyYVkwbzRDeElrV0c5ZUh1bUx3clVOdzF1TERxMDlPcVFiYWF6OER2ClJNSU1KYnNkNVpVMW50SUN0YUtYc1FLQmdHSjJBQ1JxM2lBRVN1Qk5lUTkzQmkzbXQ5c1VyT3p2YkZoOWM1MEYKV1FoRVhuL2VvcXB5SVhoYTZaQzRUSjl2K291SEVXSkdqUSt5K0svaHhXNTlDV1VkVUZ6RFM1em00WGZIRkJPZwo5NGUzMjhFaVQ5YXFJTlpXbnFzc2dLdHRQbDlMWGJGRUdhUDlvbXdHYjRBMDJXL0tHWFQ1cmdKTk8zWW9WT0lUCjZ2NVJBb0dCQU1XaVBnU3JjS09iZTdKVFRRWEt6ZnZXS3dpNnVhUXN1LzJ5QmQyNTJzM2tCOFhtTjFVSFJiU1EKYnFmdFFLaSsxN1F4QW9uZGF6RUYxVVY1VVp3akZkMEszL2VPclNubGRvSmdKQVJzdGlSbDAwd2NtcjRjeUtZdgoxd3ZleWdreitlaW1ZOHBua25VMmw1YmxTVXZWcVdYb3RHcFNTaEQ5OXhOUGp4MWlpa3J1Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg==")
                .build();
        KubernetesClient client = new DefaultKubernetesClient(config);
        ServiceSpec serviceSpec = new ServiceSpec();
        ServicePort servicePort = new ServicePort();
        servicePort.setNodePort(8080);
        servicePort.setTargetPort(new IntOrString(8080));
        serviceSpec.setType("NodePort");
        ArrayList arrayList = new ArrayList() {{
            add(servicePort);
        }};
        serviceSpec.setPorts(arrayList);
        serviceSpec.setHealthCheckNodePort(8081);
        Service jgit = client.services()
                .inNamespace("kong")
                .create(new ServiceBuilder()
                        .withNewMetadata()
                        .withName("jgit")
                        .addToLabels("another", "label")
                        .endMetadata().withSpec(serviceSpec)
                        .build());
        client.events().inAnyNamespace().watch(new Watcher<Event>() {
            @Override
            public void eventReceived(Action action, Event resource) {
                System.out.println("event " + action.name() + " " + resource.toString());
            }

            @Override
            public void onClose(KubernetesClientException cause) {
                System.out.println("Watcher close due to " + cause);
            }
        });*/
    }
}
