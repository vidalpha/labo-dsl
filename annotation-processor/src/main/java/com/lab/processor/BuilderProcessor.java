/*
From https://github.com/geekific-official/geekific-youtube/tree/main/annotation-processing
 Youtube video https://www.youtube.com/watch?v=ja4is9oq37k
 */
package com.lab.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes("com.lab.processor.annotationtype.*")
public class BuilderProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotations.forEach(annotation ->
                roundEnv.getElementsAnnotatedWith(annotation)
                        .forEach(this::generateBuilderFile)
        );
        return true; // A false cela indique que d'autres processeurs peuvent également traiter cette annotation
    }

    private void generateBuilderFile(Element element) {
        String className = element.getSimpleName().toString();
        String packageName = element.getEnclosingElement().toString();
        String builderName = className + "Builder";
        String builderFullName = packageName + "." + builderName;
        List<? extends Element> fields = element.getEnclosedElements()
                .stream()
                .filter(e ->
                        // Ne récupérer que les éléments de la classe de type champ
                        e.getKind() == ElementKind.FIELD
                )
                .toList();

        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.NOTE,"Fields " + fields);


        try (PrintWriter writer = new PrintWriter(
                processingEnv.getFiler().createSourceFile(builderFullName).openWriter())) {

            writer.println("""
                    package %s;
                         
                    public class %s {
                    """
                    .formatted(packageName, builderName)
            );

            fields.forEach(field -> {
                    String fieldType = field.asType().toString();
                    if ("java.lang.String".equals(fieldType)) {
                        fieldType = "String";
                    }
                    writer.print("""
                                private %s %s;
                            """.formatted(fieldType, field.getSimpleName())
                    );
                }
            );

            writer.println();
            fields.forEach(field -> {
                    String fieldType = field.asType().toString();
                    if ("java.lang.String".equals(fieldType)) {
                        fieldType = "String";
                    }
                    writer.println("""
                                public %s %s(%s value) {
                                    %s = value;
                                    return this;
                                }
                            """.formatted(builderName, field.getSimpleName(),
                            fieldType, field.getSimpleName())
                    );
                }
            );

            writer.println("""
                        public %s build() {
                            return new %s(%s);
                        }
                    """.formatted(className, className,
                    fields.stream().map(Element::getSimpleName).collect(joining(", ")))
            );
            writer.println("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}