package apiTest.entities;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueLink {
    private Type type;
    private InwardIssue inwardIssue;
    private OutwardIssue outwardIssue;

    @Data
    @Builder
    public static class Type{
        private String name;
        private String inward;
        private String outward;
    }
    @Data
    @Builder
    public static class InwardIssue{
        private String key;
    }
    @Data
    @Builder
    public static class OutwardIssue{
        private String key;
    }

}
