package top.haidong556.deal.event;

public enum BizTopic {
    TOPIC_A("a","event a"),TOPIC_B("b","event b");
    private final String topicName;
    private final String description;
    private BizTopic(String topicName, String description){
        this.description=description;
        this.topicName=topicName;
    }
}
