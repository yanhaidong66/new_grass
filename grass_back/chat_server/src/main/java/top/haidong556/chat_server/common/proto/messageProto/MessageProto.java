package top.haidong556.chat_server.common.proto.messageProto;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

// Protobuf Java Version: 3.25.3
public final class MessageProto {
  private MessageProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Message)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 messageId = 1;</code>
     * @return The messageId.
     */
    long getMessageId();

    /**
     * <code>int64 messageConversationId = 2;</code>
     * @return The messageConversationId.
     */
    long getMessageConversationId();

    /**
     * <code>int64 messageCreateTime = 3;</code>
     * @return The messageCreateTime.
     */
    long getMessageCreateTime();

    /**
     * <code>int64 messageSenderId = 4;</code>
     * @return The messageSenderId.
     */
    long getMessageSenderId();

    /**
     * <code>int64 messageReceiverId = 5;</code>
     * @return The messageReceiverId.
     */
    long getMessageReceiverId();

    /**
     * <code>string messageContext = 6;</code>
     * @return The messageContext.
     */
    java.lang.String getMessageContext();
    /**
     * <code>string messageContext = 6;</code>
     * @return The bytes for messageContext.
     */
    com.google.protobuf.ByteString
        getMessageContextBytes();

    /**
     * <code>bool messageRead = 7;</code>
     * @return The messageRead.
     */
    boolean getMessageRead();
  }
  /**
   * Protobuf type {@code Message}
   */
  public static final class Message extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Message)
      MessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Message.newBuilder() to construct.
    private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Message() {
      messageContext_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Message();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return MessageProto.internal_static_Message_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return MessageProto.internal_static_Message_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              MessageProto.Message.class, MessageProto.Message.Builder.class);
    }

    public static final int MESSAGEID_FIELD_NUMBER = 1;
    private long messageId_ = 0L;
    /**
     * <code>int64 messageId = 1;</code>
     * @return The messageId.
     */
    @java.lang.Override
    public long getMessageId() {
      return messageId_;
    }

    public static final int MESSAGECONVERSATIONID_FIELD_NUMBER = 2;
    private long messageConversationId_ = 0L;
    /**
     * <code>int64 messageConversationId = 2;</code>
     * @return The messageConversationId.
     */
    @java.lang.Override
    public long getMessageConversationId() {
      return messageConversationId_;
    }

    public static final int MESSAGECREATETIME_FIELD_NUMBER = 3;
    private long messageCreateTime_ = 0L;
    /**
     * <code>int64 messageCreateTime = 3;</code>
     * @return The messageCreateTime.
     */
    @java.lang.Override
    public long getMessageCreateTime() {
      return messageCreateTime_;
    }

    public static final int MESSAGESENDERID_FIELD_NUMBER = 4;
    private long messageSenderId_ = 0L;
    /**
     * <code>int64 messageSenderId = 4;</code>
     * @return The messageSenderId.
     */
    @java.lang.Override
    public long getMessageSenderId() {
      return messageSenderId_;
    }

    public static final int MESSAGERECEIVERID_FIELD_NUMBER = 5;
    private long messageReceiverId_ = 0L;
    /**
     * <code>int64 messageReceiverId = 5;</code>
     * @return The messageReceiverId.
     */
    @java.lang.Override
    public long getMessageReceiverId() {
      return messageReceiverId_;
    }

    public static final int MESSAGECONTEXT_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private volatile java.lang.Object messageContext_ = "";
    /**
     * <code>string messageContext = 6;</code>
     * @return The messageContext.
     */
    @java.lang.Override
    public java.lang.String getMessageContext() {
      java.lang.Object ref = messageContext_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        messageContext_ = s;
        return s;
      }
    }
    /**
     * <code>string messageContext = 6;</code>
     * @return The bytes for messageContext.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getMessageContextBytes() {
      java.lang.Object ref = messageContext_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        messageContext_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int MESSAGEREAD_FIELD_NUMBER = 7;
    private boolean messageRead_ = false;
    /**
     * <code>bool messageRead = 7;</code>
     * @return The messageRead.
     */
    @java.lang.Override
    public boolean getMessageRead() {
      return messageRead_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (messageId_ != 0L) {
        output.writeInt64(1, messageId_);
      }
      if (messageConversationId_ != 0L) {
        output.writeInt64(2, messageConversationId_);
      }
      if (messageCreateTime_ != 0L) {
        output.writeInt64(3, messageCreateTime_);
      }
      if (messageSenderId_ != 0L) {
        output.writeInt64(4, messageSenderId_);
      }
      if (messageReceiverId_ != 0L) {
        output.writeInt64(5, messageReceiverId_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(messageContext_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 6, messageContext_);
      }
      if (messageRead_ != false) {
        output.writeBool(7, messageRead_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (messageId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, messageId_);
      }
      if (messageConversationId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, messageConversationId_);
      }
      if (messageCreateTime_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, messageCreateTime_);
      }
      if (messageSenderId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(4, messageSenderId_);
      }
      if (messageReceiverId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(5, messageReceiverId_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(messageContext_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, messageContext_);
      }
      if (messageRead_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(7, messageRead_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof MessageProto.Message)) {
        return super.equals(obj);
      }
      MessageProto.Message other = (MessageProto.Message) obj;

      if (getMessageId()
          != other.getMessageId()) return false;
      if (getMessageConversationId()
          != other.getMessageConversationId()) return false;
      if (getMessageCreateTime()
          != other.getMessageCreateTime()) return false;
      if (getMessageSenderId()
          != other.getMessageSenderId()) return false;
      if (getMessageReceiverId()
          != other.getMessageReceiverId()) return false;
      if (!getMessageContext()
          .equals(other.getMessageContext())) return false;
      if (getMessageRead()
          != other.getMessageRead()) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + MESSAGEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMessageId());
      hash = (37 * hash) + MESSAGECONVERSATIONID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMessageConversationId());
      hash = (37 * hash) + MESSAGECREATETIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMessageCreateTime());
      hash = (37 * hash) + MESSAGESENDERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMessageSenderId());
      hash = (37 * hash) + MESSAGERECEIVERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMessageReceiverId());
      hash = (37 * hash) + MESSAGECONTEXT_FIELD_NUMBER;
      hash = (53 * hash) + getMessageContext().hashCode();
      hash = (37 * hash) + MESSAGEREAD_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getMessageRead());
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static MessageProto.Message parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static MessageProto.Message parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MessageProto.Message parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static MessageProto.Message parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MessageProto.Message parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static MessageProto.Message parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MessageProto.Message parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static MessageProto.Message parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static MessageProto.Message parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static MessageProto.Message parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static MessageProto.Message parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static MessageProto.Message parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(MessageProto.Message prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Message}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Message)
        MessageProto.MessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return MessageProto.internal_static_Message_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return MessageProto.internal_static_Message_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                MessageProto.Message.class, MessageProto.Message.Builder.class);
      }

      // Construct using MessageOuterClass.Message.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        messageId_ = 0L;
        messageConversationId_ = 0L;
        messageCreateTime_ = 0L;
        messageSenderId_ = 0L;
        messageReceiverId_ = 0L;
        messageContext_ = "";
        messageRead_ = false;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return MessageProto.internal_static_Message_descriptor;
      }

      @java.lang.Override
      public MessageProto.Message getDefaultInstanceForType() {
        return MessageProto.Message.getDefaultInstance();
      }

      @java.lang.Override
      public MessageProto.Message build() {
        MessageProto.Message result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public MessageProto.Message buildPartial() {
        MessageProto.Message result = new MessageProto.Message(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(MessageProto.Message result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.messageId_ = messageId_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.messageConversationId_ = messageConversationId_;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.messageCreateTime_ = messageCreateTime_;
        }
        if (((from_bitField0_ & 0x00000008) != 0)) {
          result.messageSenderId_ = messageSenderId_;
        }
        if (((from_bitField0_ & 0x00000010) != 0)) {
          result.messageReceiverId_ = messageReceiverId_;
        }
        if (((from_bitField0_ & 0x00000020) != 0)) {
          result.messageContext_ = messageContext_;
        }
        if (((from_bitField0_ & 0x00000040) != 0)) {
          result.messageRead_ = messageRead_;
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof MessageProto.Message) {
          return mergeFrom((MessageProto.Message)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(MessageProto.Message other) {
        if (other == MessageProto.Message.getDefaultInstance()) return this;
        if (other.getMessageId() != 0L) {
          setMessageId(other.getMessageId());
        }
        if (other.getMessageConversationId() != 0L) {
          setMessageConversationId(other.getMessageConversationId());
        }
        if (other.getMessageCreateTime() != 0L) {
          setMessageCreateTime(other.getMessageCreateTime());
        }
        if (other.getMessageSenderId() != 0L) {
          setMessageSenderId(other.getMessageSenderId());
        }
        if (other.getMessageReceiverId() != 0L) {
          setMessageReceiverId(other.getMessageReceiverId());
        }
        if (!other.getMessageContext().isEmpty()) {
          messageContext_ = other.messageContext_;
          bitField0_ |= 0x00000020;
          onChanged();
        }
        if (other.getMessageRead() != false) {
          setMessageRead(other.getMessageRead());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 8: {
                messageId_ = input.readInt64();
                bitField0_ |= 0x00000001;
                break;
              } // case 8
              case 16: {
                messageConversationId_ = input.readInt64();
                bitField0_ |= 0x00000002;
                break;
              } // case 16
              case 24: {
                messageCreateTime_ = input.readInt64();
                bitField0_ |= 0x00000004;
                break;
              } // case 24
              case 32: {
                messageSenderId_ = input.readInt64();
                bitField0_ |= 0x00000008;
                break;
              } // case 32
              case 40: {
                messageReceiverId_ = input.readInt64();
                bitField0_ |= 0x00000010;
                break;
              } // case 40
              case 50: {
                messageContext_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000020;
                break;
              } // case 50
              case 56: {
                messageRead_ = input.readBool();
                bitField0_ |= 0x00000040;
                break;
              } // case 56
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private long messageId_ ;
      /**
       * <code>int64 messageId = 1;</code>
       * @return The messageId.
       */
      @java.lang.Override
      public long getMessageId() {
        return messageId_;
      }
      /**
       * <code>int64 messageId = 1;</code>
       * @param value The messageId to set.
       * @return This builder for chaining.
       */
      public Builder setMessageId(long value) {

        messageId_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>int64 messageId = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        messageId_ = 0L;
        onChanged();
        return this;
      }

      private long messageConversationId_ ;
      /**
       * <code>int64 messageConversationId = 2;</code>
       * @return The messageConversationId.
       */
      @java.lang.Override
      public long getMessageConversationId() {
        return messageConversationId_;
      }
      /**
       * <code>int64 messageConversationId = 2;</code>
       * @param value The messageConversationId to set.
       * @return This builder for chaining.
       */
      public Builder setMessageConversationId(long value) {

        messageConversationId_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>int64 messageConversationId = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageConversationId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        messageConversationId_ = 0L;
        onChanged();
        return this;
      }

      private long messageCreateTime_ ;
      /**
       * <code>int64 messageCreateTime = 3;</code>
       * @return The messageCreateTime.
       */
      @java.lang.Override
      public long getMessageCreateTime() {
        return messageCreateTime_;
      }
      /**
       * <code>int64 messageCreateTime = 3;</code>
       * @param value The messageCreateTime to set.
       * @return This builder for chaining.
       */
      public Builder setMessageCreateTime(long value) {

        messageCreateTime_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }
      /**
       * <code>int64 messageCreateTime = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageCreateTime() {
        bitField0_ = (bitField0_ & ~0x00000004);
        messageCreateTime_ = 0L;
        onChanged();
        return this;
      }

      private long messageSenderId_ ;
      /**
       * <code>int64 messageSenderId = 4;</code>
       * @return The messageSenderId.
       */
      @java.lang.Override
      public long getMessageSenderId() {
        return messageSenderId_;
      }
      /**
       * <code>int64 messageSenderId = 4;</code>
       * @param value The messageSenderId to set.
       * @return This builder for chaining.
       */
      public Builder setMessageSenderId(long value) {

        messageSenderId_ = value;
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      /**
       * <code>int64 messageSenderId = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageSenderId() {
        bitField0_ = (bitField0_ & ~0x00000008);
        messageSenderId_ = 0L;
        onChanged();
        return this;
      }

      private long messageReceiverId_ ;
      /**
       * <code>int64 messageReceiverId = 5;</code>
       * @return The messageReceiverId.
       */
      @java.lang.Override
      public long getMessageReceiverId() {
        return messageReceiverId_;
      }
      /**
       * <code>int64 messageReceiverId = 5;</code>
       * @param value The messageReceiverId to set.
       * @return This builder for chaining.
       */
      public Builder setMessageReceiverId(long value) {

        messageReceiverId_ = value;
        bitField0_ |= 0x00000010;
        onChanged();
        return this;
      }
      /**
       * <code>int64 messageReceiverId = 5;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageReceiverId() {
        bitField0_ = (bitField0_ & ~0x00000010);
        messageReceiverId_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object messageContext_ = "";
      /**
       * <code>string messageContext = 6;</code>
       * @return The messageContext.
       */
      public java.lang.String getMessageContext() {
        java.lang.Object ref = messageContext_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          messageContext_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string messageContext = 6;</code>
       * @return The bytes for messageContext.
       */
      public com.google.protobuf.ByteString
          getMessageContextBytes() {
        java.lang.Object ref = messageContext_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          messageContext_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string messageContext = 6;</code>
       * @param value The messageContext to set.
       * @return This builder for chaining.
       */
      public Builder setMessageContext(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        messageContext_ = value;
        bitField0_ |= 0x00000020;
        onChanged();
        return this;
      }
      /**
       * <code>string messageContext = 6;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageContext() {
        messageContext_ = getDefaultInstance().getMessageContext();
        bitField0_ = (bitField0_ & ~0x00000020);
        onChanged();
        return this;
      }
      /**
       * <code>string messageContext = 6;</code>
       * @param value The bytes for messageContext to set.
       * @return This builder for chaining.
       */
      public Builder setMessageContextBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        messageContext_ = value;
        bitField0_ |= 0x00000020;
        onChanged();
        return this;
      }

      private boolean messageRead_ ;
      /**
       * <code>bool messageRead = 7;</code>
       * @return The messageRead.
       */
      @java.lang.Override
      public boolean getMessageRead() {
        return messageRead_;
      }
      /**
       * <code>bool messageRead = 7;</code>
       * @param value The messageRead to set.
       * @return This builder for chaining.
       */
      public Builder setMessageRead(boolean value) {

        messageRead_ = value;
        bitField0_ |= 0x00000040;
        onChanged();
        return this;
      }
      /**
       * <code>bool messageRead = 7;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageRead() {
        bitField0_ = (bitField0_ & ~0x00000040);
        messageRead_ = false;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Message)
    }

    // @@protoc_insertion_point(class_scope:Message)
    private static final MessageProto.Message DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new MessageProto.Message();
    }

    public static MessageProto.Message getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Message>
        PARSER = new com.google.protobuf.AbstractParser<Message>() {
      @java.lang.Override
      public Message parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<Message> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Message> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public MessageProto.Message getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Message_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Message_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rmessage.proto\"\267\001\n\007Message\022\021\n\tmessageId" +
      "\030\001 \001(\003\022\035\n\025messageConversationId\030\002 \001(\003\022\031\n" +
      "\021messageCreateTime\030\003 \001(\003\022\027\n\017messageSende" +
      "rId\030\004 \001(\003\022\031\n\021messageReceiverId\030\005 \001(\003\022\026\n\016" +
      "messageContext\030\006 \001(\t\022\023\n\013messageRead\030\007 \001(" +
      "\010b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Message_descriptor,
        new java.lang.String[] { "MessageId", "MessageConversationId", "MessageCreateTime", "MessageSenderId", "MessageReceiverId", "MessageContext", "MessageRead", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}