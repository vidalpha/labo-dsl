package lab.asm.patterns.strategy.asm

import groovyjarjarasm.asm.ClassWriter
import groovyjarjarasm.asm.FieldVisitor
import groovyjarjarasm.asm.MethodVisitor
import groovyjarjarasm.asm.Opcodes

public class StrategyPatternASM {
    public static void main(String[] args) {
        // Créer un générateur de bytecode pour la classe 'Context'
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        // Définir la classe Context
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Context", null, "java/lang/Object", new String[]{"Strategy"});

        // Ajouter le champ strategy
        FieldVisitor fv = cw.visitField(Opcodes.ACC_PRIVATE, "strategy", "LStrategy;", null, null);
        fv.visitEnd();

        // Ajouter le constructeur
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(LStrategy;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);  // this
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);  // this
        mv.visitVarInsn(Opcodes.ALOAD, 1);  // strategy
        mv.visitFieldInsn(Opcodes.PUTFIELD, "Context", "strategy", "LStrategy;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();

        // Ajouter la méthode executeStrategy
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "executeStrategy", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);  // this
        mv.visitFieldInsn(Opcodes.GETFIELD, "Context", "strategy", "LStrategy;");
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "Strategy", "execute", "()V", true);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();

        // Écrire la classe dans un fichier .class
        cw.visitEnd();
        byte[] classData = cw.toByteArray();

        // Sauvegarder la classe compilée dans un fichier .class
        try (FileOutputStream fos = new FileOutputStream("Context.class")) {
            fos.write(classData);
        }

        System.out.println("Classe Context générée avec ASM !");
    }
}