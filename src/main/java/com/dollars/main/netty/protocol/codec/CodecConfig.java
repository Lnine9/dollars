package com.dollars.main.netty.protocol.codec;

import com.alibaba.fastjson.JSON;
import com.dollars.main.netty.protocol.message.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CodecConfig {

    public static final int MAX_FRAME_LENGTH = 2048;

    public static final int LENGTH_FIELD_OFFSET = 0;

    public static final int MAGIC = 9;

    public static final byte VERSION = 1;

    public static final MsgSerializer SERIALIZE = MsgSerializer.Json;

    public static final Charset CHARSETS = StandardCharsets.UTF_8;

}

enum MsgSerializer implements Serializer {
    Json(0){
        @Override
        public <T> T deserialize(byte[] bytes, Class<T> clazz) {
            return (T) JSON.parseObject(new String(bytes, CodecConfig.CHARSETS), clazz);
        }

        @Override
        public <T> byte[] serialize(T obj) {
            return JSON.toJSONString(obj).getBytes(CodecConfig.CHARSETS);
        }
    },
    JDK(1){
        @Override
        public <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception{
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (T) ois.readObject();
        }

        @Override
        public <T> byte[] serialize(T obj) throws Exception {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();
        }
    };
    private final static Map<Integer, MsgSerializer> MAP = new HashMap<>();

    static  {
        MAP.put(Json.getId(), Json);
        MAP.put(JDK.getId(), JDK);
    }

    private final int id;

    MsgSerializer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MsgSerializer getSerializerById(int id){
        return MAP.get(id);
    }

}

class WebSocketRequest{
    int len;
    int type;
    Message msg;
}
