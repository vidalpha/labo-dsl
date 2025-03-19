package lab.asm.patterns.strategy.assets

class ConcreteStrategyA implements Strategy{

    @Override
    void execute() {
        println "Exécution de la stratégie A"
    }
}
