package com.lab.meta.runtime.db
/*
Les Dynamic Finders sont des méthodes automatiquement  générées par GORM pour interroger
des bases de données en utilisant des conventions sur les noms de méthodes. Ces méthodes
sont dynamiquement créées au moment de l'exécution en fonction du nom de la méthode
et des paramètres passés.
 */
class Repository<T> {

    private Class<T> genericType;

    Repository(Class<T> genericType){
        this.genericType = genericType;
    }

    EntityManager entityManager = new EntityManager()

    def dynamicMethods = [
        ~/findBy[A-Za-z]+/, // Exemple : matchera findByFirstName, findByLastName, etc.
    ]

    def methodMissing(String methodName, def args) {

        def matched = dynamicMethods.find { methodName =~ it }

        if(matched){

            // Si la méthode trouve une correspondance, extraire la partie après 'findBy'
            def propertyName = methodName.replaceFirst("findBy", "")
            propertyName = propertyName[0].toLowerCase() + propertyName[1..-1] // pour mettre la première lettre en minuscule

            // Si la méthode existe dans la métaclasse alors invoquer la méthode sur la métaclasse
            if (Repository.metaClass.respondsTo(this, methodName)) {
                return Repository.metaClass.invokeMethod(this, methodName, args)
            }

            // Si la méthode n'existe pas, la définir
            def method = { Object[] varArgs ->
                return entityManager.createQuery(this.genericType)
                    .findAll {
                        object -> object."${propertyName}" == varArgs[0]
                    }

            }

            Repository.metaClass."${methodName}" << method

            // Retourner le résultat de la méthode
            return method.call(args)

        }
        else throw new MissingMethodException(methodName, Repository.class, args)
    }

}