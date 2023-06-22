package at.ac.fhcampuswien.lazychatter.model.openai;

import java.util.List;

public class ChatCompletionResponse {
        String id;
        String object;
        long created;
        List<ChatCompletionChoices> choices;
        OpenAiUsageData usage;

        public String getId() {
                return id;
        }

        public String getObject() {
                return object;
        }

        public long getCreated() {
                return created;
        }

        public List<ChatCompletionChoices> getChoices() {
                return choices;
        }

        public OpenAiUsageData getUsage() {
                return usage;
        }
}
