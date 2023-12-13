package apiTest.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Issue {
    private Fields fields;

    @Data
    @Builder
    public static class Fields {
        private Project project;
        private String summary;
        private String description;
        private Issuetype issuetype;
        private Priority priority;

        @Data
        @Builder
        public static class Project {
            private String key;
        }

        @Data
        @Builder
        public static class Issuetype {
            private String name;
        }

        @Data
        @Builder
        public static class Priority {
            private String id;
        }
    }

}
