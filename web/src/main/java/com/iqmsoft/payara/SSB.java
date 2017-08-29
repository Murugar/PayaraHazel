package com.iqmsoft.payara;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.iqmsoft.payara.SampleMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;

@Stateless
public class SSB {
    private static final Logger log = LoggerFactory.getLogger(QueueConListener.class);
    @Resource(lookup = "payara/Hazelcast")
    private HazelcastInstance hazelcastInstance;
    private IQueue<SampleMessage> queue;

    public SSB() {
    }

    @PostConstruct
    private void initialise() {
        if (hazelcastInstance == null) throw new RuntimeException("Hazelcast not available.");
        queue = hazelcastInstance.getQueue("auditQueue");
    }

    public IQueue<SampleMessage> getQueue() {
        return queue;
    }

    public void setQueue(IQueue<SampleMessage> queue) {
        this.queue = queue;
    }
}
