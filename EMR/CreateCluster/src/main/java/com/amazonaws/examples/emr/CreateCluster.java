package com.amazonaws.examples.emr;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClientBuilder;
import com.amazonaws.services.elasticmapreduce.model.*;

public class CreateCluster {
    public static void main(String[] args) throws Exception {

        if(args.length != 1) {
            System.err.println("This program takes exactly one (1) parameter.\nUsage: java -cp CreateCluster.jar com.amazonaws.examples.emr.CreateCluster us-east-1a");
            System.exit(1);
        } else {
            String clusterAz = args[0];

            AmazonElasticMapReduce EmrClient = AmazonElasticMapReduceClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .build();
            try {
                RunJobFlowRequest request = new RunJobFlowRequest()
                        .withName("Java Test Cluster")
                        .withReleaseLabel("emr-5.16.0")
                        .withApplications(new Application()
                                .withName("Hadoop"))
                        .withServiceRole("EMR_DefaultRole")
                        .withJobFlowRole("EMR_EC2_DefaultRole")
                        .withVisibleToAllUsers(true)
                        .withInstances(new JobFlowInstancesConfig()
                                .withEc2KeyName("<SSH-KEY>")
                                .withInstanceCount(2)
                                .withMasterInstanceType("m3.xlarge")
                                .withKeepJobFlowAliveWhenNoSteps(true)
                                .withPlacement(new PlacementType()
                                        .withAvailabilityZone(clusterAz))
                                .withSlaveInstanceType("m3.xlarge"));

                RunJobFlowResult result = EmrClient.runJobFlow(request);
                System.out.println("New cluster: " + result.getJobFlowId());
            } catch (Exception e) {
                System.err.println("Exception occured: " + e);
                System.exit(1);
            }

        }
    }
}
