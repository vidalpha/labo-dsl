# Examples

- Installation et utilisation de mapstruct dans gradle pour la création des DTO
- 
```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CarMapper {
@Mapping(source = "numberOfSeats", target = "seatCount")
CarDto carToCarDto(CarEntity car);
}
```

- Création d'une annotation processing personnaliée : @Builder

```java
import com.lab.processor.annotationtype.Builder;

@Builder
public class Person {

    private int age;

    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    
    // ...
}
```

**Result**

```java
Person p = new PersonBuilder().name("John").age(20).build();
```