package auth.bit;

// import org.junit.jupiter.api.*;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;

import org.junit.Test;
// import static org.junit.Assert.assertThat;
// import static org.junit.Assume.assumeTrue;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// import static org.hamcrest.CoreMatchers.is;

import java.util.*;
// import java.util.stream.Stream;

// import utils.JsonReader;
// import utils.DataType;

public class TestPmsn {

    @Test
    public void test() {
        System.out.println("*".repeat(30) + " test Start " + "*".repeat(30));

        Map<String, String> userMenus = new HashMap<String, String>();
        

        int userAthorityCode = 
            Menu.DEF_MENU1.menuValue | 
            Menu.DEF_MENU3.menuValue | 
            Menu.DEF_MENU4.menuValue | 
            Menu.BIG_MENU1.menuValue |
            Menu.SML_MENU1_1.menuValue |
            Menu.SML_MENU1_2.menuValue |
            Menu.SML_MENU1_4.menuValue |
            Menu.BIG_MENU3.menuValue
            ;
        long userAthorityCodeL = 
            MenuL.DEF_MENU1.menuValue | 
            MenuL.DEF_MENU3.menuValue | 
            MenuL.DEF_MENU4.menuValue | 
            MenuL.BIG_MENU1.menuValue |
            MenuL.SML_MENU1_1.menuValue |
            MenuL.SML_MENU1_2.menuValue |
            MenuL.SML_MENU1_4.menuValue |
            MenuL.BIG_MENU3.menuValue |
            MenuL.BIG_MENU6.menuValue |
            MenuL.SML_MENU6_2.menuValue 
            ;
        
        // userMenus = Menu.getUserMenus(userAthorityCode);
        userMenus = MenuL.getUserMenus(userAthorityCodeL);


        System.out.println("-".repeat(30) + " Result " + "-".repeat(30));
        userMenus.forEach((strKey, strValue) -> {
            System.out.println("- " + strKey + " : " + strValue);
        });

        System.out.println("*".repeat(30) + " test End " + "*".repeat(30));
    }

}