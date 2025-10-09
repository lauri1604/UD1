package laura.api.storage;
import com.fasterxml.jackson.databind.ObjectMapper;
import laura.api.Character;
import laura.api.Response;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class CharacterStorage {

    public List<Character> getAllCharacters() throws MalformedURLException {
        ObjectMapper objectMapper = new ObjectMapper();
        int page = 1;

        List<Character> characters = new ArrayList<>();
        while (page < 43) {
            try {
                URL link = new URL(String.format("https://rickandmortyapi.com/api/character?page=%d", page));
                Response response = objectMapper.readValue(link, Response.class);
                List<Character> group = response.getResults();
                for (Character i : group) {
                    characters.add(i);
                }
                page++;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return characters;
    }

    public List<Character> getCharactersOrigin (String origin) throws MalformedURLException {
        ObjectMapper objectMapper = new ObjectMapper();
        int page = 1;
        List<Character> characters = new ArrayList<>();
        while (page < 43) {
            try {
                URL link = new URL(String.format("https://rickandmortyapi.com/api/character?page=%d", page));
                Response response = objectMapper.readValue(link, Response.class);
                List<Character> group = response.getResults();
                for (Character i : group) {
                    characters.add(i);
                }
                page++;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return characters.stream().filter(n -> n.getOrigin().getName().contains(origin)).toList();
    }

    public List<Character> getCharactersbyGender (String gender) throws MalformedURLException {
        ObjectMapper objectMapper = new ObjectMapper();
        int page = 1;
        List<Character> characters = new ArrayList<>();
        while (page < 43) {
            try {
                URL link = new URL(String.format("https://rickandmortyapi.com/api/character?page=%d", page));
                Response response = objectMapper.readValue(link, Response.class);
                List<Character> group = response.getResults();
                for (Character i : group) {
                    characters.add(i);
                }
                page++;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return characters.stream().filter(n -> n.getGender().contains(gender)).toList();
    }
}