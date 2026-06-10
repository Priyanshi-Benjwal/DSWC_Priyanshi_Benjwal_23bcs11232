package Day2.Q5;


abstract class DataPayload {

    public abstract String getRawContent();
}


class JsonPayload extends DataPayload {

    private String rawContent;

    public JsonPayload(String rawContent) {
        this.rawContent = rawContent;
    }

    @Override
    public String getRawContent() {
        return rawContent;
    }
}

class XmlPayload extends DataPayload {

    private String rawContent;

    public XmlPayload(String rawContent) {
        this.rawContent = rawContent;
    }

    @Override
    public String getRawContent() {
        return rawContent;
    }
}

class PipelineProcessor<T extends DataPayload> {

    public void process(T payload) {

        System.out.println("Processing Payload...");
        System.out.println("Raw Content: " + payload.getRawContent());
        System.out.println("Processing Completed.\n");
    }
}

public class DataStreamETL {

    public static void main(String[] args) {

        JsonPayload jsonData =
                new JsonPayload("{\"name\":\"Varun\",\"age\":20}");

        XmlPayload xmlData =
                new XmlPayload("<user><name>Varun</name></user>");

        PipelineProcessor<JsonPayload> jsonProcessor =
                new PipelineProcessor<>();

        PipelineProcessor<XmlPayload> xmlProcessor =
                new PipelineProcessor<>();

        jsonProcessor.process(jsonData);
        xmlProcessor.process(xmlData);

    }
}
