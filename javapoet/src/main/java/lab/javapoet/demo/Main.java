package lab.javapoet.demo;

import com.palantir.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * <a href="#">https://github.com/palantir/javapoet</a>
 */
public class Main {
    public static void main(String[] args) throws IOException {

        addHelloWorldSingleton();

        // Lancer le singleton
//        HelloWorld.getInstance("James");
//        HelloWorld.getInstance("Mathew");
//        HelloWorld.getInstance("Georges");
    }

    public static void addHelloWorldSingleton() throws IOException {
        ClassName singletonClass = ClassName.get("", "HelloWorld");

        // Créer le champ 'instance' privé
        FieldSpec instanceSingleton = FieldSpec.builder(singletonClass, "instance",
                        Modifier.PRIVATE, Modifier.STATIC, Modifier.VOLATILE)
                .initializer("null")
                .build();

        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addParameter(String.class, "name")
                .addStatement("$T.out.println($L)", System.class, "String.format(\"Hello, %s !\", name)")
                .build();

        MethodSpec getInstanceMethod = MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(String.class, "name")
                .returns(singletonClass)  // Utilise 'singletonClass' pour le type de retour
                .addCode("if (instance == null) {\n")
                .addCode("    synchronized ($T.class) {\n", singletonClass)  // Utilise 'singletonClass' ici aussi
                .addCode("        if (instance == null) {\n")
                .addCode("            $T.out.println($S);\n", System.class, "First time !!! Never again")
                .addCode("            instance = new $T($L);\n", singletonClass, "name")  // Utilise 'singletonClass' pour créer une instance
                .addCode("        }\n")
                .addCode("    }\n")
                .addCode("}\n")
                .addCode("return instance;\n")
                .build();

        TypeSpec singletonSpec = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(instanceSingleton)
                .addMethod(constructor)
                .addMethod(getInstanceMethod)
                .build();

        JavaFile javaFile = JavaFile.builder("lab.javapoet.demo", singletonSpec)
                .build();

        //javaFile.writeTo(System.out);
        // Il suit le package name du fichier pour construire le reste
        javaFile.writeTo(Paths.get("javapoet/src/main/java"));
    }
}