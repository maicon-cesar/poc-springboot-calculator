package br.com.calculator.javaspringcalculator.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.calculator.javaspringcalculator.identities.Operation;

public interface CalculatorRepository extends MongoRepository<Operation, String> {

}
