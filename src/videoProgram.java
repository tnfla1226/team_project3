import java.util.Arrays;
import java.util.Scanner;

import static java.lang.String.*;

public class videoProgram {

    static Scanner sc = new Scanner(System.in);

    // 비디오 정보
    static String[] video = {"어벤져스", "기생충", "분노의 질주", "겨울왕국", "하울의 움직이는 성"};
    static int[] videoNum = {3, 3, 2, 3, 3};

    // 유저 정보
    static String[] id = {"admin", "abcd1234"};
    static String[] password = {"admin", "abcd1234"};
    static String[][] rentalVideo = {{"admin"}, {"어벤져스", "기생충"}};
    static String[][] rentalDate = {{"admin"}, {"210708", "210809"}};
    static String[][] returnDate = {{"admin"}, {"210815", "210820"}};

    // 현재 로그인 유저
    static String currentId;

    // 아이디 확인 하는 함수, 있으면 해당 아이디의 인덱스, 없으면 -1을 반환
    public static int checkId (String inputId) {
        for (int i = 0; i < id.length; i++) {
            if(inputId.equals(id[i])){
                return i;
            }
        }
        return -1;
    }

    // 로그인 기능
    public static void login() {
        System.out.println();
        System.out.println("=============================");
        System.out.println("++++++++++++로그인++++++++++++");
        System.out.println("=============================");
        while (true) {
            System.out.print("ID: ");
            String inputId = sc.nextLine();

            int idIdx = checkId(inputId);
            if(idIdx == -1) {
                System.out.println("없는 아이디 입니다.");
            } else {
                System.out.print("PW: ");
                String inputPw = sc.nextLine();
                if(!inputPw.equals(password[idIdx])) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                } else {
                    currentId = inputId;
                    System.out.println("=============================");
                    break;
                }
            }
        }

    }


    // 회원가입 기능
    public static void join() {
        System.out.println("=============================");
        System.out.println("+++++++++++회원가입+++++++++++");
        System.out.println("=============================");
        System.out.print("ID: ");
        String inputId = sc.nextLine();
        int idIdx = checkId(inputId);

        // 회원정보 비교
        if(idIdx != -1) {
            System.out.println("있는 아이디 입니다.");
        } else { // 데이터 추가
            System.out.print("PW: ");
            String inputPw = sc.nextLine();

            String[] idTemp = new String[id.length + 1];
            String[] pwTemp = new String[password.length + 1];
            String[][] rentalVideoTemp = new String[rentalVideo.length + 1][];
            String[][] returnDateTemp = new String[returnDate.length + 1][];
            String[][] rentalDateTemp = new String[rentalDate.length + 1][];

            for (int i = 0; i < id.length; i++) {
                idTemp[i] = id[i];
                pwTemp[i] = password[i];
                rentalVideoTemp[i] = rentalVideo[i];
                rentalDateTemp[i] = rentalDate[i];
                returnDateTemp[i] = returnDate[i];
            }

            idTemp[idTemp.length - 1] = inputId;
            pwTemp[pwTemp.length - 1] = inputPw;

            id = idTemp;
            password = pwTemp;
            rentalVideo = rentalVideoTemp;
            rentalDate = rentalDateTemp;
            returnDate = returnDateTemp;

            idTemp = null;
            pwTemp = null;

            System.out.println(Arrays.toString(id));
            System.out.println(Arrays.toString(password));
            System.out.println(Arrays.deepToString(rentalVideo));
        }
        System.out.println("=============================");
    }


    // 고객관리 화면
    static void customerMgm () {
        System.out.println("");
        System.out.println("=============================");
        System.out.println("+++++고객관리++++++");
        System.out.println("=============================");

        for (int i = 0; i <id.length; i++) {
            System.out.printf("%d| %s\n", i+1, id[i]);
        }
        System.out.println("=============================");
        System.out.println("고객 ID를 입력하세요.");
        System.out.print("> ");
        String inputId = sc.next();
        int idx = checkId(inputId);

        if (idx == -1) {
            System.out.println("해당 id가 존재하지 않습니다.");
        } else {
            System.out.println("");
            System.out.println("========================================================");
            System.out.printf("[%s]님이 대여한 비디오 목록\n", id[idx]);
            System.out.println("========================================================");
            System.out.println("번호 | 비디어 이름 | 대여 일자 | 반납일자 ");
            System.out.println("========================================================");
            for (int i = 0; i < rentalVideo[idx].length; i++) {
                System.out.printf("%-5d| %-12s| %-12s| %-12s\n", i+1, rentalVideo[idx][i], rentalDate[idx][i], returnDate[idx][i]);
            }
        }
    }

    // 프로그램 메인 실행부
    public static void main(String[] args) {
        // 메뉴 버튼
        int menuNum;

        // 첫 화면
        System.out.println("=============================");
        System.out.println("+++++비디오 대여 프로그램++++++");
        System.out.println("=============================");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("=============================");
        while(true) {
            System.out.print("> ");
            menuNum = sc.nextInt();
            sc.nextLine();

            switch (menuNum) {
                case 1:
                    login();
                    break;
                case 2:
                    join();
                    break;
                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
                    continue;
            }
            break;
        }

        if (currentId.equals("admin")) {
            //관리자 화면
            System.out.println("=============================");
            System.out.println("+++++관리자 권한++++++");
            System.out.println("=============================");
            System.out.println("1. 고객관리");
            System.out.println("2. 재고관리");
            System.out.print("> ");
            menuNum = sc.nextInt();

            switch (menuNum) {
                case 1:
                    customerMgm();
                case 2:
            }
        } else {
            System.out.println("이용자 페이지 입니다.");
        }


    }

}
