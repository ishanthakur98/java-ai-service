package com.ai.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class GeminiResponse {
    private List<Candidate> candidates;
    private UsageMetadata usageMetadata;
    private String modelVersion;

    @Getter
    @Setter
    @ToString
    public static class Candidate {
        private Content content;
        private String finishReason;
        private int index;
    }

    @Getter
    @Setter
    @ToString
    public static class Content {
        private List<Part> parts;
        private String role;
    }

    @Getter
    @Setter
    @ToString
    public static class Part {
        private String text;
    }

    @Getter
    @Setter
    @ToString
    public static class UsageMetadata {
        private int promptTokenCount;
        private int candidatesTokenCount;
        private int totalTokenCount;
    }
}
