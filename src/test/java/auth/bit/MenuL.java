package auth.bit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MenuL {
  DEF_MENU0("DM00000", squareTwo(0), "기본메뉴0"),
  DEF_MENU1("DM00001", squareTwo(1), "기본메뉴1"),
  DEF_MENU2("DM00002", squareTwo(2), "기본메뉴2"),
  DEF_MENU3("DM00003", squareTwo(3), "기본메뉴3"),
  DEF_MENU4("DM00004", squareTwo(4), "기본메뉴4"),
  DEF_MENU5("DM00005", squareTwo(5), "기본메뉴5"),
  BIG_MENU1("BM10000", squareTwo(10), "대메뉴1"),
  SML_MENU1_1("SM10001", squareTwo(11), "소메뉴1-1"),
  SML_MENU1_2("SM10002", squareTwo(12), "소메뉴1-2"),
  SML_MENU1_3("SM10003", squareTwo(13), "소메뉴1-3"),
  SML_MENU1_4("SM10004", squareTwo(14), "소메뉴1-4"),
  SML_MENU1_5("SM10005", squareTwo(15), "소메뉴1-5"),
  BIG_MENU2("BM20000", squareTwo(20), "대메뉴2"),
  SML_MENU2_1("SM20001", squareTwo(21), "소메뉴2-1"),
  SML_MENU2_2("SM20002", squareTwo(22), "소메뉴2-2"),
  SML_MENU2_3("SM20003", squareTwo(23), "소메뉴2-3"),
  SML_MENU2_4("SM20004", squareTwo(24), "소메뉴2-4"),
  BIG_MENU3("BM30000", squareTwo(30), "대메뉴3"),
  SML_MENU3_1("SM30001", squareTwo(31), "소메뉴3-1"),
  BIG_MENU6("BM60000", squareTwo(60), "대메뉴6"),
  SML_MENU6_2("SM30002", squareTwo(62), "소메뉴6-2"),
  // SML_MENU6_3("SM60003", squareTwo(63), "소메뉴6-3"), // long 범위 초과
  ;

  private String menuCode;
  // private 로 선언해야 안전함 (여기서는 편의를 위해서 안씀)
  long menuValue;
  private String menuName;
  static MenuL[] menuArray = MenuL.values();

  MenuL(String menuCode, long menuValue, String menuName) {
    this.menuCode = menuCode;
    this.menuValue = menuValue;
    this.menuName = menuName;
  }

  public String getMenuCode() {
    return menuCode;
  }

  public static long squareTwo(int squareCount) {
    return (long) Math.pow(2, squareCount);
  }

  public static Map<String, String> getUserMenus(long userAthorityCode) {
    Map<String, String> userMenus = new HashMap<String, String>();

    System.out.println("-".repeat(30) + " Tested " + "-".repeat(30));
    System.out.println("userPermissions\t|\tpermissionToCheck\t|\tcheckedValue");

    List<MenuL> menuList = Arrays.asList(menuArray);
    for (MenuL menu : menuList) {
      System.out.println(menu.menuName + "\t:\t" + menu.menuCode + "\t:\t" + menu.menuValue);
      if (hasPermission(userAthorityCode, menu.menuValue)) {
        userMenus.put(menu.menuCode, menu.menuName);
      }
      System.out.println("-".repeat(34) + "-".repeat(34));
    }
    ;

    return userMenus;
  }

  // 사용자 권한 확인 메서드
  public static boolean hasPermission(long userPermissions, long permissionToCheck) {
    System.out.println(userPermissions + "\t|\t" + permissionToCheck + "\t|\t"
        + (userPermissions & permissionToCheck) + "\t|\t" + ((userPermissions & permissionToCheck) != 0));
    System.out.println(Long.toBinaryString(userPermissions));
    System.out.println(Long.toBinaryString(permissionToCheck));
    System.out.println(Long.toBinaryString((userPermissions & permissionToCheck)));
    System.out.println((userPermissions & permissionToCheck) != 0);

    return (userPermissions & permissionToCheck) != 0;
  }
}