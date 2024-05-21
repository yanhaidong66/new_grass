package top.haidong556.chat_server.common;
import java.time.Instant;
import java.util.Random;


public class UniqueIDGenerator {
    // 定义纪元时间为2024-01-01 00:00:00 UTC
    private static final long EPOCH = Instant.parse("2024-01-01T00:00:00Z").toEpochMilli();
    private static final Random random = new Random();

    // 掩码
    private static final long CONVERSATION_ID_MASK = 0x0000000000000000L; // 最高位为0，次高位为0
    private static final long MESSAGE_ID_MASK = 0x4000000000000000L;      // 最高位为0，次高位为1

    public static long generateConversationId(long userId) {
        return generateUniqueId(userId, CONVERSATION_ID_MASK);
    }

    public static long generateMessageId(long userId) {
        return generateUniqueId(userId, MESSAGE_ID_MASK);
    }

    private static long generateUniqueId(long userId, long idMask) {
        long currentTimeMillis = System.currentTimeMillis();
        long timestamp = currentTimeMillis - EPOCH;

        // 确保时间戳在41位范围内
        timestamp &= 0x1FFFFFFFFFFL;

        // 确保用户ID在16位范围内
        userId &= 0xFFFFL;

        // 生成7位随机数
        long randomNumber = random.nextInt(128);

        // 组合成64位ID并添加掩码
        long uniqueId = (timestamp << 23) | (userId << 7) | randomNumber | idMask;

        return uniqueId;
    }

    public static void main(String[] args) {
        long userId = 12345;
        long conversationId = generateConversationId(userId);
        long messageId = generateMessageId(userId);

        System.out.println("Conversation ID: " + conversationId);
        System.out.println("Message ID: " + messageId);
    }
}

