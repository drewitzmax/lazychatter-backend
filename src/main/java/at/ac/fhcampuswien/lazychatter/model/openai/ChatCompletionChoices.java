package at.ac.fhcampuswien.lazychatter.model.openai;

public class ChatCompletionChoices {
    int index;
    OpenAiMessage message;
    String finish_reason;

    public int getIndex() {
        return index;
    }

    public OpenAiMessage getMessage() {
        return message;
    }

    public String getFinish_reason() {
        return finish_reason;
    }
}
