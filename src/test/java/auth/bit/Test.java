package auth.bit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.hamcrest.CoreMatchers.is;

import java.util.*;
import java.util.stream.Stream;

import utils.JsonReader;
import utils.DataType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Test {

    DataType dataType = new DataType();
    JsonReader json_reader = new JsonReader();

    Test test = new Test();

    final String SOLUTION_PACKAGE_PATH = test.getClass().getPackage().getName();
    final String SOLUTION_FOLDER_PATH = SOLUTION_PACKAGE_PATH.replaceAll("\\.","/");
    final String SOLUTION_FILE_PATH = "/testcase.json";
    final String SOLUTION_EACH_PATH = SOLUTION_FOLDER_PATH + SOLUTION_FILE_PATH;

    final Map<String, Object> BASE_MAP = json_reader.jsonReader(SOLUTION_EACH_PATH);
    final Map<String, String> DATA_TYPES_MAP = (Map<String, String>) BASE_MAP.get("Data_Types");
    final Map<String, Map> TEST_CASES_MAP = (Map<String, Map>) BASE_MAP.get("Test_Cases");
    final Long TIME_LIMIT = (Long) BASE_MAP.get("Time_Limit");

    long startTime = 0;
    long stopTime = 0;

    List<Arguments> arguList = new ArrayList<Arguments>();
    Boolean testExecute = false;
    
    // @BeforeAll
    void beforeAllTest() {
        System.out.println("=".repeat(50) + " Test initiate " + "=".repeat(50));

        // System.out.println(BASE_MAP.toString());
        // System.out.println("DATA_TYPES_MAP : " + DATA_TYPES_MAP.toString());
        // System.out.println("TEST_CASES_MAP : " + TEST_CASES_MAP.toString());
        // System.out.println("TIME_LIMIT : " + TIME_LIMIT);

        if(DATA_TYPES_MAP == null || DATA_TYPES_MAP.isEmpty()){
            testExecute = false;
            
            System.err.println("Warning!! : Test has been stopped. DATA_TYPES_MAP is Empty.");
        }
        else if(TEST_CASES_MAP == null || TEST_CASES_MAP.isEmpty()){
            testExecute = false;

            System.err.println("Warning!! : Test has been stopped. TEST_CASES_MAP is Empty.");
        }else {
            testExecute = true;
            
            List<String> testCaseKeyList = new ArrayList<>( TEST_CASES_MAP.keySet() );

            // 키 값으로 오름차순 정렬
            Collections.sort(testCaseKeyList);

            for(String testCaseKey : testCaseKeyList){
                arguList.add( Arguments.of(testCaseKey, TEST_CASES_MAP.get(testCaseKey)));
            }
        }
    }

    // @BeforeEach
    void beforeEachTest() {
        assumeTrue(testExecute);
        startTime = System.currentTimeMillis();
    }

    Stream<Arguments> arguListToStream() {
        return arguList.stream();
    }

    // @Timeout(value = TIME_LIMIT, unit = TimeUnit.MILLISECONDS)
    @MethodSource("arguListToStream")
    @ParameterizedTest(name = "testCase : {0}, Parameters : {1}")
    //@DisplayName(solutionPackagePath + "정답비교 테스트")
    void test(String testCaseNum, HashMap testCaseMap) {
        System.out.println("*".repeat(30) + testCaseNum + " test Start " + "*".repeat(30));

        Object expected =null;
        Object actual = null;
        
        // Map TestCaseMap = testCaseMap
        
        Object[] returnArr = dataType.makeReturnArr((Object) testCaseMap.get("return"), (String) DATA_TYPES_MAP.get("return"));
        expected = returnArr[0];
        testCaseMap.remove("return");

        // Collection<Object> tcParamsCol = testCaseMap.values();
        // List<Object> tcParamsList = new ArrayList<>(tcParamsCol);
        // Object[] tcParamsArr = tcParamsList.toArray();

        Auth.ACE_M_LOGIN





        
        System.out.println("-".repeat(30) + testCaseNum + " Solution Start " + "-".repeat(30));
        try{







            
            actual = "";
        } catch(Exception e) {
            System.out.println("-".repeat(25) + testCaseNum + " Solution Source Error " + "-".repeat(25));
        }
        System.out.println("-".repeat(30) + testCaseNum + " Solution End " + "-".repeat(30));

        System.out.println("expected  : " + expected);
        System.out.println("actual  : "+ actual);

        assertThat(actual, is(expected));
        System.out.println("*".repeat(30) + testCaseNum + " test End " + "*".repeat(30));
    }

    @AfterEach
    void afterEachTest() {
        stopTime = System.currentTimeMillis();

        long runTime = stopTime - startTime;

        if(TIME_LIMIT < runTime) {
            testExecute = false;

            System.err.println("Warning!! : Test has been stopped. runTime is Long.");
            System.err.println("TIME LIMIT\t:\t" + TIME_LIMIT);
            System.err.println("RUN TIME\t:\t" + runTime);
            System.err.println("Exceeded by " +  (TIME_LIMIT - runTime) + " milliseconds.");
        }else {
            System.out.println("run Time : " + runTime + " ms");
        }
    }

    @AfterAll
    void afterAllTest(){
        System.out.println("=".repeat(50) + " Test Terminate " + "=".repeat(50));
    }

}