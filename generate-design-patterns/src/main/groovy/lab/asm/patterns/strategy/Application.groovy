package lab.asm.patterns.strategy

import lab.asm.patterns.strategy.assets.*

public class Application {

    static void main(String[] args) {
        // Charger la classe générée Context.class
        def classLoader = new GroovyClassLoader()
        def contextClass = classLoader.parseClass(new File("Context.class"))

        // Créer une instance de Context avec ConcreteStrategyA
        def strategyA = new ConcreteStrategyA()
        def context = contextClass.getConstructor(Strategy).newInstance(strategyA)
        context.executeStrategy()

        // Changer la stratégie à ConcreteStrategyB
        def strategyB = new ConcreteStrategyB()
        context.setStrategy(strategyB)
        context.executeStrategy()
    }
}