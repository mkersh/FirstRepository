package org.totaltutorial.beginner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BookTest.class, PersonTest.class, MyLibraryTest.class })
public class AllTests {

}
