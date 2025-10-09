package laura.api;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Response {

    @JsonProperty("results")
    private List<Character> results;

    @JsonProperty("info")
    private Info info;

    public List<Character> getResults(){
        return results;
    }

    public Info getInfo(){
        return info;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "results = '" + results + '\'' +
                        ",info = '" + info + '\'' +
                        "}";
    }
}
