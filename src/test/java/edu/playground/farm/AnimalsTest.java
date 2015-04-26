package edu.playground.farm;

import static edu.playground.farm.Animals.DOMESTIC_CAT;
import static edu.playground.farm.Animals.HUSKY_DOG;
import static edu.playground.farm.Animals.MAINE_COON_CAT;
import static edu.playground.farm.Animals.RED_FOX;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toCollection;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import edu.playground.farm.annotations.Animal;
import edu.playground.farm.annotations.Canidae;
import edu.playground.farm.annotations.Felidae;

@RunWith(Parameterized.class)
public class AnimalsTest {
	private static final Object[] EMPTY_ARRAY = {};

	@Parameter(value = 0)
	public Class<? extends Annotation> animalClass;

	@Parameter(value = 1)
	public List<String> characteristics;


	@Parameters
	public static Collection<Object[]> animals() {
		return asList(new Object[][] {
				// animalClass		// characteristics
				{ Felidae.class, 	asList(DOMESTIC_CAT, MAINE_COON_CAT) },
				{ Canidae.class, 	asList(HUSKY_DOG, RED_FOX) }, 
				{ Animal.class,  	asList(HUSKY_DOG, DOMESTIC_CAT, MAINE_COON_CAT, RED_FOX) }
		});
	}


	@Test
	public void testAnimals() {
		final Animals obj = new Animals();

		List<Method> methodList = methodsWithAnnotation(obj, animalClass);
		assertThat(methodList.size(), is(characteristics.size()));

		List<String> descriptions = collectInvocationResults(obj, methodList);
		assertThat(descriptions.containsAll(characteristics), is(true));
	}


	//HELPER
	/**
	 * Filters out methods using given annotation.<br>
	 * E.g. with {@code @Felidae}, result will be two methods:<br> 
	 * {@link Animals#getDomesticCat()}<br>
	 * {@link Animals#getMaineCoonCat()}
	 * 
	 * @param obj Animals instance
	 * @param {@code @Felidae, @Canidae or @Animal}
	 * @return list of methods
	 */
	private static List<Method> methodsWithAnnotation(Animals obj, Class<? extends Annotation> annotation) {
		return asList(obj.getClass().getMethods()).stream()
				.filter(method -> method.isAnnotationPresent(annotation))
				.collect(toCollection(ArrayList::new));
	}


	/**
	 * Collects the results of method invocations on object of {@link Animals} type.
	 * @param obj Animals instance
	 * @param methodList
	 * @return results
	 */
	private static List<String> collectInvocationResults(Animals obj, List<Method> methodList) {
		return methodList.stream()
				.map(method -> invoke(obj, method))
				.collect(toCollection(ArrayList::new));
	}


	private static String invoke(Animals obj, Method method) {
		try {
			return (String) method.invoke(obj, EMPTY_ARRAY);
		} catch (Exception e) {
			return "exception";
		}
	}
}
