package at.ac.fhcampuswien.lazychatter.model.openai;

public enum OpenAiRole {
    user,
    system,
    assistant,
    function;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
