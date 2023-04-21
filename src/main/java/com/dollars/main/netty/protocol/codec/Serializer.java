package com.dollars.main.netty.protocol.codec;

public interface Serializer {

    <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception;

    <T> byte[] serialize(T obj) throws Exception;

}
