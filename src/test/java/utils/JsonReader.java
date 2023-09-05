package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

    /**
     * json 파일 리더기 
     * # 해당 경로에 대한 액세스 거부 문제 (관리자권한 실행)
     * @param path : 테스트케이스.json 파일 경로
     * @return : 테스트케이스(매개변수 및 정답 기대값)
     * @throws IOException
     * @throws ParseException
     */
    public Map<String, Object> jsonReader(String solutionEachPath) {
        Map<String, Object> testCase = new HashMap<String, Object>();

        JSONParser parser = new JSONParser();

        try {

            File file = new File(".");
            File projectFile = file.getAbsoluteFile();
            String projectPath = projectFile.getParent();
            //System.out.println("현재 프로젝트의 경로 : " + projectPath );

            String solutionCommonPath = "/src/test/java/";
            String jsonFilePath = projectPath + solutionCommonPath + solutionEachPath;

            // JSON 파일 읽기
            Reader reader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            testCase = jsonToMap(jsonObject);
            
        } catch (IOException e) {
            System.err.println("JSONException : test.json 읽어드리는중 Exception 발생");
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("JSONException : test.json 읽어드리는중 Exception 발생");
            e.printStackTrace();
        } catch (JSONException e) {
            System.err.println("JSONException : Json -> Map 변환중 Exception 발생");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception : jsonReader Exception 발생");
        } finally{
            //System.out.println("jsonFilePath : " + jsonFilePath);
        }

        return testCase;
    }

    /**
     * json을 받아 hashmap으로 변환하는 메소드
     * @param json
     * @return
     * @throws JSONException
     */
    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> returnMap = new HashMap<String, Object>();

        if (json != null) {
            returnMap = toMap(json);
        }

        return returnMap;
    }

    /**
     * json객체 안에 또다른 json 객체가 있을 경우
     * @param object
     * @return
     * @throws JSONException
     */
    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        @SuppressWarnings("rawtypes")
        Set keys = object.keySet();

        @SuppressWarnings("unchecked")
        Iterator<String> keysItr = keys.iterator();

        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }

        return map;
    }

    /**
     * json객체 안에 json 배열이 있을경우
     * @param array
     * @return
     * @throws JSONException
     */
    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();

        for (int i = 0; i < array.size(); i++) {
            Object value = array.get(i);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }

        return list;
    }
}
