package com.iqmsoft.payara;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.iqmsoft.payara.SampleMessage;

import java.util.Random;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Starting Hazelcast");
        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        IQueue<SampleMessage> queue = client.getQueue("auditQueue");
        System.out.println("Queue size: " + queue.size());
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        while (true) {
            String wait = input.nextLine();
            if (wait.startsWith("q")) break;
            SampleMessage message = new SampleMessage();
            message.setMessage(Integer.toString(rand.nextInt(100)));
            if (queue.offer(message)) System.out.println("Placed message in queue: " + message.getMessage());
            else System.out.println("Failed to place message in queue.");
        }
        client.shutdown();
    }
}
