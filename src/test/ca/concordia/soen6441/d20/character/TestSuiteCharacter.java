package test.ca.concordia.soen6441.d20.character;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * This class implements a test suit for testing the fighter factory
 * @author negar
 *
 */
@RunWith(Suite.class)
@SuiteClasses({CharacterTest.class,CharacterMovementTest.class,CharacterBuilderTest.class})
public class TestSuiteCharacter {

}
