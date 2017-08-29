package com.iqmsoft.payara;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;

public class SampleMessage implements IdentifiedDataSerializable {
    private String message;

    public SampleMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFactoryId() {
        return MsgDataSerializableFactory.FACTORY_ID;
    }

    public int getId() {
        return MsgDataSerializableFactory.MESSAGE_TYPE;
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(message);
    }

    public void readData(ObjectDataInput in) throws IOException {
        message = in.readUTF();
    }
}
