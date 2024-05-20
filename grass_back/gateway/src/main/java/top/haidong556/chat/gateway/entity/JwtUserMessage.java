package top.haidong556.chat.gateway.entity;


import lombok.Data;

@Data
public class JwtUserMessage {
    private String audience;
    private long userId;
    private String userName;
    private String email;
    private String[] authorities;
    private String issuer;
    private long issuedAt;
    private long expiresAt;
    public String toJson() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"audience\":").append("\"").append(audience).append("\",");
        jsonBuilder.append("\"userId\":").append(userId).append(",");
        jsonBuilder.append("\"userName\":").append("\"").append(userName).append("\",");
        jsonBuilder.append("\"email\":").append("\"").append(email).append("\",");
        jsonBuilder.append("\"authorities\":").append("[");
        if (authorities != null && authorities.length > 0) {
            for (int i = 0; i < authorities.length; i++) {
                jsonBuilder.append("\"").append(authorities[i]).append("\"");
                if (i < authorities.length - 1) {
                    jsonBuilder.append(",");
                }
            }
        }
        jsonBuilder.append("],");
        jsonBuilder.append("\"issuer\":").append("\"").append(issuer).append("\",");
        jsonBuilder.append("\"issuedAt\":").append(issuedAt).append(",");
        jsonBuilder.append("\"expiresAt\":").append(expiresAt);
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
