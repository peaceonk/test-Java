package test;

public class PermissionManager {
    // 권한 비트 정의
    public static final int READ = 0x1;
    public static final int WRITE = 0x2;
    public static final int EXECUTE = 0x4;
    public static final int ADMIN = 0x8;

  public static void main(String[] args) {
    // 사용자 권한 설정 (예: 사용자 1은 읽기와 쓰기 권한을 가지고 있음)

    int user1Permissions = READ | WRITE;

    int user2Permissions = 242;
    String user2PermissionsStr = Integer.toHexString(user2Permissions);

    System.out.println(user2PermissionsStr);

    // 사용자 권한 확인
    if (hasPermission(user1Permissions, READ)) {
    System.out.println("사용자 1은 READ 권한이 있습니다.");
    } else {
    System.out.println("사용자 1은 READ 권한이 없습니다.");
    }

    // 사용자 권한 확인
    if (hasPermission(user1Permissions, WRITE)) {
    System.out.println("사용자 1은 WRITE 권한이 있습니다.");
    } else {
    System.out.println("사용자 1은 WRITE 권한이 없습니다.");
    }

    if (hasPermission(user1Permissions, EXECUTE)) {
    System.out.println("사용자 1은 실행 권한이 있습니다.");
    } else {
    System.out.println("사용자 1은 실행 권한이 없습니다.");
    }

    if (hasPermission(user1Permissions, ADMIN)) {
    System.out.println("사용자 1은 ADMIN 권한이 있습니다.");
    } else {
    System.out.println("사용자 1은 ADMIN 권한이 없습니다.");
    }
  }

  // 사용자 권한 확인 메서드
  public static boolean hasPermission(int userPermissions, int permissionToCheck) {
    System.out
        .println("userPermissions [ " + userPermissions + " ] \t permissionToCheck : [ " + permissionToCheck + " ]");
    // System.out.println("userPermissions [ " + userPermissions + " ] \t
    // permissionToCheck : [ " + permissionToCheck + " ]");
    System.out.println("checkedValue : " + (userPermissions & permissionToCheck));
    return (userPermissions & permissionToCheck) != 0;
  }
}