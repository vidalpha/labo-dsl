# Examples

- Utilisation de groovy AST pour modifier l'AST d'un code et ajouter des instructions

```groovy
import com.lab.meta.ast.annotations.SayHello

static void main(String[] args) {
    println("Groovy custom AST transformations")
    printHelloWorld()
}

@SayHello(name="ignace")
def static printHelloWorld() {
    println "Hello, World!"
}

```

**Console**

```
> Groovy custom AST transformations
> Starting printHelloWorld
> Hello, World!
> Ending printHelloWorld
```