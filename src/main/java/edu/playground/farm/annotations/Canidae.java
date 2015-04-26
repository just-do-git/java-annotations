package edu.playground.farm.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import edu.playground.farm.enums.SpeciesCanidae;

@Target(METHOD)
@Retention(RUNTIME)
public @interface Canidae {
	abstract SpeciesCanidae species();
}
