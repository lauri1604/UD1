package laura.api;
import laura.api.storage.CharacterStorage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("hola Rick");

        CharacterStorage characters = new CharacterStorage();
        List<Character> characterList = characters.getAllCharacters();

        System.out.println(characterList.stream().collect(Collectors.groupingBy(Character::getName,Collectors.counting())));
        System.out.println(characterList.size());
        Map<Integer, List<Object[]>> resultado = characterList.stream().collect(Collectors.groupingBy(Character::getId, Collectors.mapping(ch -> new Object[]{ch.getName(), ch.getStatus(), ch.getImage()}, Collectors.toList())));
        resultado.forEach((id, arrays) -> {
            System.out.println("ID " + id + ":");
            arrays.forEach(arr -> System.out.println(Arrays.toString(arr)));
        });
        System.out.println();
        System.out.println("obtener por Origen");
        System.out.println(characters.getCharactersOrigin("Earth").stream().toList());
        System.out.println();
        System.out.println("obtener por genero");
        System.out.println(characters.getCharactersbyGender("Female").stream().toList());
    }
}