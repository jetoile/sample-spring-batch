cd target/appassembler

chmod +x bin/sample.sh

./bin/sample.sh file:///home/khanh/<emplacement>/src/main/resources/fr/jetoile/sample/springbatch/batch/springbatch.xml job



POUR LANCER EN TEST (sortie dans un fichier texte sans modification de la base)
./bin/sample.sh file:///home/khanh/<emplacement>/src/main/resources/fr/jetoile/sample/springbatch/batch/springbatch.xml foo
=> sortie dans target/appassembler/error.log target/appassembler/target/test-outputs/output.txt
