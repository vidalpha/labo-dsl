package lab.asm.patterns.strategy.assets

class ConcreteStrategyB implements Strategy {
    @Override
    void execute() {
        println "Exécution de la stratégie B"
    }
}