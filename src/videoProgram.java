import java.util.Arrays;
import java.util.Scanner;

import static java.lang.String.*;

public class videoProgram {

    static Scanner sc = new Scanner(System.in);

    // 비디오 정보
    static String[] video = {"겨울왕국", "어벤져스", "분노의 질주", "싱크홀", "모가디슈", "프리 가이", "보스베이비",
            "블랙위도우", "더 수어사이드스퀘드", "원티드 킬러", "마이 네버 리스트", "이도공간", "올드", "켈리 갱"};
    static int[] videoNum = {3, 2, 3, 3, 4, 3, 2, 1, 3, 5, 3, 2, 2, 5};

    // 유저 정보
    static String[] id = {"admin", "abcd1234"};
    static String[] password = {"admin", "abcd1234"};
    static String[][] rentalVideo = {{"admin"}, {"어벤져스", "기생충"}};
    static String[][] rentalDate = {{"admin"}, {"210708", "210809"}};
    static String[][] returnDate = {{"admin"}, {"210815", "210820"}};

    // 현재 로그인 유저
    static boolean isLogined = false;
    static boolean isQuit = false;
    static String currentId;

    // 화면 초기화
    public static void clearScreen() {
        for (int i = 0; i < 80; i++)
            System.out.println("");
    }
    
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
        clearScreen();
        System.out.println("=============================");
        System.out.println("++++++++++++로그인++++++++++++");
        System.out.println("=============================");
        System.out.println("* ID 입력 창에 숫자 0을 입력시 첫화면으로 이동");
        while (true) {
            System.out.print("ID: ");
            String inputId = sc.nextLine();

            if(inputId.equals("0")) {
                return;
            }

            int idIdx = checkId(inputId);
            if(idIdx == -1) {
                System.out.println("존재하지 않는 아이디 입니다.");
            } else {
                System.out.print("PW: ");
                String inputPw = sc.nextLine();
                if(!inputPw.equals(password[idIdx])) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                } else {
                    currentId = inputId;
                    isLogined = true;
                    System.out.println("=============================");
                    return;
                }
            }
        }

    }

    // 회원가입 기능
    public static void join() {
        clearScreen();
        System.out.println("=============================");
        System.out.println("+++++++++++회원가입+++++++++++");
        System.out.println("=============================");
        System.out.println("* 숫자 0을 입력시 첫화면으로 이동");
        System.out.print("ID: ");
        String inputId = sc.nextLine();
        if(inputId.equals("0")) {
            return;
        }

        int idIdx = checkId(inputId);

        // 회원정보 비교
        if(idIdx != -1) {
            System.out.println("존재하는 아이디 입니다.");
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

            System.out.println(Arrays.toString(id));
            System.out.println(Arrays.toString(password));
            System.out.println(Arrays.deepToString(rentalVideo));
        }
        System.out.println("=============================");
    }

    // 이용자 상세 정보 표시
    static void showUserInfo(int idx) {
        while (true) {
            clearScreen();
            System.out.println("========================================================");
            System.out.printf("[%s]님이 대여한 비디오 목록\n", id[idx]);
            System.out.println("========================================================");
            System.out.println("번호 |  비디오 이름  |  대여 일자  |  반납일자  ");
            System.out.println("========================================================");
            for (int i = 0; i < rentalVideo[idx].length; i++) {
                System.out.printf("%-4d| %-10s| %-10s| %-10s\n", i+1, rentalVideo[idx][i], rentalDate[idx][i], returnDate[idx][i]);
            }
            System.out.println("숫자 0을 입력시 뒤로갑니다.");
            System.out.print("> ");
            if(sc.nextLine().equals("0")) return;
        }
    }
    // 고객관리 화면
    static void customerMgm () {
        while(true) {
            clearScreen();
            System.out.println("=============================");
            System.out.println("+++++고객관리++++++");
            System.out.println("=============================");

            for (int i = 0; i <id.length; i++) {
                System.out.printf("%d| %s\n", i+1, id[i]);
            }
            System.out.println("=============================");
            System.out.println("* 고객 ID를 입력하세요.");
            System.out.println("* 숫자 0을 입력시 메인 메뉴로 이동합니다.");
            System.out.println("=============================");

            System.out.print("> ");
            String inputId = sc.nextLine();
            if(inputId.equals("0")) return;
            int idx = checkId(inputId);

            if (idx == -1) {
                System.out.println("해당 id가 존재하지 않습니다.");
            } else {
                showUserInfo(idx);
            }
        }
    }
    
    // 비디오 목록 보여주기 기능
    public static void showvideoList(int currentPage) {

        // 전체 페이지
        int page = (int) Math.ceil(video.length / 5.0);

        // 표시할 목록 위치 설정
        int startNum = 5 * (currentPage - 1);
        int endNum = 5 * currentPage;
        if (currentPage == page) {
            endNum = video.length;
        }

        // 목록 리스트 표시
        clearScreen();
        System.out.println("================");
        System.out.println("비디오 목록");
        System.out.println("================");
        for (int i = startNum; i < endNum; i++) {
            System.out.print((i + 1) + " " + video[i] + " " + videoNum[i] + "권 남음" + "\n");
        }
    }

    // 비디오 리스트 목록 보여주기
    public static void VideoList() {
        // 현재페이지
        int currentPage = 1;

        while (true) {

            showvideoList(currentPage);

            System.out.println("================");
            System.out.println("1. 다음페이지");
            System.out.println("2. 이전페이지");
            System.out.println("3. 비디오 추가");
            System.out.println("4. 비디오 제거");
            System.out.println("5. 메인 화면으로 이동");
            System.out.println("================");

            System.out.print("> ");
            int menuNum = sc.nextInt();
            sc.nextLine();

            // 선택된 메뉴에 따른 행위
            switch (menuNum) {
                case 1: // 다음페이지
                    if(currentPage == (int) Math.ceil(video.length / 5.0)) {
                        break;
                    }
                    currentPage++;
                    break;
                case 2: // 이전페이지
                    if(currentPage == 1) {
                        break;
                    }
                    currentPage--;
                    break;
                case 3: // 비디오 추가
                    addVideo();
                    break;
                case 4: // 비디오 삭제
                    removeVideo();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
            }

        }
    }

    //비디오 추가 기능
    static void addVideo() {

        System.out.println("추가할 비디오 이름을 입력하세요.");
        System.out.print("> ");
        String newVideo = sc.nextLine();

        System.out.println("추가할 비디오의 개수를 입력하세요.");
        System.out.print("> ");
        int newVideoNum = sc.nextInt();
        sc.nextLine();

        // 추가 확인
        System.out.printf("%s(%d개)를 추가하시겠습니까?\n", newVideo, newVideoNum);
        System.out.println("숫자 0을 입력하면 취소합니다.");
        System.out.print("> ");
        if(sc.nextLine().equals("0")) return;

        //배열에 비디오 이름 추가
        String[] videoTemp = new String[video.length + 1];
        int[] videoNumTemp = new int[videoNum.length + 1];

        for (int i = 0; i < videoTemp.length - 1; i++) {
            videoTemp[i] = video[i];
            videoNumTemp[i] = videoNum[i];
        }
        videoTemp[videoTemp.length - 1] = newVideo;
        videoNumTemp[videoNumTemp.length - 1] = newVideoNum;

        video = videoTemp;
        videoNum = videoNumTemp;
    }

    // 비디오 탐색 기능, 인덱스 반환, 없을시 -1 반환
    static public int checkVideoIdx(String searchVideoName) {
        int idx = -1;
        for (int i = 0; i < video.length; i++) {
            if (searchVideoName.equals(video[i])) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    // 비디오 삭제 기능
    public static void removeVideo() {
        while(true) {
            System.out.println("- 삭제할 비디오를 입력하세요! ");
            System.out.print("> ");
            String removeVideoName = sc.nextLine();
            
            // 인덱스 탐색
            int idx = checkVideoIdx(removeVideoName);

            // 비디오 유무 판단
            if (idx == -1) {
                System.out.println("없는 비디오 입니다.");
            } else {
                System.out.println(video[idx] + "을(를) 제거하시겠습니까?");
                System.out.println("숫자 0을 입력하면 취소합니다.");
                System.out.print("> ");
                if(sc.nextLine().equals("0")) return;

                //삭제 알고리즘
                for (int i = idx; i < video.length - 1; i++) {
                    video[i] = video[i + 1];
                    videoNum[i] = videoNum[i + 1];
                }

                String[] temp = new String[video.length - 1];
                int[] temp1 = new int[videoNum.length - 1];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = video[i];
                    temp1[i] = videoNum[i];
                }
                video = temp;
                videoNum = temp1;
                return;
            }
        }
    }
    
    // 로그인 화면(첫 화면)
    static public void loginMenu() {
        // 메뉴 버튼
        int menuNum;

        // 첫 화면
        clearScreen();
        System.out.println("=============================");
        System.out.println("+++++비디오 대여 프로그램++++++");
        System.out.println("=============================");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 프로그램 종료");
        System.out.println("=============================");
        while(true) {
            System.out.print("> ");
            menuNum = sc.nextInt();
            sc.nextLine();

            switch (menuNum) {
                case 1: // 로그인
                    login();
                    return;
                case 2: // 회원가입
                    join();
                    return;
                case 3:
                    systemQuit();
                    return;
                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
            }
        }
    }

    // 시스템 종료 기능
    static public void systemQuit() {
        System.out.println("프로그램을 종료하시겠습니까?");
        System.out.println("숫자 0을 입력하면 취소합니다.");
        System.out.print("> ");
        if(sc.nextLine().equals("0")) return;
        isQuit = true;
    }

    // 로그아웃 기능
    static  public void logout() {
        System.out.println("로그아웃하시겠습니까?");
        System.out.println("숫자 0을 입력하면 취소합니다.");
        System.out.print("> ");
        if(sc.nextLine().equals("0")) return;
        isLogined = false;
        currentId = "";
    }

    static public void mainMenu() {
        int menuNum = 0;
        if (currentId.equals("admin")) {  //관리자 화면
            clearScreen();
            System.out.println("=============================");
            System.out.println("+++++관리자 권한++++++");
            System.out.println("=============================");
            System.out.println("1. 고객관리");
            System.out.println("2. 재고관리");
            System.out.println("3. 로그아웃");
            System.out.println("4. 프로그램 종료");

            while (true) {
                System.out.print("> ");
                menuNum = sc.nextInt();
                sc.nextLine();

                switch (menuNum) {
                    case 1:
                        customerMgm();
                        return;
                    case 2:
                        VideoList();
                        return;
                    case 3:
                        logout();
                        return;
                    case 4:
                        systemQuit();
                        return;
                    default:
                        System.out.println("잘못된 메뉴 선택입니다.");
                }
            }
        } else { // 이용자 화면
            System.out.println("=============================");
            System.out.println("+++++비디오 대여++++++");
            System.out.println("=============================");
            System.out.println("1. 비디오 목록");
            System.out.println("2. 대여 내역");
            System.out.println("3. 로그아웃");
            System.out.println("4. 프로그램 종료");

            while (true) {
                System.out.print("> ");
                menuNum = sc.nextInt();
                sc.nextLine();

                switch (menuNum) {
                    case 1:
                        customerMgm();
                        return;
                    case 2:
                        VideoList();
                        return;
                    case 3:
                        logout();
                        return;
                    case 4:
                        systemQuit();
                        return;
                    default:
                        System.out.println("잘못된 메뉴 선택입니다.");
                }
            }
        }
    }


    // 프로그램 메인 실행부
    public static void main(String[] args) {

        while(!isQuit) {
            if(!isLogined) { // 로그인되지 않았다면
                loginMenu();
            } else { // 로그인 상태라면
                mainMenu();
            }
        }
    }
}

