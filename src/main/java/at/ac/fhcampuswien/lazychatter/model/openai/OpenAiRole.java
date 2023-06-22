package at.ac.fhcampuswien.lazychatter.model.openai;

public enum OpenAiRole {
    USER,
    SYSTEM,
    ASSISTANT,
    FUNCTION;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
