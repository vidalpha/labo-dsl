package lab.asm.demo

static void main(String[] args) {
    MyClassLoader classLoader = new MyClassLoader();
    Class<?> clazz = classLoader.loadClass("lab.asm.demo.HelloWorld");
    Object instance = clazz.newInstance();
    System.out.println(instance);
}