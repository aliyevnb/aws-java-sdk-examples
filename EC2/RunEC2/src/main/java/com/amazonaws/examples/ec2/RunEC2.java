package com.amazonaws.examples.ec2;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

public class RunEC2 {

    public static void main(String[] args) throws Exception {

        if(args.length != 2) {
            System.err.println("Usage: RunEC2 <AMI ID> <subnet>");
            System.exit(2);
        }

        String AMI = args[0];
        String subnetId = args[1];

        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        RunInstancesRequest runInstancesRequest = new RunInstancesRequest();

        runInstancesRequest.withImageId(AMI)
                .withInstanceType("c3.xlarge")
                .withMinCount(1)
                .withMaxCount(1);

        RunInstancesResult result = ec2Client.runInstances(runInstancesRequest);
        System.out.println(result);
    }
}
