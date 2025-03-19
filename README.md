Grâce à la **métaprogrammation au runtime**, il est possible de reporter au runtime
la décision d'intercepter, d'injecter et même de  générer les méthodes des classes 
et des interfaces.

# Examples

- Mise en place d'un système de Dynamic Finders par metaprogrammation au runtime

```groovy
def personRepository = new Repository<>(Person.class)
def r1 = personRepository.findByFirstName("John")
println r1

def r2 = personRepository.findByLastName("Pan")
println r2

def bookRepository = new Repository<>(Book.class)
def b1 = bookRepository.findByTitle("Design Patterns")
print b1
```



